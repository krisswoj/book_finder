package pl.krzysiek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.allegro_api.domain.Regular;
import pl.krzysiek.api.google_books_api.domain.IndustryIdentifier;
import pl.krzysiek.api.google_books_api.domain.Item;
import pl.krzysiek.domain.Book;
import pl.krzysiek.domain.CompareBookPrices;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class SearchBookService {

    private GoogleBooksService googleBooksService;
    private AllegroServices allegroServices;
    private ConverterService converterService;
    private HelionService helionService;
    private BookBookService bookBookService;

    @Value("${ALLEGRO_OFFER_URL}")
    private String allegroUrl;

    @Autowired
    public SearchBookService(GoogleBooksService googleBooksService, AllegroServices allegroServices, ConverterService converterService, HelionService helionService, BookBookService bookBookService) {
        this.googleBooksService = googleBooksService;
        this.allegroServices = allegroServices;
        this.converterService = converterService;
        this.helionService = helionService;
        this.bookBookService = bookBookService;
    }

    public List<Book> findBook(String bookTitle) throws IOException, URISyntaxException {

        List<Book> booksList = new ArrayList<>();

        if (googleBooksService.findBook(bookTitle).getItems() == null)
            return booksList;

        for (Item itemBook : googleBooksService.findBook(bookTitle).getItems()) {

            Book book = new Book();
            List<CompareBookPrices> compareBookPricesList = new ArrayList<>();

            book.setTitle(itemBook.getVolumeInfo().getTitle());
            book.setPublishedDate(itemBook.getVolumeInfo().getPublishedDate());
            book.setDescription(itemBook.getVolumeInfo().getDescription());

            if (itemBook.getVolumeInfo().getImageLinks() != null)
                book.setPictureLink(itemBook.getVolumeInfo().getImageLinks().getSmallThumbnail().orElse("null").toString());

            setIsbn(itemBook.getVolumeInfo().getIndustryIdentifiers(), book);
            checkGoogleStore(compareBookPricesList, itemBook);
            checkAllegro(compareBookPricesList, book.getIsbn10(), book.getIsbn13(), book);

//            checkHelionStore(compareBookPricesList, book);
//            checkBookBookStore(compareBookPricesList, book);

            compareBookPricesList.sort(Comparator.comparing(CompareBookPrices::getPrice));

            book.setCompareBookPrices(compareBookPricesList);
            booksList.add(book);

        }
        return booksList;
    }



    private Book setIsbn(List<IndustryIdentifier> list, Book book) {

        if (list == null) return book;

        Map<String, String> isbnMap = new HashMap<>();
        for (IndustryIdentifier industryIdentifier : list) {
            isbnMap.put(industryIdentifier.getType(), industryIdentifier.getIdentifier());

            String ifOther = isbnMap.get("OTHER");
            if (ifOther != null) {
                book.setIsbn13(isbnMap.get("OTHER"));
            } else {
                book.setIsbn13(isbnMap.get("ISBN_13"));
                book.setIsbn10(isbnMap.get("ISBN_10"));
            }
        }
        return book;
    }

    private List<CompareBookPrices> checkAllegro(List<CompareBookPrices> compareBookPricesList, String isbn10, String isbn13, Book book) throws IOException, URISyntaxException {

        if (isbn10 == null && isbn13 == null) return compareBookPricesList;

        allegroSearchByIsbn(compareBookPricesList, isbn13);
        allegroSearchByIsbn(compareBookPricesList, isbn10);

        return compareBookPricesList;
    }

    private List<CompareBookPrices> allegroSearchByIsbn(List<CompareBookPrices> compareBookPricesList, String isbn) throws IOException, URISyntaxException {

        if (allegroServices.allegroAuctionRespone(isbn).getItems().getRegular().size() == 0)
            return compareBookPricesList;

        for (Regular element : allegroServices.allegroAuctionRespone(isbn).getItems().getRegular()) {

            CompareBookPrices compareBookPrices = new CompareBookPrices();
            compareBookPrices.setStoreName("Allegro");
            compareBookPrices.setDirectLink(allegroUrl+element.getId());
            compareBookPrices.setStoreBookTitle(element.getName());
            compareBookPrices.setCurrency(element.getSellingMode().getPrice().getCurrency());
            compareBookPrices.setPrice(converterService.priceConventer(element.getSellingMode().getPrice().getAmount()));
            compareBookPrices.setShippingCost(converterService.priceConventer(element.getDelivery().getLowestPrice().getAmount()));
            compareBookPricesList.add(compareBookPrices);
        }

        return compareBookPricesList;
    }

    private List<CompareBookPrices> checkGoogleStore(List<CompareBookPrices> compareBookPricesList, Item itemBook) {

        if (!itemBook.getSaleInfo().getSaleability().equals("FOR_SALE")) return compareBookPricesList;

        CompareBookPrices compareBookPrices = new CompareBookPrices();
        compareBookPrices.setStoreName("Google store");
        compareBookPrices.setDirectLink(itemBook.getSaleInfo().getBuyLink());
        compareBookPrices.setCurrency(itemBook.getSaleInfo().getRetailPrice().getCurrencyCode());
        compareBookPrices.setPrice(itemBook.getSaleInfo().getRetailPrice().getAmount());
        compareBookPricesList.add(compareBookPrices);
        return compareBookPricesList;
    }

    private List<CompareBookPrices> checkHelionStore(List<CompareBookPrices> compareBookPricesList, Book book) throws IOException {

        CompareBookPrices compareBookPrices = helionService.helionCheckPrice(book.getIsbn10());
        CompareBookPrices compareBookPrices2 = helionService.helionCheckPrice(book.getIsbn13());

        if(compareBookPrices != null)
            compareBookPricesList.add(compareBookPrices);

        if(compareBookPrices2 != null)
            compareBookPricesList.add(compareBookPrices2);

        return compareBookPricesList;
    }

    private List<CompareBookPrices> checkBookBookStore(List<CompareBookPrices> compareBookPricesList, Book book) throws IOException {

        CompareBookPrices compareBookPrices = bookBookService.bookBookCheckPrice(book.getIsbn10());
        CompareBookPrices compareBookPrices2 = bookBookService.bookBookCheckPrice(book.getIsbn13());

        if(compareBookPrices != null)
            compareBookPricesList.add(compareBookPrices);

        if(compareBookPrices2 != null)
            compareBookPricesList.add(compareBookPrices2);

        return compareBookPricesList;
    }
}
package pl.krzysiek.services;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.krzysiek.domain.CompareBookPrices;

import java.io.IOException;
import java.net.SocketTimeoutException;

@Service
public class BookBookService {

    @Autowired
    private ConverterService converterService;

    private final String brandName = "Book Book";

    @Value("${BOOKBOOK_DOMAIN}")
    private String bookBookDomain;

    @Value("${BOOKBOOK_SEARCH_URL}")
    private String bookBookSearchUrl;

    public CompareBookPrices bookBookCheckPrice(String isbn) throws IOException {
        CompareBookPrices compareBookPrice = new CompareBookPrices();
        try {
            String url = bookBookSearchUrl + isbn;
            Document doc = converterService.jsoupConnector(url);

            String bookUrl = getDirectLinkBb(doc);
            Double bookPrice = null;

            System.out.println("Wyszukiwana fraza: " + url);
            System.out.println("Uzykany link url z bookbook: " + bookUrl);

            if (bookUrl == null || bookUrl.equals(bookBookDomain)) return null;

            bookPrice = converterService.priceConventer(bookPriceInStringBb(bookUrl));

            if (bookPrice != null) {
                compareBookPrice.setDirectLink(bookUrl);
                compareBookPrice.setPrice(bookPrice);
                compareBookPrice.setStoreName(brandName);
                compareBookPrice.setCurrency("PLN");
            }

        } catch (SocketTimeoutException e) {
            System.out.println("Księgarnia BookBook jest obecnie niedostepna, prosze sprobowac pozniej");
        }
        return compareBookPrice;
    }

    /*
     *If the book is not available in the store (they don't have it in offer), system will return null. Otherwise direct link to book;
     */

    private String getDirectLinkBb(Document document) {

        Elements element = document.select(".message.flat.no-items");
        if (!element.text().isEmpty())
            return null;

        Elements link = document.select("div[data-type=\"product-list\"]").select("a[href]");
        return bookBookDomain + link.attr("href");
    }

    /*
     *If the book is not available for sell (But they have or had in offer), system will return null. Otherwise price in String format;
     */

    private String bookPriceInStringBb(String directBookUrl) throws IOException {
        Document document = converterService.jsoupConnector(directBookUrl);

        Elements element = document.select(".product-unavailable-label");
        if (element.text().contains("niedostępna"))
            return null;

        Elements elementt = document.select(".message.flat.no-items");
        if (elementt.text().contains("niedostępna"))
            return null;

        Elements elementTwo = document.select(".product-price-row, .price").select("span");
        System.out.println("Format pobranej ceny: " + elementTwo.text());
        return elementTwo.text();
    }
}

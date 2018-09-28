package pl.krzysiek.services;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketTimeoutException;

@Service
public class BookBookService {

    @Autowired
    private ConverterService converterService;

    private final String brandName = "Book Book";
    private final String brandDomain = "https://www.bookbook.pl";

    public Double bookBookCheckPrice() throws IOException {

        Double priceBook = 0.0;

        try {
            String url = "https://www.bookbook.pl/s?q=9788380085091";
            Document doc = converterService.jsoupConnector(url);

            String zobaczymy = directLinkToBookBook(doc);
            System.out.println("stan linku: " + zobaczymy);

            priceBook = converterService.priceConventer(bookPriceInStringBookBook(zobaczymy));
            return priceBook;

        } catch (SocketTimeoutException e) {
            System.out.println("Księgarnia BookBook jest obecnie niedostepna, prosze sprobowac pozniej");
        }
        return priceBook;
    }

    /*
     *If the book is not available in the store (they don't have it in offer), system will return null. Otherwise direct link to book;
     */

    private String directLinkToBookBook(Document document) {

        Elements element = document.select(".message.flat.no-items");
        if (!element.text().isEmpty())
            return null;

        Elements link = document.select("div[data-type=\"product-list\"]").select("a[href]");
        return brandDomain+link.attr("href");
    }

    /*
     *If the book is not available for sell (But they have or had in offer), system will return null. Otherwise price in String format;
     */

    private String bookPriceInStringBookBook(String directBookUrl) throws IOException {
        Document document = converterService.jsoupConnector(directBookUrl);

        Elements element = document.select(".product-unavailable-label");
        if (element.text().contains("niedostępna"))
            return null;

        Elements elementTwo = document.select(".product-price-row, .price").select("span");
        System.out.println("Format pobranej ceny: " + elementTwo.text());
        return elementTwo.text();
    }
}

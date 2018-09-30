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
public class HelionService {

    @Autowired
    private ConverterService converterService;

    private final String brandName = "Helion";

    @Value("${HELION_SEARCH_URL}")
    private String helionSearchUrl;

    public CompareBookPrices helionCheckPrice(String isbn) throws IOException {
        CompareBookPrices compareBookPrice = new CompareBookPrices();
        try {
            String url = helionSearchUrl + isbn;
            Document doc = converterService.jsoupConnector(url);

            String bookUrl = getDirectLink(doc);
            Double bookPrice = null;

            System.out.println("Wyszukiwana fraza: " + url);
            System.out.println("Uzykany link url z bookbook: " + bookUrl);

            if (bookUrl != null)
                bookPrice = converterService.priceConventer(bookPriceInString(bookUrl));

            if (bookPrice != null) {
                compareBookPrice.setDirectLink(bookUrl);
                compareBookPrice.setPrice(bookPrice);
                compareBookPrice.setStoreName(brandName);
                compareBookPrice.setCurrency("PLN");
            }

            if (bookUrl == null || bookPrice == null) return null;

        } catch (SocketTimeoutException e) {
            System.out.println("Księgarnia Helion jest obecnie niedostepna, prosze sprobowac pozniej");
        }
        return compareBookPrice;
    }

    /*
     *If the book is not available in the store (they don't have it in offer), system will return null. Otherwise direct link to book;
     */

    private String getDirectLink(Document document) {

        Elements element = document.select(".not-found");
        if (!element.text().isEmpty())
            return null;

        Elements link = document.select(".book-list-inner").select("a[href]");
        if (link.attr("href").isEmpty())
            return null;

        return link.attr("href");
    }

    /*
     *If the book is not available for sell (But they have or had in offer), system will return null. Otherwise price in String format;
     */

    private String bookPriceInString(String directBookUrl) throws IOException {
        Document document = converterService.jsoupConnector(directBookUrl);
        Elements element = document.select(".book-price");
        return element.text();
    }

}

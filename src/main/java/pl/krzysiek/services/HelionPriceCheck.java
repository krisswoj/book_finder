package pl.krzysiek.services;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HelionPriceCheck {

    @Autowired
    private PriceChecker priceChecker;

    private final String brandName = "Helion";

    public void helionCheckPrice() throws IOException {

        try {
            String url = "https://helion.pl/search?qa=&serwisyall=0&szukaj=isbn%253A9788328330061";
            Document doc = priceChecker.jsoupConnector(url);


            String zobaczymy = directLinkToBook(doc);
            System.out.println("stan linku: " + zobaczymy);

            if(zobaczymy != null){
                System.out.println("Cena ksiazki: " + priceChecker.priceConventer(bookPriceInString(zobaczymy)));
            }


        } catch (SocketTimeoutException e) {
            System.out.println("Księgarnia Helion jest obecnie niedostepna, prosze sprobowac pozniej");
        }
    }

    /*
     *If the book is not available in the store (they don't have it in offer), system will return null. Otherwise direct link to book;
     */

    private String directLinkToBook(Document document) {

        Elements element = document.select(".not-found");
        if (!element.text().isEmpty())
            return null;
        Elements link = document.select(".book-list-inner").select("a[href]");
        return link.attr("href");
    }

    /*
     *If the book is not available for sell (But they have or had in offer), system will return null. Otherwise price in String format;
     */

    private String bookPriceInString(String directBookUrl) throws IOException {
        Document document = priceChecker.jsoupConnector(directBookUrl);
        Elements element = document.select(".book-price").select("span");

        if (element.text().contains("niedostępna"))
            return null;

        Elements elementTwo = document.select(".book-price");
        return elementTwo.text();
    }

}

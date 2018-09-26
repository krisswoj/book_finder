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
            String url = "https://helion.pl/search?qa=&serwisyall=&szukaj=8371976038&wprzyg=&wsprzed=&wyczerp=";
            Document doc = priceChecker.jsoupConnector(url);


            String zobaczymy = directLinkToBook(doc);
            System.out.println("nie ma linku" + zobaczymy);

            Double price = priceChecker.priceConventer(bookPriceInString("https://helion.pl/ksiazki/java-9-przewodnik-doswiadczonego-programisty-wydanie-ii-cay-s-horstmann,jav9p2.htm#format/d"));

            System.out.println("Cena ksiazki: " + price);


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
        return link.get(0).attr("href");
    }

    /*
     *If the book is not available for sell (But they have or had in offer), system will return null. Otherwise price in String format;
     */

    private String bookPriceInString(String url) throws IOException {
        Document document = priceChecker.jsoupConnector(url);
        Elements element = document.select(".book-price").select("span");

        if (element.text().contains("niedostępna"))
            return null;

        Elements elementTwo = document.select(".book-price");
        return elementTwo.text();
    }

}

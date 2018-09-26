package pl.krzysiek.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PriceChecker {

    /*
     *Function to establish a connection with the store website
     */

    public Document jsoupConnector(String url) throws IOException {
        return Jsoup.connect(url)
                .header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                .get();
    }

    /*
     *Function to convert coded price in String to Double
     */

    public Double priceConventer(String stringPrice) throws IOException {

        Pattern compiledPattern = Pattern.compile("\\d{1,}[,. ]\\d{1,}");
        Matcher matcher = compiledPattern.matcher(stringPrice);

        List<Double> allMatches = new ArrayList<>();
        while (matcher.find()) {
            allMatches.add(Double.parseDouble(matcher.group()
                    .replace(",", ".")
                    .replace(" ", ".")));
        }
        return allMatches.get(0);
    }

}
package pl.krzysiek.services;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.Normalizer;
import java.text.Normalizer.Form;

@Service
public class ConverterService {

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

        if (stringPrice == null) return 0.0;

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

    public String searchQueryConverter(String searchTitle) {
        Pattern compiledPattern = Pattern.compile("\\w+");
        Matcher matcher = compiledPattern.matcher(removeAccents(searchTitle));

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group()).append("+");
        }
        return result.toString();
    }

    private String removeAccents(String text) {
        return text == null ? null :
                Normalizer.normalize(text, Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public String HttpResponseConverter(CloseableHttpResponse response) throws IOException {

        String res = null;
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            InputStream instream = entity.getContent();
            byte[] bytes = IOUtils.toByteArray(instream);
            res = new String(bytes, "UTF-8");
            instream.close();
        }
        return res;
    }
}
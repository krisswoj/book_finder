package pl.krzysiek.services;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.google_books_api.GoogleBooksResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

@Service
public class GoogleBooksService {

    @Autowired
    private ConverterService converterService;

    private final Gson gson = new Gson();

    @Value("${GOOGLE_BOOKS_API_URL}")
    private String googleUrl;

    @Value("${JSON_CONTENT_TYPE}")
    private String jsonContent;

    public GoogleBooksResponse findBook(String searchTitle) throws IOException, URISyntaxException {

        CloseableHttpClient client = HttpClients.createDefault();

        URIBuilder builder = new URIBuilder(googleUrl);
        String url = converterService.searchQueryConverter(searchTitle);
        builder.setParameter("q", url);
        builder.setParameter("source", "lnms");
        builder.setParameter("tbm", "bks");
        builder.setParameter("orderBy", "newest");
        builder.setParameter("printType", "books");
        HttpGet httpGet = new HttpGet(URLDecoder.decode(builder.build().toString()));

        System.out.println("Wygenerowany link: " + httpGet.toString());
        httpGet.setHeader("Content-Type", jsonContent);

        CloseableHttpResponse response = client.execute(httpGet);
        String res = converterService.HttpResponseConverter(response);
        client.close();

        return gson.fromJson(res, GoogleBooksResponse.class);
    }
}

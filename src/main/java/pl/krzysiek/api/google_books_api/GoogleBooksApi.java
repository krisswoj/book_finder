package pl.krzysiek.api.google_books_api;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.google_books_api.domain.GoogleBooksResponse;
import pl.krzysiek.services.ConverterService;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class GoogleBooksApi {

    @Autowired
    private ConverterService converterService;

    private final Gson gson = new Gson();

    @Value("${GOOGLE_BOOKS_API_URL}")
    private String googleUrl;

    @Value("${JSON_CONTENT_TYPE}")
    private String jsonContent;

    public GoogleBooksApi() {
    }

    public GoogleBooksResponse findBook(String searchTitle) throws IOException, URISyntaxException {

        CloseableHttpClient client = HttpClients.createDefault();

        URIBuilder builder = new URIBuilder(googleUrl);
        builder.setParameter("q", converterService.searchQueryConverter(searchTitle));
        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.setHeader("Content-Type", jsonContent);

        CloseableHttpResponse response = client.execute(httpGet);
        String res = converterService.HttpResponseConverter(response);
        client.close();

        return gson.fromJson(res, GoogleBooksResponse.class);
    }
}

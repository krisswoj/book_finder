package pl.krzysiek.services;

import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.allegro_api.domain.AllegroApiResponeAuction;
import pl.krzysiek.domain.AllegroResponseToken;
import pl.krzysiek.repository.AllegroTokenRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class AllegroServices {

    @Autowired
    private AllegroTokenRepository allegroTokenRepository;

    @Autowired
    private ConverterService converterService;

    private final Gson gson = new Gson();

    @Value("${ALLEGRO_API_CLIENT_ID}")
    private String clientId;

    @Value("${ALLEGRO_API_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${ALLEGRO_BOOK_CATEGORY}")
    private String bookCategory;

    @Value("${ALLEGRO_URL_TO_GET_TOKEN}")
    private String allegroTokenUrl;

    @Value("${ALLEGRO_URL_TO_AUTH}")
    private String allegroAuth;

    @Value("${REDIRECT_MAIN_URL}")
    private String redirectUrl;

    @Value("${ALLEGRO_URL_API_OFFERS}")
    private String offersUrl;

    @Value("${ALLEGRO_ACCEPT_API_IFNO}")
    private String apiInfo;

    public List<AllegroResponseToken> allegroResponseApis() {
        return (List<AllegroResponseToken>) allegroTokenRepository.findAll();
    }

    public AllegroResponseToken saveToken(AllegroResponseToken allegroResponseToken) {
        return allegroTokenRepository.save(allegroResponseToken);
    }

    private String base64Encoder(String clientId, String clientSecret){
        return Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
    }

    public String allegroToken(){
        return allegroTokenRepository.findTopByOrderByIdDesc().getAccessToken();
    }

    public String allegroAuthUrl() throws URISyntaxException {

        URIBuilder builder = new URIBuilder(allegroAuth);
        builder
                .setParameter("response_type", "code")
                .setParameter("client_id", clientId)
                .setParameter("redirect_uri", redirectUrl);

        return builder.toString();
    }

    public AllegroResponseToken allegroApiTokenAuthO(String code) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(allegroTokenUrl);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("redirect_uri", redirectUrl));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        httpPost.setHeader("Authorization", "Basic " + base64Encoder(clientId, clientSecret));
        CloseableHttpResponse response = client.execute(httpPost);
        String res = converterService.HttpResponseConverter(response);
        AllegroResponseToken allegroResponseToken = gson.fromJson(res, AllegroResponseToken.class);

        client.close();

        return saveToken(allegroResponseToken);
    }

    public AllegroApiResponeAuction allegroAuctionRespone(String sellerId, String searchPhrase) throws IOException, URISyntaxException {

        CloseableHttpClient client = HttpClients.createDefault();

        URIBuilder builder = new URIBuilder(offersUrl);
        builder
                .setParameter("categoryId", bookCategory)
                .setParameter("seller.id", sellerId)
                .setParameter("searchMode", "DESCRIPTIONS")
                .setParameter("phrase", searchPhrase);

        HttpGet httpGet = new HttpGet(builder.build());

        httpGet.setHeader("Authorization", "Bearer " + allegroToken());
        httpGet.setHeader("Accept", apiInfo);
        CloseableHttpResponse response = client.execute(httpGet);
        String res = converterService.HttpResponseConverter(response);
        client.close();

        return gson.fromJson(res, AllegroApiResponeAuction.class);
    }

}

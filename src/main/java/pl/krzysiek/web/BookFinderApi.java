package pl.krzysiek.web;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.krzysiek.api.google_books_api.domain.GoogleBooksResponse;
import pl.krzysiek.domain.AllegroResponseApi;
import pl.krzysiek.repository.AllegroResponseApiRepository;
import pl.krzysiek.repository.ICurrencyRepository;
import pl.krzysiek.domain.Currency;
import pl.krzysiek.services.AllegroServices;
import pl.krzysiek.services.BookBookPriceCheck;
import pl.krzysiek.services.CurrencyService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Base64;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@Transactional
@RestController
public class BookFinderApi {


    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ICurrencyRepository currencyRepository;

    @Autowired
    private BookBookPriceCheck bookBookPriceCheck;

    @Autowired
    private AllegroServices allegroServices;

    private final Gson gson = new Gson();

    private final String base64Hash = "NDAwODM1ZGNlNTVjNDVjMjhkZjJmNmVlMTE1NTFiMGM6NW9XeURpRndjQURreURsejMxOVhpWXUyaVdTbWdFUmd6V3VZcG9IREpTNkxHNGo2MVcxQ1pvem9meElSTE1ESg==";


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String allegroCode(@RequestParam(value = "code") String code) throws IOException {
        String info = this.whenPostJsonUsingHttpClient_thenCorrect(code);
        return info;
    }


    public String whenPostJsonUsingHttpClient_thenCorrect(String code)
            throws ClientProtocolException, IOException {



//        URL url = new URL ("http://ip:port/login");
//        String encoding = Base64.getEncoder().encodeToString(("test1:test1").getBytes());
//
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setDoOutput(true);
//        connection.setRequestProperty  ("Authorization", "Basic " + encoding);
//        InputStream content = (InputStream)connection.getInputStream();
//        BufferedReader in   =
//                new BufferedReader (new InputStreamReader(content));
//        String line;
//        while ((line = in.readLine()) != null) {
//            System.out.println(line);
//        }



//        DefaultHttpClient httpclient = new DefaultHttpClient();
//
//        String encoding = Base64.getEncoder().encodeToString(("test1:test1").getBytes());
//        HttpPost httppost = new HttpPost("http://host:post/test/login");
//        httppost.setHeader("Authorization", "Basic " + encoding);
//
//        System.out.println("executing request " + httppost.getRequestLine());
//        HttpResponse response = httpclient.execute(httppost);
//        HttpEntity entity = response.getEntity();


        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://allegro.pl/auth/oauth/token");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("redirect_uri", "https://book-finder-krisswoj.herokuapp.com"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        httpPost.setHeader("Authorization", "Basic " + base64Hash);

        CloseableHttpResponse response = client.execute(httpPost);

        String info1 = "Odpowiedz z serwera: " + response.getStatusLine().getStatusCode();
        String info2 = "Odpowiedz z serwera: " + response.getStatusLine().getReasonPhrase();
        String info3 = "Odpowiedz z serwera: " + response.getStatusLine().getProtocolVersion().getProtocol();


        String res = this.response(response);

        client.close();

        return res;
    }


    public String response(CloseableHttpResponse response) throws IOException {

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


    @RequestMapping(value = "/works", method = RequestMethod.GET)
    public String worksTest() {
        return "it works";
    }

    @RequestMapping(value = "/test-book", method = RequestMethod.GET)
    public String testBook() throws IOException {
        return String.valueOf(bookBookPriceCheck.bookBookCheckPrice());
    }

    @RequestMapping(value = "/allegro", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AllegroResponseApi tokenData(@RequestBody AllegroResponseApi allegroResponseApi) {
        return allegroServices.save(allegroResponseApi);
    }

    @RequestMapping(value = "/all-allegro", method = RequestMethod.GET)
    public List<AllegroResponseApi> allegroResponseApiList() {
        return allegroServices.allegroResponseApis();
    }

    @RequestMapping(value = "/all-xml", method = RequestMethod.GET)
    public List<Currency> getAllFromXml() {
        return currencyService.allCurrenciesFromXml();
    }

    @RequestMapping(value = "/update-all", method = RequestMethod.GET)
    public List<Currency> updateCurrencyRate() throws IOException {
        return currencyService.updateRateAllCurrency();
    }

    @RequestMapping(value = "/last-rates/{amount}", method = RequestMethod.GET)
    public List<List<Currency>> lastXAllCurrency(@PathVariable("amount") Integer amount) {
        return currencyService.lastXRatesAllCurrency(amount);
    }

    @RequestMapping(value = "/last-rates", method = RequestMethod.GET)
    public List<Currency> lastAllCurrencyRates() {
        return currencyService.lastAllCurrencyRates();
    }

    @RequestMapping(value = "/last-rate/{currencyPair}", method = RequestMethod.GET)
    public Currency lastCurrency(@PathVariable("currencyPair") String currencyPair) {
        return currencyRepository.lastSingleCurrencyPairRates(currencyPair);
    }


}

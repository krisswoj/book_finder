package pl.krzysiek.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.krzysiek.domain.AllegroToken;
import pl.krzysiek.domain.Book;
import pl.krzysiek.repository.ICurrencyRepository;
import pl.krzysiek.domain.Currency;
import pl.krzysiek.services.AllegroServices;
import pl.krzysiek.services.CurrencyService;
import pl.krzysiek.services.SearchBookService;

import javax.transaction.Transactional;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.*;

@Transactional
@RestController
public class BookFinderApi {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ICurrencyRepository currencyRepository;

    @Autowired
    private AllegroServices allegroServices;

    @Autowired
    private SearchBookService searchBookService;

    private Gson gson = new Gson();


    @RequestMapping(value = "/new-token", method = RequestMethod.GET)
    public ModelAndView allegroNewToken() throws URISyntaxException {
        return new ModelAndView("redirect:" + allegroServices.allegroAuthUrl());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String allegroCode(@RequestParam(value = "code") String code) throws IOException {
        AllegroToken info = allegroServices.allegroApiTokenAuthO(code);
        return info.getAccessToken();

    }


    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String checkBookPrices(@RequestParam(value = "book") String bookName) throws IOException, URISyntaxException {

        Type founderListType = new TypeToken<List<Book>>() {
        }.getType();
        return gson.toJson(searchBookService.findBook(bookName), founderListType);
    }

    @RequestMapping(value = "/works", method = RequestMethod.GET)
    public String worksTest() {
        return "it works";
    }


    @RequestMapping(value = "/allegro", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AllegroToken tokenData(@RequestBody AllegroToken allegroToken) {
        return allegroServices.saveToken(allegroToken);
    }

    @RequestMapping(value = "/all-allegro", method = RequestMethod.GET)
    public List<AllegroToken> allegroResponseApiList() {
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

package pl.krzysiek.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.krzysiek.api.allegro_api.AllegroApiResponeAuction;
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

    private CurrencyService currencyService;
    private ICurrencyRepository currencyRepository;
    private AllegroServices allegroServices;
    private SearchBookService searchBookService;

    private Gson gson = new Gson();

    @Autowired
    public BookFinderApi(CurrencyService currencyService, ICurrencyRepository currencyRepository, AllegroServices allegroServices, SearchBookService searchBookService) {
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
        this.allegroServices = allegroServices;
        this.searchBookService = searchBookService;
    }

    @GetMapping(value = "/allegro-auctions", params = "code")
    public AllegroApiResponeAuction allegroApiResponeAuction (@RequestParam(value = "code") String searchPositions) throws IOException, URISyntaxException {
        return allegroServices.allegroAuctionRespone(searchPositions);
    }

    @GetMapping(value = "/find", params = "book")
    public String checkBookPrices(@RequestParam(value = "book") String bookName) throws IOException, URISyntaxException {

        Type founderListType = new TypeToken<List<Book>>() {
        }.getType();
        return gson.toJson(searchBookService.findBook(bookName), founderListType);
    }

    @GetMapping(value = "/new-token")
    public ModelAndView allegroNewToken() throws URISyntaxException {
        return new ModelAndView("redirect:" + allegroServices.allegroAuthUrl());
    }

    @GetMapping(value = "/", params = "code")
    public String allegroCode(@RequestParam(value = "code") String code) throws IOException {
        AllegroToken info = allegroServices.allegroApiTokenAuthO(code);
        return "token has been refreshed";

    }

    @GetMapping(value = "/works")
    public String worksTest() {
        return "it works";
    }


    @PostMapping(value = "/allegro",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AllegroToken tokenData(@RequestBody AllegroToken allegroToken) {
        return allegroServices.saveToken(allegroToken);
    }

    @GetMapping(value = "/all-allegro")
    public List<AllegroToken> allegroResponseApiList() {
        return allegroServices.allegroResponseApis();
    }

    @GetMapping(value = "/all-xml")
    public List<Currency> getAllFromXml() {
        return currencyService.allCurrenciesFromXml();
    }

    @GetMapping(value = "/update-all")
    public List<Currency> updateCurrencyRate() throws IOException {
        return currencyService.updateRateAllCurrency();
    }

    @GetMapping(value = "/last-rates/{amount}")
    public List<List<Currency>> lastXAllCurrency(@PathVariable("amount") Integer amount) {
        return currencyService.lastXRatesAllCurrency(amount);
    }

    @GetMapping(value = "/last-rates")
    public List<Currency> lastAllCurrencyRates() {
        return currencyService.lastAllCurrencyRates();
    }

    @GetMapping(value = "/last-rate/{currencyPair}")
    public Currency lastCurrency(@PathVariable("currencyPair") String currencyPair) {
        return currencyRepository.lastSingleCurrencyPairRates(currencyPair);
    }
}

package pl.krzysiek.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.krzysiek.dao.ICurrencyRepository;
import pl.krzysiek.domain.Currency;
import pl.krzysiek.services.CurrencyService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;


@Transactional
@RestController
public class BookFinderApi {


    @Autowired
    CurrencyService currencyService;

    @Autowired
    ICurrencyRepository currencyRepository;


    @RequestMapping(value = "/all-xml", method = RequestMethod.GET)
    public List<Currency> getAllFromXml() {
        return currencyService.allCurrenciesFromXml();
    }

    @RequestMapping(value = "/update-all", method = RequestMethod.GET)
    public List<Currency> updateCurrencyRate() throws IOException {
        return currencyService.updateRateAllCurrency();
    }

    @RequestMapping(value = "/last-rates/{amount}", method = RequestMethod.GET)
    public List<List<Currency>> lastXAllCurrency(@PathVariable ("amount") Integer amount){
        return currencyService.lastXRatesAllCurrency(amount);
    }

    @RequestMapping(value = "/last-rates", method = RequestMethod.GET)
    public List<Currency> lastAllCurrencyRates(){
        return currencyService.lastAllCurrencyRates();
    }

    @RequestMapping(value = "/last-rate/{currencyPair}", method = RequestMethod.GET)
    public Currency lastCurrency(@PathVariable ("currencyPair") String currencyPair){
        return currencyRepository.lastSingleCurrencyPairRates(currencyPair);
    }




}

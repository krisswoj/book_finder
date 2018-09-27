package pl.krzysiek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.currency_api.CurrencyApi;
import pl.krzysiek.repository.ICurrencyRepository;
import pl.krzysiek.domain.Currency;

import java.io.IOException;
import java.util.*;

@Service
public class CurrencyService {

    @Autowired
    private ReaderXMLFilesService readerXMLFilesService;
    @Autowired
    private ICurrencyRepository currencyRepository;
    @Autowired
    private CurrencyApi currencyApi;

    public void updateSingleCurrencyPair(String currencyPair) throws IOException {
        currencyRepository.save(currencyApi.getActuallyRate(currencyPair));
    }

    public List<Currency> allCurrenciesFromXml() {

        final String xmlPath  = "xml_files/currency_list.xml";
        final String xmlId = "currency";

        List<Currency> currencyList = new ArrayList<>();
        List<ArrayList<String>> xmlData = readerXMLFilesService.readXMLFiles(xmlPath, xmlId);

        for (ArrayList<String> position : xmlData) {
            Currency currency = new Currency(position.get(0) + "_" + position.get(1), position.get(0), position.get(1));
            currencyList.add(currency);
        }
        return currencyList;
    }

    public List<List<Currency>> lastXRatesAllCurrency(Integer recordAmount) {

        List<List<Currency>> mainList = new ArrayList<>();

        for (Currency currency : this.allCurrenciesFromXml()) {
            List<Currency> currencyList = currencyRepository.lastCurrencyPairRates(currency.getPairName(), recordAmount);
            mainList.add(currencyList);
        }
        return mainList;
    }

    public List<Currency> lastAllCurrencyRates() {

        List<Currency> currencyList = new ArrayList<>();
        for (Currency currency : this.allCurrenciesFromXml())
            currencyList.add(currencyRepository.lastSingleCurrencyPairRates(currency.getPairName()));

        return currencyList;
    }

    public List<Currency> updateRateAllCurrency() throws IOException {
        List<Currency> currentRates = new ArrayList<>();

        for (Currency currency : this.allCurrenciesFromXml()) {
            currentRates.add(currencyApi.getActuallyRate(currency.getPairName()));
            currencyRepository.save(currentRates.get(currentRates.size() - 1));
        }
        return currentRates;
    }

    public Map<String, Currency> currencyMap() {
        Map<String, Currency> currencyMap = new HashMap<>();
        for (Currency currency : this.lastAllCurrencyRates())
            currencyMap.put(currency.getPairName(), currency);

        return currencyMap;
    }

    //In current version, for everthing to work correctly - the PLN currency must be the second. Example: EUR_PLN, USD_PLN.
    public String getCurrencyPair(String fromCurrency, String toCurrency) {
        if (!fromCurrency.equals("PLN"))
            return (fromCurrency + "_" + toCurrency);
        else
            return (toCurrency + "_" + fromCurrency);

    }

    private Double rateValue(String currencyPair) {
        return currencyRepository.lastSingleCurrencyPairRates(currencyPair).getRateValue();
    }

    private Double currencyDivision(Double currencyValue, String currencyPair, Double feeRate) {
        return (currencyValue / (rateValue(currencyPair) * feeRate));
    }

    private Double currencyMultiplication(Double currencyValue, String currencyPair, Double feeRate) {
        return (currencyValue * rateValue(currencyPair) * feeRate);
    }

    private Double currencyWithFee(String currencyPair, Double feeRate) {
        return (rateValue(currencyPair) * feeRate);
    }


    private Double currencyDivisionFee(Double currencyValue, String currencyPair, Double feeRate) {
        return ((currencyValue / (rateValue(currencyPair))) - (currencyValue / (rateValue(currencyPair) * feeRate)));
    }

    private Double currencyMultiplicationFee(Double currencyValue, String currencyPair, Double feeRate) {
        return ((currencyValue * (rateValue(currencyPair) * feeRate))) - currencyValue * rateValue(currencyPair);
    }
}
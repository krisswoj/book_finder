package pl.krzysiek.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class CurrencyExchangeController {

    @RequestMapping(value = "/user/currency-exchange", method = RequestMethod.GET)
    public ModelAndView buySellCurrencyView(){
        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

    @RequestMapping(value = "/user/currency-exchange", method = RequestMethod.POST)
    public ModelAndView verifyCurrencyExchange() throws IOException {
        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }
}

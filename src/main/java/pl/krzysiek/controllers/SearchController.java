package pl.krzysiek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.krzysiek.domain.Book;
import pl.krzysiek.services.AllegroServices;
import pl.krzysiek.services.SearchBookService;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchBookService searchBookService;

    @GetMapping(value = "/")
    public ModelAndView addFood() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book_view", new Book());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping(value = "/")
    public ModelAndView addFood(@Valid Book book) throws IOException, URISyntaxException {
        ModelAndView modelAndView = new ModelAndView();
        searchBookService.findBook(book.getTitle());
        modelAndView.addObject("book_view", new Book());
        modelAndView.addObject("book_list", searchBookService.findBook(book.getTitle()));
        modelAndView.setViewName("home");
        return modelAndView;
    }

}

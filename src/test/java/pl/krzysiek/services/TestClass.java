package pl.krzysiek.services;

import org.junit.Test;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.krzysiek.api.google_books_api.GoogleBooksApi;
import pl.krzysiek.api.google_books_api.domain.GoogleBooksResponse;
import pl.krzysiek.configuration.RestConfiguration;
import pl.krzysiek.configuration.RestInitializer;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestConfiguration.class, RestInitializer.class})
public class TestClass {



    @Autowired
    private GoogleBooksApi googleBooksApi;

    @Autowired
    HelionPriceCheck helionPriceCheck;

    @Autowired
    BookBookPriceCheck bookBookPriceCheck;



    @Test
    public void testBook() throws IOException{

        GoogleBooksResponse googleBooksResponse = googleBooksApi.getBookList();

    }

    @Test
    public void jsoupTest() throws IOException {

//        helionPriceCheck.helionCheckPrice();

        bookBookPriceCheck.bookBookCheckPrice();
    }

}















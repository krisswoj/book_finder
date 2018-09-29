package pl.krzysiek.services;

import com.google.gson.Gson;
import org.hibernate.validator.constraints.EAN;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.krzysiek.api.allegro_api.AllegroApiResponeAuction;
import pl.krzysiek.api.google_books_api.GoogleBooksResponse;
import pl.krzysiek.configuration.RestConfiguration;
import pl.krzysiek.configuration.RestInitializer;
import pl.krzysiek.domain.Book;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestConfiguration.class, RestInitializer.class})
public class TestClass {



    @Autowired
    private GoogleBooksService googleBooksService;

    @Autowired
    HelionService helionService;

    @Autowired
    BookBookService bookBookService;

    @Autowired
    AllegroServices allegroServices;

    @Autowired
    ConverterService converterService;

    @Autowired
    SearchBookService searchBookService;


    private final Gson gson = new Gson();



//    @Test
//    public void testBook() throws IOException, URISyntaxException {
//
//        GoogleBooksResponse googleBooksResponse = googleBooksService.findBook();
//        googleBooksResponse.getItems().size();
//
//    }
//
//    @Test
//    public void jsoupTest() throws IOException {
//
////        helionPriceCheck.helionCheckPrice();
//
//        bookBookPriceCheck.bookBookCheckPrice();
//    }
//
//    @Test
//    public void saveAllegroApi(){
//
//        String allegroApi = "{\"access_token\":\"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzgxMTkyMzUsInVzZXJfbmFtZSI6IjEzODU3NjA5IiwianRpIjoiY2NlZDgzZjctNDQ0Ny00MTc2LWIwMjQtMWRmOTU1NDZmZmQ0IiwiY2xpZW50X2lkIjoiNDAwODM1ZGNlNTVjNDVjMjhkZjJmNmVlMTE1NTFiMGMiLCJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdfQ.A8nMrgIrRBQ0-64OwDxfJB2gbIOnkt5PcfhKwtANlKqnsynrnKuQxiPzKFSrFfogmse0XuH7XTmfNs7Ht7nQyKxFoKc_ZfaVGb3q5Q4USXWw0XjOGjDUlzFdpETKR6bGtIsNhUuoCoRC8cGnVpjIqRewkIFHWAHTmLGFHYqHaYnhUKtzVUPjLbp3EFN4jWkOhtJHmybAmibThLbo2JHEMeQtAL0avlbvKHtg2JBDcIu8K5X2ayvez4plB4LNYmSrSvs67vK3ZAzBc1OmWlD8fYdA1DvCIxWKUYfwsmKDA9DrUiFNohX9MyKTlbp1QcsGoKMHiSaHJ6RYQtrqAFuV6g\",\"token_type\":\"bearer\",\"refresh_token\":\"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njk2MTIwMzUsInVzZXJfbmFtZSI6IjEzODU3NjA5IiwianRpIjoiZTYxZjQ4ZGUtMzM3Ni00ZjZhLTgxOTQtMWU5MmViMzRmYWIwIiwiY2xpZW50X2lkIjoiNDAwODM1ZGNlNTVjNDVjMjhkZjJmNmVlMTE1NTFiMGMiLCJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJhdGkiOiJjY2VkODNmNy00NDQ3LTQxNzYtYjAyNC0xZGY5NTU0NmZmZDQifQ.yPHC4fIWkL2DgMd8oGyVJxNgPo-VtHqIGKVppAiK3z8Sjxdw3ZxTFvFYSKi5BSAqKm-qFhYQb2AzVsJ-UsO9UrCW6DCMXO_-vjSlwt52MnFv3rXIfJqZ8CPZGaXPKCJgj6xvMgq-bfXMiZaihVEASB3ckUu5k4vKwPOii1NPCPEWP4TupKvc9e_d3A29wUezSiTrFCsIPRhd0vgTM6GksgkO8HY4kNKW8rpI5tPk2A_wZRhShLYwccS7juN5C99OSoEXrXN0F1uGmk7x-s5LqoM5HQxa0DbWfeY9YKx-JkyAGMHnUtnm4VtXilznoB_KRu47KKiSK4XouvK2QRY67g\",\"expires_in\":43199,\"scope\":\"allegro_api\",\"jti\":\"cced83f7-4447-4176-b024-1df95546ffd4\"}";
//
//        String allegroApi2 = "{\"access_token\":\"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzgxNjUwMjksInVzZXJfbmFtZSI6IjEzODU3NjA5IiwianRpIjoiYjMwOGZmZmItZTA4Mi00NDU2LTg0ZTMtYTE0NDY2ZWViMDcyIiwiY2xpZW50X2lkIjoiNDAwODM1ZGNlNTVjNDVjMjhkZjJmNmVlMTE1NTFiMGMiLCJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdfQ.Yf2ZrmPzg4Y0dTIgzf-e3EC-fXOjDMCjon3hApIdzh7ku3OtbCQRn72SYC6Vz5UL99V9hIQ2QNmq7YOkmDXbjrDAcO-7zFpc2HLdnQy25BLmlCqKQQtvZ_gMVz479Y_Do6O5LTyB3bN2hM-8BoBOVqrQs1oPDvZeN39TtQ2JrMAl0_Xnfh9govQZheSRHFF3xd4XN1oVanKH9u13AM7mcYP1UrN4FNtga6b6z6ARUGZ8kZI8ByI3sfmb07y6DWcqRcNgFfpK4qRRIHXWvYpzyK3eKBnrMngKqgQCjWK40r47uMPbcIkLYyfstamlTfkZCkEcxDlucjGo2V6_MO3J1Q\",\"token_type\":\"bearer\",\"refresh_token\":\"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njk2NTc4MjksInVzZXJfbmFtZSI6IjEzODU3NjA5IiwianRpIjoiNTEwZTQ1ZWEtODhjNS00M2FjLTk5Y2QtYWMzYWJkN2Q4MDAyIiwiY2xpZW50X2lkIjoiNDAwODM1ZGNlNTVjNDVjMjhkZjJmNmVlMTE1NTFiMGMiLCJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJhdGkiOiJiMzA4ZmZmYi1lMDgyLTQ0NTYtODRlMy1hMTQ0NjZlZWIwNzIifQ.cKNzwSFUPz_mrpBXNDF3xxmX3Kl4ZbcNvBBUTwA61J2G-uTjaXDnSPmE40SHHgV4Fah1mluB6WpIR9QtvuYlldPfYYgIGicDnn1LvkV_uDPIxyFBnfOn-bRSMMNUpHnx0XAvYBV3K53IhmUoGZDEwy6IeNsE8x9Qdd6HhLa7NyVLqWciOgJ4Ax_Ii7Ir3Ic6LaOeprhDaJa7rw_EO815lQ3m3g3znHW8dbci9VWK6XBkHGzMXlIX15rmxT2MPfHhrbDlMiR6CyEPbTgmTFY4FrZjw_jRwUTXpNVoqbshCGliQ-QvinHiCE174uFXP5SaizOl5qb4U7tVPz1ZsShVQQ\",\"expires_in\":43199,\"scope\":\"allegro_api\",\"jti\":\"b308fffb-e082-4456-84e3-a14466eeb072\"}";
//
//        AllegroToken allegroResponseToken = gson.fromJson(allegroApi2, AllegroToken.class);
//        allegroServices.saveToken(allegroResponseToken);
//
//    }
//
//    @Test
//    public void testLastApi(){
//
//        String result = allegroServices.allegroToken();
//        System.out.println(result);
//
//    }


    @Test
    public void wordConverter() throws IOException, URISyntaxException {

//        GoogleBooksResponse googleBooksResponse = googleBooksService.findBook("harry potter");
//        googleBooksResponse.getItems();

//        System.out.println("Auth url: " + allegroServices.allegroAuthUrl());

    }

    @Test
    public void alleroTest() throws IOException, URISyntaxException {

        AllegroApiResponeAuction allegroApiResponeAuction = allegroServices.allegroAuctionRespone("365 dni");
        allegroApiResponeAuction.getCategories();
    }

    @Test
    public void testBookSearch() throws IOException, URISyntaxException {
//        Book book = searchBookService.findBook("Język C++. Szkoła programowania. Wydanie VI");
//        book.equals("dupa");

//        String wynikconwe2w = converterService.searchQueryConverter("Harry Potter i przeklęte dziecko J. K.");
//        System.out.println(wynikconwe2w);
//
//        GoogleBooksResponse googleBooksResponse = googleBooksService.findBook("Harry Potter i przeklęte dziecko J. K.");
//        googleBooksResponse.getItems();

        List<Book> bookList = searchBookService.findBook("Harry Potter i przeklęte dziecko J. K.");
        bookList.size();

    }

}















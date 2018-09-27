package pl.krzysiek.services;

import com.google.gson.Gson;
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
import pl.krzysiek.domain.AllegroResponseApi;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestConfiguration.class, RestInitializer.class})
public class TestClass {



    @Autowired
    private GoogleBooksApi googleBooksApi;

    @Autowired
    HelionPriceCheck helionPriceCheck;

    @Autowired
    BookBookPriceCheck bookBookPriceCheck;

    @Autowired
    AllegroServices allegroServices;

    private final Gson gson = new Gson();



    @Test
    public void testBook() throws IOException{

        GoogleBooksResponse googleBooksResponse = googleBooksApi.getBookList();

    }

    @Test
    public void jsoupTest() throws IOException {

//        helionPriceCheck.helionCheckPrice();

        bookBookPriceCheck.bookBookCheckPrice();
    }

    @Test
    public void saveAllegroApi(){

        String allegroApi = "{\"access_token\":\"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzgxMTkyMzUsInVzZXJfbmFtZSI6IjEzODU3NjA5IiwianRpIjoiY2NlZDgzZjctNDQ0Ny00MTc2LWIwMjQtMWRmOTU1NDZmZmQ0IiwiY2xpZW50X2lkIjoiNDAwODM1ZGNlNTVjNDVjMjhkZjJmNmVlMTE1NTFiMGMiLCJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdfQ.A8nMrgIrRBQ0-64OwDxfJB2gbIOnkt5PcfhKwtANlKqnsynrnKuQxiPzKFSrFfogmse0XuH7XTmfNs7Ht7nQyKxFoKc_ZfaVGb3q5Q4USXWw0XjOGjDUlzFdpETKR6bGtIsNhUuoCoRC8cGnVpjIqRewkIFHWAHTmLGFHYqHaYnhUKtzVUPjLbp3EFN4jWkOhtJHmybAmibThLbo2JHEMeQtAL0avlbvKHtg2JBDcIu8K5X2ayvez4plB4LNYmSrSvs67vK3ZAzBc1OmWlD8fYdA1DvCIxWKUYfwsmKDA9DrUiFNohX9MyKTlbp1QcsGoKMHiSaHJ6RYQtrqAFuV6g\",\"token_type\":\"bearer\",\"refresh_token\":\"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njk2MTIwMzUsInVzZXJfbmFtZSI6IjEzODU3NjA5IiwianRpIjoiZTYxZjQ4ZGUtMzM3Ni00ZjZhLTgxOTQtMWU5MmViMzRmYWIwIiwiY2xpZW50X2lkIjoiNDAwODM1ZGNlNTVjNDVjMjhkZjJmNmVlMTE1NTFiMGMiLCJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJhdGkiOiJjY2VkODNmNy00NDQ3LTQxNzYtYjAyNC0xZGY5NTU0NmZmZDQifQ.yPHC4fIWkL2DgMd8oGyVJxNgPo-VtHqIGKVppAiK3z8Sjxdw3ZxTFvFYSKi5BSAqKm-qFhYQb2AzVsJ-UsO9UrCW6DCMXO_-vjSlwt52MnFv3rXIfJqZ8CPZGaXPKCJgj6xvMgq-bfXMiZaihVEASB3ckUu5k4vKwPOii1NPCPEWP4TupKvc9e_d3A29wUezSiTrFCsIPRhd0vgTM6GksgkO8HY4kNKW8rpI5tPk2A_wZRhShLYwccS7juN5C99OSoEXrXN0F1uGmk7x-s5LqoM5HQxa0DbWfeY9YKx-JkyAGMHnUtnm4VtXilznoB_KRu47KKiSK4XouvK2QRY67g\",\"expires_in\":43199,\"scope\":\"allegro_api\",\"jti\":\"cced83f7-4447-4176-b024-1df95546ffd4\"}";

        AllegroResponseApi allegroResponseApi = gson.fromJson(allegroApi, AllegroResponseApi.class);
        allegroServices.save(allegroResponseApi);

    }

}















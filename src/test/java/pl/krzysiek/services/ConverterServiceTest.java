package pl.krzysiek.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.krzysiek.configuration.RestConfiguration;
import pl.krzysiek.configuration.RestInitializer;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestConfiguration.class, RestInitializer.class})
public class ConverterServiceTest {

    @Autowired
    ConverterService converterService;

    @Test
    public void priceConverterTest() throws IOException {

        String pricetoCheck = "134,87";
        Double priceToVerify = 134.87;
        assertEquals(priceToVerify, converterService.priceConventer(pricetoCheck));
    }

    @Test
    public void searchQueryTest(){
        String query="Harry potter i zakon feniksa";
        String queryAfter = "Harry+potter+i+zakon+feniksa+";

        assertEquals(queryAfter, converterService.searchQueryConverter(query));
    }

    @Test
    public void removeAccents(){
        String wordWithPolishLetters = "ąężźć";
        String without = "aezzc";
        assertEquals(without, converterService.removeAccents(wordWithPolishLetters));
    }



}

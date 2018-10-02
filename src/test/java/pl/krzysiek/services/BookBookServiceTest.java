package pl.krzysiek.services;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.krzysiek.configuration.RestConfiguration;
import pl.krzysiek.configuration.RestInitializer;
import pl.krzysiek.domain.CompareBookPrices;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestConfiguration.class, RestInitializer.class})
public class BookBookServiceTest {

    @Autowired
    BookBookService bookBookService;

    @Autowired
    BookBookService bookBookServiceMock;

    private Gson gson = new Gson();

    @Before
    public void setUp(){
        bookBookServiceMock = mock(BookBookService.class);
    }

    @Test
    public void bookCheckPriceTest() throws IOException {

        CompareBookPrices compareBookPrices = new CompareBookPrices();
        compareBookPrices.setStoreName("Allegro");
        compareBookPrices.setPrice(20.0);

        when(bookBookServiceMock.bookBookCheckPrice(anyString())).thenReturn(compareBookPrices);
        CompareBookPrices cbp = bookBookServiceMock.bookBookCheckPrice("harry potter");
        assertEquals(cbp, compareBookPrices);

        verify(bookBookServiceMock, times(1)).bookBookCheckPrice(anyString());
    }
}

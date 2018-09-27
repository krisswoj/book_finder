package pl.krzysiek.api.google_books_api;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.google_books_api.domain.GoogleBooksResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class GoogleBooksApi {

    private final Gson gson = new Gson();

    public GoogleBooksApi() {
    }

    public GoogleBooksResponse getBookList() throws IOException{

        HttpURLConnection connection = (HttpURLConnection) new URL("https://www.googleapis.com/books/v1/volumes?q=harry+potter").openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");

        String jsonString = readStream(connection.getInputStream());

        GoogleBooksResponse googleBooksResponse = gson.fromJson(jsonString, GoogleBooksResponse.class);

        return googleBooksResponse;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}

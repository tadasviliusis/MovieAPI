package lt.viko.eif.groupproject.movieapi.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MovieTitleAPI {
    private static final String API_KEY = "e654497f20msh242b428e7b5e43fp130701jsnaea63401a3ed";
    private static final String HOST = "moviesdatabase.p.rapidapi.com";

    private OkHttpClient client;

    public MovieTitleAPI() {
        this.client = new OkHttpClient();
    }

    public String fetchMovie(String movieTitle) throws IOException {
        Request request = new Request.Builder()
                .url("https://moviesdatabase.p.rapidapi.com/titles/search/keyword/" + movieTitle)
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", HOST)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}

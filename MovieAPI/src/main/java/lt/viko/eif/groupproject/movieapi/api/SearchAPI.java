package lt.viko.eif.groupproject.movieapi.api;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class SearchAPI {

    private static final String API_KEY = "e654497f20msh242b428e7b5e43fp130701jsnaea63401a3ed";
    private static final String HOST = "moviesdatabase.p.rapidapi.com";

    public static String searchTitles(String title, String page) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://moviesdatabase.p.rapidapi.com/titles/search/title/" + title).newBuilder();
        urlBuilder.addQueryParameter("page", page);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", HOST)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


}

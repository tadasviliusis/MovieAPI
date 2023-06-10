package lt.viko.eif.groupproject.movieapi.api;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TitlesAPI {
    private static final String API_KEY = "e654497f20msh242b428e7b5e43fp130701jsnaea63401a3ed";
    private static final String HOST = "moviesdatabase.p.rapidapi.com";


    public static String getTitleById(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://moviesdatabase.p.rapidapi.com/titles/" + id)
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", HOST)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return json;
        }
    }

    public static String getTitleRating(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://moviesdatabase.p.rapidapi.com/titles/" + id + "/ratings")
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", HOST)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return json;
        }
    }

    public static String getTitleActors(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://moviesminidatabase.p.rapidapi.com/movie/id/" + id + "/cast/")
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", "moviesminidatabase.p.rapidapi.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return json;
        }
    }


    public static String getMovieUserReviews(String id) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://online-movie-database.p.rapidapi.com/title/get-user-reviews").newBuilder();
        urlBuilder.addQueryParameter("tconst", id);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", "online-movie-database.p.rapidapi.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return json;
        }
    }
}

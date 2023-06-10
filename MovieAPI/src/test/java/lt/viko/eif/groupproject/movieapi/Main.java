package lt.viko.eif.groupproject.movieapi;

import lt.viko.eif.groupproject.movieapi.model.Movie;
import lt.viko.eif.groupproject.movieapi.repository.MovieRepo;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, String> movieList = MovieRepo.searchMovieByTitle("Avatar");
        for (Map.Entry<String, String> entry : movieList.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }
}

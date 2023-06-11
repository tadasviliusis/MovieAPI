package lt.viko.eif.groupproject.movieapi.controller;

import lt.viko.eif.groupproject.movieapi.repository.MovieReviewRepo;
import lt.viko.eif.groupproject.movieapi.repository.UserRepo;
import lt.viko.eif.groupproject.movieapi.repository.WatchlistRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@RestController
public class MovieController {

    private final UserRepo userRepo;
    private final MovieReviewRepo reviewRepo;
    private final WatchlistRepo watchlistRepo;

    public MovieController(UserRepo userRepo, MovieReviewRepo reviewRepo, WatchlistRepo watchlistRepo){
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
        this.watchlistRepo = watchlistRepo;
    }

    @GetMapping("/movies")
    String mainMethod() throws IOException {
        return "WebService for movies.";
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) throws IOException {
        Movie movie = MovieRepo.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movies/search/{title}")
    public ResponseEntity<Map<String, String>> searchMovies(@PathVariable String title) throws IOException {
        Map<String, String> result = MovieRepo.searchMovieByTitle(title);
        if (result != null && !result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movie/{id}/cast")
    public ResponseEntity<Map<String,  Map<String, String>>> getMovieActors(@PathVariable String id) throws IOException {
        Map<String,  Map<String, String>> result = MovieRepo.getMovieCast(id);
        if (result != null && !result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movie/{id}/reviews")
    public ResponseEntity<List<MovieReview>> getMovieReviews(@PathVariable String id) throws IOException, ParseException {
        List<MovieReview> result = MovieRepo.getMovieUserReviews(id);
        if (result != null && !result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //get
    @GetMapping("/{userId}/watchlist")
    public ResponseEntity<Map<String,String>> getUserWatchlist(@PathVariable long userId){
        List<Watchlist> list = watchlistRepo.findAllByCurrentUser(userId);
        Map<String,String> map = new HashMap<>();
        for (Watchlist watchlist:list) {
            map.put(watchlist.getMovieId(),watchlist.getMovieName());
        }
        if (map != null && !map.isEmpty()) {
            return ResponseEntity.ok(map);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //add
    @PostMapping("/{userId}/add/{movieId}")
    Watchlist addMovieToWatchlist(@PathVariable long userId, @PathVariable String movieId) throws IOException {
        if(userRepo.findById(userId).isPresent()){
            Movie movie = MovieRepo.getMovieById(movieId);
            Watchlist newWatchlistItem = new Watchlist(movieId, movie.getTitleText(), userRepo.getReferenceById(userId));

            return watchlistRepo.save(newWatchlistItem);
        }
        return null;
    }

    //delete
    @DeleteMapping("/{userId}/delete/{movieId}")
    public void deleteWatchlistItem(@PathVariable long userId, @PathVariable String movieId){
        watchlistRepo.findByUserIDAndMovieID(userId,movieId);
    }

}

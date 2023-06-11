package lt.viko.eif.groupproject.movieapi.controller;

import lt.viko.eif.groupproject.movieapi.repository.MovieReviewRepo;
import lt.viko.eif.groupproject.movieapi.repository.UserRepo;
import lt.viko.eif.groupproject.movieapi.model.*;
import lt.viko.eif.groupproject.movieapi.repository.MovieRepo;
import lt.viko.eif.groupproject.movieapi.repository.WatchlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@RestController
public class MovieController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MovieReviewRepo reviewRepo;
    @Autowired
    private WatchlistRepo watchlistRepo;


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

    @GetMapping("/movies/{id}/cast")
    public ResponseEntity<Map<String,  Map<String, String>>> getMovieActors(@PathVariable String id) throws IOException {
        Map<String,  Map<String, String>> result = MovieRepo.getMovieCast(id);
        if (result != null && !result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movies/{id}/reviews")
    public ResponseEntity<List<MovieReview>> getMovieReviews(@PathVariable String id) throws IOException, ParseException {
        List<MovieReview> result = MovieRepo.getMovieUserReviews(id);
        if (result != null && !result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/movies/{movieId}/user/{userId}/reviews/newReview")
    public ResponseEntity<Boolean> createOurUserNewReview(@RequestBody MovieReview newReview, @PathVariable String movieId, @PathVariable Long userId)
    {
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent())
        {
            newReview.setAuthor(optionalUser.get());
            reviewRepo.save(newReview);
            return ResponseEntity.ok(true);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/movies/user/{Id}/reviews")
    public ResponseEntity<List<MovieReview>> getOurUserReviewsByUser(@PathVariable Long Id)
    {
        return ResponseEntity.ok(reviewRepo.findAllReviewsByCurrentUser(Id));
    }

    @GetMapping("/movies/{Id}/user/reviews")
    public ResponseEntity<List<MovieReview>> getOurUserReviewsByMovie(@PathVariable String Id)
    {
        return ResponseEntity.ok(reviewRepo.findAllReviewsByMovie(Id));
    }

    @DeleteMapping("/movies/user/reviews/{Id}")
    public ResponseEntity<Boolean> deleteOurUserReviewById(@PathVariable Long Id)
    {
        if(!reviewRepo.findById(Id).isPresent())
            return ResponseEntity.notFound().build();
        reviewRepo.deleteReviewById(Id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/movies/user/{Id}/oneReview")
    public ResponseEntity<MovieReview> getOurUserOneReviewById(@PathVariable Long Id)
    {
        Optional<MovieReview> optionalReview = reviewRepo.findById(Id);
        return optionalReview.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/movies/user/reviews/{Id}")
    public ResponseEntity<ReviewDTO> changeOurUserReviewInfoById(@RequestBody ReviewDTO newReview, @PathVariable Long Id)
    {
        if(!reviewRepo.findById(Id).isPresent())
            return ResponseEntity.notFound().build();
        reviewRepo.updateReviewById(newReview.getTitle(),newReview.getText(), newReview.getIsSpoiler(), new Date(), Id);
        return ResponseEntity.ok(newReview);
    }

    @PostMapping("/movies/user/newUser")
    public ResponseEntity<User> createNewUser(@RequestBody UserDTO newUser){
        return ResponseEntity.ok(userRepo.save(new User(newUser.getUsername(), newUser.getPassword())));
    }

    @GetMapping("/movies/user/{Id}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable Long Id){
        Optional<User> optionalUser = userRepo.findById(Id);
        if(optionalUser.isPresent())
            return ResponseEntity.ok(new UserDTO(optionalUser.get().getUsername(), optionalUser.get().getPassword()));
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/movies/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepo.findAll());
    }

    @PutMapping("/movies/user/{Id}")
    public ResponseEntity<UserDTO> changeUserInfo(@RequestBody UserDTO newUser, @PathVariable Long Id)
    {
        if(!userRepo.findById(Id).isPresent())
            return ResponseEntity.notFound().build();
        userRepo.updateUserById(newUser.getUsername(), newUser.getPassword(), Id);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/movies/user/{Id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long Id)
    {
        if(!userRepo.findById(Id).isPresent())
            return ResponseEntity.notFound().build();
        userRepo.deleteUserById(Id);
        return ResponseEntity.ok(true);
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
package lt.viko.eif.groupproject.movieapi.controller;

import lt.viko.eif.groupproject.movieapi.database.MovieReviewRepo;
import lt.viko.eif.groupproject.movieapi.database.UserRepo;
import lt.viko.eif.groupproject.movieapi.model.*;
import lt.viko.eif.groupproject.movieapi.repository.MovieRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MovieController {

    private final UserRepo userRepo;
    private final MovieReviewRepo reviewRepo;

    public MovieController(UserRepo userRepo, MovieReviewRepo reviewRepo){
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
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

}

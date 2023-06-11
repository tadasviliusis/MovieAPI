package lt.viko.eif.groupproject.movieapi.repository;

import jakarta.transaction.Transactional;
import lt.viko.eif.groupproject.movieapi.model.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

public interface MovieReviewRepo extends JpaRepository<MovieReview, Long> {
    @Query("FROM MovieReview where author.id = :userId")
    List<MovieReview> findAllReviewsByCurrentUser(@Param("userId") Long userId);

    @Query("FROM MovieReview where movie = :movie")
    List<MovieReview> findAllReviewsByMovie(@Param("movie") String userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM movie_review WHERE id = :Id", nativeQuery = true)
    void deleteReviewById(@Param("Id") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE movie_review SET title = :newTitle, text = :newText, is_spoiler = :newSpoiler, submission_date = :newDate WHERE id = :reviewId", nativeQuery = true)
    void updateReviewById(@Param("newTitle") String newTitle, @Param("newText") String newText,
                          @Param("newSpoiler") boolean newSpoiler, @Param("newDate") Date newDate, @Param("reviewId") Long reviewId);

}
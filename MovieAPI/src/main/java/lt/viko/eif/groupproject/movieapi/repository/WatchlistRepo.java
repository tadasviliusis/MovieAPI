package lt.viko.eif.groupproject.movieapi.repository;

import jakarta.transaction.Transactional;
import lt.viko.eif.groupproject.movieapi.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface WatchlistRepo extends JpaRepository<Watchlist, Long> {
    @Query("FROM Watchlist g where g.user.id = :userId")
    List<Watchlist> findAllByCurrentUser(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE From moviedb.Watchlist where moviedb.Watchlist.user_id = :userId and moviedb.Watchlist.movie_id = :movieId", nativeQuery = true)
    void findByUserIDAndMovieID(@Param("userId") long userId, @Param("movieId") String movieId);
}

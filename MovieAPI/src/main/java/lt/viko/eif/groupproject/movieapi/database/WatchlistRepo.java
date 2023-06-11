package lt.viko.eif.groupproject.movieapi.database;

import lt.viko.eif.groupproject.movieapi.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface WatchlistRepo extends JpaRepository<Watchlist, Long> {
}

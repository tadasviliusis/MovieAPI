package lt.viko.eif.groupproject.movieapi.database;

import lt.viko.eif.groupproject.movieapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}

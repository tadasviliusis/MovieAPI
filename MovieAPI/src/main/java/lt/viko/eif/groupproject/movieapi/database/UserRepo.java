package lt.viko.eif.groupproject.movieapi.database;

import jakarta.transaction.Transactional;
import lt.viko.eif.groupproject.movieapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM User WHERE id = :Id", nativeQuery = true)
    void deleteUserById(@Param("Id") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User u SET u.username = :newUsername, u.password = :newPassword WHERE u.id = :userId", nativeQuery = true)
    void updateUserById(@Param("newUsername") String newUsername, @Param("newPassword") String newPassword, @Param("userId") Long userId);
}

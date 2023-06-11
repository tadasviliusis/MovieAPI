package lt.viko.eif.groupproject.movieapi.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieId;
    private String movieName;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Watchlist() {

    }

    public Watchlist(int id, String movieId, String movieName, User user) {
        this.id = id;
        this.movieId = movieId;
        this.movieName = movieName;
        this.user = user;
    }

    public Watchlist(String movieId, String movieName, User user) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Watchlist{" +
                "id=" + id +
                ", movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watchlist watchlist = (Watchlist) o;
        return id == watchlist.id && movieId.equals(watchlist.movieId) && movieName.equals(watchlist.movieName) && user.equals(watchlist.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, movieName, user);
    }
}

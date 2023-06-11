package lt.viko.eif.groupproject.movieapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MovieReview {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User author;
    private String movie;
    private String title;
    private String text;
    private boolean isSpoiler;
    private Date submissionDate;

    public MovieReview() {
    }

    public MovieReview(Long id, User author, String movie, String title, String text, boolean isSpoiler, Date submissionDate) {
        this.id = id;
        this.author = author;
        this.movie = movie;
        this.title = title;
        this.text = text;
        this.isSpoiler = isSpoiler;
        this.submissionDate = submissionDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSpoiler(boolean spoiler) {
        isSpoiler = spoiler;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }
    public String getMovie() {
        return movie;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean getIsSpoiler() {
        return isSpoiler;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }
}

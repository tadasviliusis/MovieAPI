package lt.viko.eif.groupproject.movieapi.model;

import java.util.Date;

public class MovieReview {
    private String id;
    private String author;
    private String title;
    private String text;
    private boolean isSpoiler;
    private Date submissionDate;

    public MovieReview() {
    }

    public MovieReview(String id, String author, String title, String text, boolean isSpoiler, Date submissionDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.isSpoiler = isSpoiler;
        this.submissionDate = submissionDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
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

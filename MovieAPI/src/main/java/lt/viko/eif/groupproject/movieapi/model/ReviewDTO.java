package lt.viko.eif.groupproject.movieapi.model;

import java.util.Date;

public class ReviewDTO {
    private String title;
    private String text;
    private boolean isSpoiler;
    private Date submissionDate;

    public ReviewDTO() {
    }

    public ReviewDTO(String title, String text, boolean isSpoiler, Date submissionDate) {
        this.title = title;
        this.text = text;
        this.isSpoiler = isSpoiler;
        this.submissionDate = submissionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsSpoiler() {
        return isSpoiler;
    }

    public void setSpoiler(boolean spoiler) {
        isSpoiler = spoiler;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }
}

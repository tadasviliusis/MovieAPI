package lt.viko.eif.groupproject.movieapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Movie {
    private String id;
    private String titleType;
    private String titleText;
    private Integer beginYear;
    private Integer endYear;
    private Date releaseDate;
    private float averageRating;

    public Movie(String id, String titleType, String titleText, Integer beginYear, Integer endYear, Date releaseDate, float averageRating) {
        this.id = id;
        this.titleType = titleType;
        this.titleText = titleText;
        this.beginYear = beginYear;
        this.endYear = endYear;
        this.releaseDate = releaseDate;
        this.averageRating = averageRating;
    }

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String titleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String titleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public Integer beginYear() {
        return beginYear;
    }

    public void setBeginYear(int beginYear) {
        this.beginYear = beginYear;
    }

    public Integer endYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Date releaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float averageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }
}

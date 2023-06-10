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

    public Movie() {
    }

    public Movie(String id, String titleType, String titleText, Integer beginYear, Integer endYear, Date releaseDate, float averageRating) {
        this.id = id;
        this.titleType = titleType;
        this.titleText = titleText;
        this.beginYear = beginYear;
        this.endYear = endYear;
        this.releaseDate = releaseDate;
        this.averageRating = averageRating;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public void setBeginYear(Integer beginYear) {
        this.beginYear = beginYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public String getId() {
        return id;
    }

    public String getTitleType() {
        return titleType;
    }

    public String getTitleText() {
        return titleText;
    }

    public Integer getBeginYear() {
        return beginYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public float getAverageRating() {
        return averageRating;
    }
}

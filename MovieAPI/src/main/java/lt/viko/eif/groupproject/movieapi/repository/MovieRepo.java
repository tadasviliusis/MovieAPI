package lt.viko.eif.groupproject.movieapi.repository;

import lt.viko.eif.groupproject.movieapi.api.SearchAPI;
import lt.viko.eif.groupproject.movieapi.api.TitlesAPI;
import lt.viko.eif.groupproject.movieapi.model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;


public class MovieRepo {

    public static Movie getMovieById(String id) throws IOException {
        JSONObject tempObject = new JSONObject(TitlesAPI.getTitleById(id));
        JSONObject mainTempObject = tempObject.getJSONObject("results");

        String movieId = mainTempObject.getString("id");

        String titleText = mainTempObject.getJSONObject("originalTitleText").getString("text");
        String titleType = mainTempObject.getJSONObject("titleType").getString("text");

        JSONObject releaseYearObject = mainTempObject.getJSONObject("releaseYear");
        int beginYear = releaseYearObject.getInt("year");
        Integer endYear;
        if (!releaseYearObject.isNull("endYear"))
            endYear = releaseYearObject.getInt("endYear");
        else endYear = null;

        JSONObject dataObject = mainTempObject.getJSONObject("releaseDate");
        Calendar calendar = new GregorianCalendar(dataObject.getInt("year"), dataObject.getInt("month") - 1, dataObject.getInt("day"));
        Date releaseDate = calendar.getTime();

        JSONObject tempRatingObject = new JSONObject(TitlesAPI.getTitleRating(id));
        JSONObject mainTempRatingObject = tempRatingObject.getJSONObject("results");

        float averageRating = mainTempRatingObject.getFloat("averageRating");

        Movie movie = new Movie(movieId, titleType, titleText, beginYear, endYear, releaseDate, averageRating);
        return movie;
    }

    public static Map<String, String> searchMovieByTitle(String title) throws IOException {
        JSONArray mainObjectArr = new JSONArray();
        int page = 1;
        while (true)
        {
            String json = SearchAPI.searchTitles(title, String.valueOf(page));
            JSONObject tempObject1 = new JSONObject(json);
            JSONArray tempObjectArr = tempObject1.getJSONArray("results");
            mainObjectArr.putAll(tempObjectArr);
            if(tempObjectArr.length() != 10)
                break;
            page++;
        }

        Map<String, String> movieList = new HashMap<>();

        for(int i = 0; i < mainObjectArr.length(); i++)
        {
            String movieId = mainObjectArr.getJSONObject(i).getString("id");
            String titleText = mainObjectArr.getJSONObject(i).getJSONObject("originalTitleText").getString("text");
            if(!mainObjectArr.getJSONObject(i).isNull("releaseYear")) {
                int year = mainObjectArr.getJSONObject(i).getJSONObject("releaseYear").getInt("year");
                movieList.put(movieId, titleText + "(" + year + ")");
            }
            else movieList.put(movieId, titleText);
        }

        return movieList;
    }

}

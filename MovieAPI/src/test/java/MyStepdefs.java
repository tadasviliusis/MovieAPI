import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.groupproject.movieapi.controller.MovieController;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MyStepdefs {

    //private ResponseEntity entity;

    private Map<String,String> response;
    private Map<String,String> correctAnswer;
    MovieController movieController = new MovieController();

    @Given("Client sends a Get request with a search word")
    public void client_sends_a_get_request_with_a_search_word() throws IOException {
        response = movieController.searchMovies("Breaking Bad").getBody();
    }

    @When("Sends HTTP GET request")
    public void sends_http_get_request() throws JsonProcessingException {
        String jsonString = "{\"tt4669580\":\"Breaking Bad Movie Deal Gone Bad(2015)\",\"tt11685508\":\"Blind Wave: Breaking Bad Reaction(2018)\",\"tt9243946\":\"El Camino: A Breaking Bad Movie(2019)\",\"tt4588714\":\"Breaking Bad Clayton's Diary(2015)\",\"tt0903747\":\"Breaking Bad(2008)\",\"tt9746510\":\"Breaking Bad Wolf(2018)\",\"tt2387761\":\"Breaking Bad: Original Minisodes(2009)\",\"tt4516518\":\"Fixing Breaking Bad(2013)\",\"tt8325984\":\"Captagon: Breaking Bad Jihadists(2016)\"}";
        correctAnswer = new ObjectMapper().readValue(jsonString, HashMap.class);
    }

    @Then("User receives a list of movies")
    public void user_receives_a_list_of_movies() {
        assertEquals(correctAnswer, response);
    }

}

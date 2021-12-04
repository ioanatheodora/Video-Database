package user;

import entertainment.Season;
import fileio.*;
import video.Video;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String username;
    private Map<String, Integer> history;
    private String subscriptionType;
    private ArrayList<String> favoriteVideos;
    private Map<String, Double> Mratings = new HashMap<>();
    private Map<String,ArrayList<Double>> serialRating = new HashMap<>();
    private int ratingsGiven;

    public User(UserInputData inputData){
        username = inputData.getUsername();
        history = inputData.getHistory();
        subscriptionType = inputData.getSubscriptionType();
        favoriteVideos = inputData.getFavoriteMovies();
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteVideos;
    }

    public void setHistory(Map<String, Integer> history) {
        this.history = history;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public void setFavoriteMovies(ArrayList<String> favoriteMovies) {
        this.favoriteVideos = favoriteMovies;
    }

    public Map<String, Double> getMratings() {
        return Mratings;
    }

    public void setMratings(Map<String, Double> mratings) {
        Mratings = mratings;
    }

    public Map<String, ArrayList<Double>> getSerialRating() {
        return serialRating;
    }

    public void setSerialRating(Map<String, ArrayList<Double>> serialRating) {
        this.serialRating = serialRating;
    }

    public int getRatingsGiven() {
        return ratingsGiven;
    }

    public void setRatingsGiven(int ratingsGiven) {
        this.ratingsGiven = ratingsGiven;
    }


    public void initiateRating(String serial, int numberOfSeasons){
        ArrayList<Double> ratings = new ArrayList<>() ;
        for(int i = 0; i < numberOfSeasons; i++){
            ratings.add(0d);
        }
        serialRating.put(serial, ratings);
    }

    public void addMovieRating(String movie, double rating){
        if(Mratings.containsKey(movie)){
            Mratings.put(movie, rating);
        }
    }

    public void addSeasonRating(String serial, int season, double rating){
        if(serialRating.containsKey(serial)){
           for(Map.Entry<String, ArrayList<Double>> entry : serialRating.entrySet()){
               if(entry.getKey().equals(serial))
                   entry.getValue().add(season - 1, rating);
           }
        }
    }
}

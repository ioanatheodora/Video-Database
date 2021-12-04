package user;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class User {
    private final String username;
    private Map<String, Integer> history;
    private String subscriptionType;
    private ArrayList<String> favoriteVideos;
    private Map<String, Double> movieRatings = new HashMap<>();
    private Map<String, ArrayList<Double>> serialRating = new HashMap<>();
    private int ratingsGiven;

    public User(final UserInputData inputData) {
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

    public void setHistory(final Map<String, Integer> history) {
        this.history = history;
    }

    public void setSubscriptionType(final String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public void setFavoriteMovies(final ArrayList<String> favoriteMovies) {
        this.favoriteVideos = favoriteMovies;
    }

    public Map<String, Double> getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(final Map<String, Double> movieRatings) {
        this.movieRatings = movieRatings;
    }

    public Map<String, ArrayList<Double>> getSerialRating() {
        return serialRating;
    }

    public void setSerialRating(final Map<String, ArrayList<Double>> serialRating) {
        this.serialRating = serialRating;
    }

    public int getRatingsGiven() {
        return ratingsGiven;
    }

    public void setRatingsGiven(final int ratingsGiven) {
        this.ratingsGiven = ratingsGiven;
    }


    public void initiateRating(final String serial, final int numberOfSeasons) {
        ArrayList<Double> ratings = new ArrayList<>();
        for (int i = 0; i < numberOfSeasons; i++) {
            ratings.add(0d);
        }
        serialRating.put(serial, ratings);
    }

    public void addMovieRating(final String movie, final double rating) {
        if (movieRatings.containsKey(movie)) {
            movieRatings.put(movie, rating);
        }
    }

    public void addSeasonRating(final String serial, final int season, final double rating) {
        if (serialRating.containsKey(serial)) {
           for (Map.Entry<String, ArrayList<Double>> entry : serialRating.entrySet()) {
               if (entry.getKey().equals(serial)) {
                   entry.getValue().add(season - 1, rating);
               }
           }
        }
    }
}

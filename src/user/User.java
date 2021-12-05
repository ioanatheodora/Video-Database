package user;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String username;
    private Map<String, Integer> history;
    private final String subscriptionType;
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

    /**
     * get the username
     * @return a String - username
     */
    public String getUsername() {
        return username;
    }

    /**
     * get the user's history
     * @return A Map - history
     */
    public Map<String, Integer> getHistory() {
        return history;
    }

    /**
     * get the user's subscription type
     * @return a String - subscription Type
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * get the user's favorite movies
     * @return an ArrayList - favorite movies
     */
    public ArrayList<String> getFavoriteMovies() {
        return favoriteVideos;
    }

    /**
     * get the user's ratings for movies
     * @return a Map - ratings of movies
     */
    public Map<String, Double> getMovieRatings() {
        return movieRatings;
    }

    /**
     * get the user's ratings for shows
     * @return a Map - ratings of shows
     */
    public Map<String, ArrayList<Double>> getSerialRating() {
        return serialRating;
    }

    /**
     * get the number of ratings the user has given
     * @return int - number of ratings
     */
    public int getRatingsGiven() {
        return ratingsGiven;
    }

    /**
     * set the number of ratings the user has given
     * @param ratingsGiven the number to be set
     */
    public void setRatingsGiven(final int ratingsGiven) {
        this.ratingsGiven = ratingsGiven;
    }

    /**
     * The method initiates the ArrayList of ratings of the serial in order to access
     * a specific season anytime and adds the show to the HashMap
     * @param serial name of the show
     * @param numberOfSeasons number of seasons
     */
    public void initiateRating(final String serial, final int numberOfSeasons) {
        ArrayList<Double> ratings = new ArrayList<>();
        for (int i = 0; i < numberOfSeasons; i++) {
            ratings.add(0d);
        }
        serialRating.put(serial, ratings);
    }

    /**
     * It adds the rating of a season in the specified ArrayList of ratings
     * @param serial name of the show
     * @param season number of the season the user rated
     * @param rating rating of the season
     */
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

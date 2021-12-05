package video;



import fileio.MovieInputData;

import java.util.ArrayList;

public class Movie extends Video {
    private final int duration;
    private ArrayList<Double> ratings = new ArrayList<>();
    private double averageGrade;

    public Movie(final MovieInputData movieInput) {
        super(movieInput);
        duration = movieInput.getDuration();
    }

    /**
     * get the ratings given to the movie
     * @return an ArrayList - ratings of the movies
     */
    public ArrayList<Double> getRatings() {
        return ratings;
    }
    /**
     * get the duration of a movie
     * @return int - the duration of the movie
     */
    public int getDuration() {
        return duration;
    }

    /**
     * adds rating to the list of ratings of the movie
     * @param rating rating of the movie
     */
    public void addRating(final double rating) {
        ratings.add(rating);
    }

    /**
     * get the average garde of the movie
     * @return double - average grade of the movie
     */
    public double getAverageGrade() {
        return averageGrade;
    }

    /**
     * calculates the average rating of the movie
     */
    public void calculateAverageRating() {
        averageGrade = 0;
        if (!ratings.isEmpty()) {
            for (double i :ratings) {
                averageGrade += i;
            }
            averageGrade = averageGrade / ratings.size();
        }
    }
}

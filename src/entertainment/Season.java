package entertainment;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about a season of a tv show
 * <p>
 * DO NOT MODIFY
 */
public final class Season {
    /**
     * Number of current season
     */
    private final int currentSeason;
    /**
     * Duration in minutes of a season
     */
    private int duration;
    /**
     * List of ratings for each season
     */
    private ArrayList<Double> ratings = new ArrayList<>();
    private double averageRating;

    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    public double getAverageRating() {
        return averageRating;
    }
    /**
     * adds the rating in the list of rating
     * @param rating the rating given by the user
     */
    public void addRating(final double rating) {
        ratings.add(rating);
    }

    /**
     * calculates the average rating of the season
     */
    public void calculateAverageRating() {
        averageRating = 0;
        if (!ratings.isEmpty()) {
            for (double i : ratings) {
                averageRating += i;
            }
            averageRating = (averageRating / ratings.size());
        }
    }

    @Override
    public String toString() {
        return "Episode{"
                + "currentSeason="
                + currentSeason
                + ", duration="
                + duration
                + '}';
    }
}


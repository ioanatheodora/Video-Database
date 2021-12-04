package video;

import fileio.MovieInputData;

import java.util.ArrayList;

public final class Movie extends Video {
    final private int duration;
    private ArrayList<Double> ratings = new ArrayList<>();
    private double averageGrade;

    public Movie(final MovieInputData movieInput) {
        super(movieInput);
        duration = movieInput.getDuration();
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    public int getDuration() {
        return duration;
    }

    public void addRating(final double rating) {
        ratings.add(rating);
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(final double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void calculateAverageRating() {
        averageGrade = 0;
        if (!ratings.isEmpty()) {
            for (double i : ratings) {
                averageGrade += i;
            }
            averageGrade = averageGrade / ratings.size();
        }
    }
}

package video;

import database.Database;
import fileio.*;
import user.User;

import java.util.ArrayList;

public class Movie extends Video{
    final private int duration;
    private ArrayList<Double> ratings = new ArrayList<>();
    private double averageGrade;

    public Movie(MovieInputData movieInput){
        super(movieInput);
        duration = movieInput.getDuration();
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    public int getDuration() {
        return duration;
    }

    public void addRating(double rating){
        ratings.add(rating);
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void calculateAverageRating(){
        averageGrade = 0;
        if(!ratings.isEmpty()){
            for(double i :ratings){
                averageGrade += i;
            }
            averageGrade = averageGrade/ratings.size();
        }
    }
}

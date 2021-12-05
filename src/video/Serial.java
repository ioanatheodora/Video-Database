package video;


import entertainment.Season;
import fileio.SerialInputData;

import java.util.ArrayList;

public class Serial extends Video {
    private ArrayList<Season> seasons;
    private final int numberOfSeasons;
    private double averageGrade;

    public Serial(final SerialInputData serialInput, final int numberOfSeasons) {
        super(serialInput);
        seasons = serialInput.getSeasons();
        this.numberOfSeasons = numberOfSeasons;
    }

    /**
     * get the number of seasons
     * @return int - number of seasons
     */
    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    /**
     * get the array of seasons
     * @return ArrayList - seasons
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * get the average grade of the show
     * @return double - average grade of the show
     */
    public double getAverageGrade() {
        return averageGrade;
    }

    /**
     * calculates the total duration of the movie by adding the duration of
     * every seasons
     * @return int - total duration
     */
    public int calculateDuration() {
        int duration = 0;
        for (Season season : seasons) {
            duration = duration + season.getDuration();
        }
        return duration;
    }

    /**
     * calculates the average grade by making the average of all the seasons
     */
    public void calculateAverageGrade() {
        averageGrade = 0;

        for (Season season : seasons) {
            season.calculateAverageRating();
            averageGrade += season.getAverageRating();
        }
        averageGrade = (averageGrade / numberOfSeasons);
    }

}

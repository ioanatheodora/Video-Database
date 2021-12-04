package video;

import entertainment.Season;
import fileio.SerialInputData;

import java.util.ArrayList;

public final class Serial extends Video {
    private ArrayList<Season> seasons;
    private int numberOfSeasons;
    private double averageGrade;

    public Serial(final SerialInputData serialInput, final int numberOfSeasons) {
        super(serialInput);
        seasons = serialInput.getSeasons();
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(final int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(final ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(final double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int calculateDuration() {
        int duration = 0;
        for (Season season : seasons) {
            duration = duration + season.getDuration();
        }
        return duration;
    }

    public void calculateAverageGrade() {
        averageGrade = 0;
        for (Season season : seasons) {
            season.calculateAverageRating();
            averageGrade += season.getAverageRating();
        }
        averageGrade = (averageGrade / numberOfSeasons);
    }

}

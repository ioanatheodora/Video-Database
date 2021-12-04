package video;

import entertainment.*;
import fileio.*;
import net.sf.json.util.JSONUtils;

import java.util.ArrayList;

public class Serial extends Video{
    private ArrayList<Season> seasons;
    private int numberOfSeasons;
    private double averageGrade;

    public Serial(SerialInputData serialInput, int numberOfSeasons){
        super(serialInput);
        seasons = serialInput.getSeasons();
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int calculateDuration(){
        int duration = 0;
        for(Season season : seasons){
            duration = duration + season.getDuration();
        }
        return duration;
    }

    public void calculateAverageGrade(){
        averageGrade = 0;

        for(Season season : seasons){
            season.calculateAverageRating();
            averageGrade += season.getAverageRating();
        }
        averageGrade = (averageGrade/numberOfSeasons);
    }

}

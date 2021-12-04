package actor;

import database.Database;
import fileio.ActorInputData;
import video.Movie;
import video.Serial;

import java.util.ArrayList;
import java.util.Map;

public final class Actor {
    final private String name;
    final private String careerDescription;
    final private ArrayList<String> filmography;
    final private Map<ActorsAwards, Integer> awards;
    private int noAwards;
    private double averageGrade;

    public Actor(final ActorInputData actorInputData) {
        name = actorInputData.getName();
        careerDescription = actorInputData.getCareerDescription();
        filmography = actorInputData.getFilmography();
        awards = actorInputData.getAwards();
        noAwards = calculateNoAwards(actorInputData);
    }

    public String getName() {
        return name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public int getNoAwards() {
        return noAwards;
    }

    public void setNoAwards(final int noAwards) {
        this.noAwards = noAwards;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(final int averageGrade) {
        this.averageGrade = averageGrade;
    }

    private int calculateNoAwards(final ActorInputData actorInputData) {
        int numOfAwards = 0;
        for (Map.Entry<ActorsAwards, Integer> entry : actorInputData.getAwards().entrySet()) {
            numOfAwards = numOfAwards + entry.getValue();
        }
        return numOfAwards;
    }

    public void getAverageGradeActor(final Database database) {
        averageGrade = 0;
        int number = 0;
        for (Movie movie : database.getMovies()) {
            if (filmography.contains(movie.getTitle())) {
                movie.calculateAverageRating();
                if (movie.getAverageGrade() > 0) {
                    averageGrade += movie.getAverageGrade();
                    number++;
                }
            }
        }

        for (Serial serial : database.getSerials()) {
            if (filmography.contains(serial.getTitle())) {
                serial.calculateAverageGrade();
                if (serial.getAverageGrade() > 0) {
                    averageGrade += serial.getAverageGrade();
                    number++;
                }
            }
        }
        if (number > 0) {
            averageGrade = (averageGrade / number);
        }
    }

}

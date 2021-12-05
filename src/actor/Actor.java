package actor;

import database.Database;
import fileio.ActorInputData;
import video.Movie;
import video.Serial;

import java.util.ArrayList;
import java.util.Map;

public final class Actor {
    private final String name;
    private final String careerDescription;
    private final ArrayList<String> filmography;
    private final Map<ActorsAwards, Integer> awards;
    private final int noAwards;
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

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(final int averageGrade) {
        this.averageGrade = averageGrade;
    }

    /**
     * calculates the number of awards for each actor
     * @param actorInputData
     * @return
     */
    private int calculateNoAwards(final ActorInputData actorInputData) {
        int numOfAwards = 0;
        for (Map.Entry<ActorsAwards, Integer> entry : actorInputData.getAwards().entrySet()) {
            numOfAwards = numOfAwards + entry.getValue();
        }
        return numOfAwards;
    }

    /**
     * Calculates the average grade for each actor based on the ratings
     * previously given
     * @param database
     */
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

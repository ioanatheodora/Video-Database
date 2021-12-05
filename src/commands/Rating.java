package commands;

import database.Database;
import user.User;
import video.Movie;
import video.Serial;

import java.util.ArrayList;
import java.util.Map;

public final class Rating {
    private final User user;
    private String movie;
    private String serial;
    private int season;
    private final double rating;
    private final Database database;

    public Rating(final User user, final String movie, final double rating,
                  final Database database) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.database = database;
    }

    public Rating(final User user, final String serial, final int season, final double rating,
                  final Database database) {
        this.user = user;
        this.serial = serial;
        this.season = season;
        this.rating = rating;
        this.database = database;
    }

    /**
     * rates the movie with the rating given by the user and adds
     * the rating to the user's rating and the movie's ratings
     * @return a String - message following the state of the command
     */
    public String rateMovie() {
        String message = "";

        if (user.getHistory().containsKey(movie)) {
//            check whether the movie is viewed
            if (user.getMovieRatings() != null) {
                if (!user.getMovieRatings().containsKey(movie)) {
//                check whether the movie has already been rated
                    user.getMovieRatings().put(movie, rating);
                    message = "success -> " + movie + " was rated with " + rating
                            + " by " + user.getUsername();
                    user.setRatingsGiven(user.getRatingsGiven() + 1);
                    for (Movie video : database.getMovies()) {
                        if (video.getTitle().equals(movie)) {
                            video.addRating(rating);
                        }
                    }
                } else {
                    message = "error -> " + movie + " has been already rated";
                }
            }
        } else {
            message = "error -> " + movie + " is not seen";
        }

        return message;
    }

    /**
     * rates the show with the rating given by the user and adds the rating
     * to user's ratings and the show's ratings
     * @return a String - message following the state of the command
     */
    public String rateSerial() {
        String message = "";

        if (user.getHistory().containsKey(serial)) {
//            check whether the serial has been seen
                if (user.getSerialRating().containsKey(serial)) {
                    // verify whether it has ever been rated
                    for (Map.Entry<String, ArrayList<Double>> entry
                           : user.getSerialRating().entrySet()) {
                        if (entry.getKey().equals(serial)) {
                            if (entry.getValue().get(season - 1) != 0) {
                                //it has already been rated
                                message = "error -> " + serial + " has been already rated";
                            } else {
                                user.addSeasonRating(serial, season, rating);
                                user.setRatingsGiven(user.getRatingsGiven() + 1);
                                message = "success -> " + serial + " was rated with "
                                        + rating + " by " + user.getUsername();
//                                add the rating in the list of ratings
                                for (Serial video : database.getSerials()) {
                                    if (video.getTitle().equals(serial)) {
                                        video.getSeasons().get(season - 1).addRating(rating);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (Serial video : database.getSerials()) {
                        if (video.getTitle().equals(serial)) {
                            user.initiateRating(serial, video.getNumberOfSeasons());
                            user.addSeasonRating(serial, season, rating);
                            video.getSeasons().get(season - 1).addRating(rating);
                        }
                    }
                    message = "success -> " + serial + " was rated with "
                            + rating + " by " + user.getUsername();
                    user.setRatingsGiven(user.getRatingsGiven() + 1);
                }
            } else {
            message = "error -> " +  serial + " is not seen";
        }
        return message;
    }

}

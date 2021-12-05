package database;

import actor.Actor;
import fileio.ActorInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.UserInputData;
import fileio.SerialInputData;
import user.User;
import video.Movie;
import video.Serial;

public class AddToDatabase {
    private final Input input;

    public AddToDatabase(final Input input) {
        this.input = input;
    }

    /**
     * Adds the input to the database and calculates furthe the number
     * of views and number of times the video appeared in the user's
     * favorite list, which are updated later. It also calculates the number of the
     * times a genre has been viewed
     * @return the database
     */
    public Database addAll() {
        Database database = new Database();

        for (UserInputData user : input.getUsers()) {
            User newUser = new User(user);
            database.addUser(newUser);
        }

        for (ActorInputData actor: input.getActors()) {
            Actor newActor = new Actor(actor);
            database.addActor(newActor);
        }

        for (MovieInputData movie : input.getMovies()) {
            Movie newMovie = new Movie(movie);
            database.addMovie(newMovie);
        }

        for (SerialInputData serial : input.getSerials()) {
            Serial newSerial = new Serial(serial, serial.getNumberSeason());
            database.addSerial(newSerial);
        }

        database.getViewsPerVideo();
        database.getFavouritePerVideo();
        database.getNoOfGenres();

        return database;
    }
}

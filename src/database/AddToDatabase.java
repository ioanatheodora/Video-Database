package database;

import actor.Actor;
import fileio.ActorInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import user.User;
import video.Movie;
import video.Serial;

public final class AddToDatabase {
    private Input input;

    public AddToDatabase(final Input input) {
        this.input = input;
    }

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

package database;

import actor.Actor;
import user.User;
import video.Movie;
import video.Serial;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private final ArrayList<User> users;
    private final ArrayList<Actor> actors;
    private ArrayList<Movie> movies;
    private ArrayList<Serial> serials;
    private HashMap<String, Integer> popularGenres;

    public Database() {
        users = new ArrayList<User>();
        actors = new ArrayList<Actor>();
        movies = new ArrayList<Movie>();
        serials = new ArrayList<Serial>();
        popularGenres = new HashMap<String, Integer>();
    }

    /**
     * get the user from the data base
     * @return ArrayList - users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * get the actors from the database
     * @return ArrauList - actors
     */
    public ArrayList<Actor> getActors() {
        return actors;
    }

    /**
     * get the movies from the database
     * @return ArrayList - movies
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * get the shows from the database
     * @return ArrauList - shows
     */
    public ArrayList<Serial> getSerials() {
        return serials;
    }

    /**
     * get the genres that appear in the database
     * @return ArrayList - genres
     */
    public HashMap<String, Integer> getPopularGenres() {
        return popularGenres;
    }

    /**
     * add a user to the list of users
     * @param user - the user to be added
     */
    public void addUser(final User user) {
        users.add(user);
    }

    /**
     * add an actor to the list of actors
     * @param actor - the actor to be added
     */
    public void addActor(final Actor actor) {
        actors.add(actor);
    }

    /**
     * add a movie to the database
     * @param movie - the movie to be added
     */
    public void addMovie(final Movie movie) {
        movies.add(movie);
    }

    /**
     * add a show to the database
     * @param serial - the show to be added
     */
    public void addSerial(final Serial serial) {
        serials.add(serial);
    }

    /**
     It populates the field favorite for every movie / show with the number
     * of the times the movie / show has been added to users' favorite list
     */
    public void getFavouritePerVideo() {
        for (Movie movie : movies) {
            for (User user : users) {
                for (String fav : user.getFavoriteMovies()) {
                    if (fav.equals(movie.getTitle())) {
                        movie.setFavorite(movie.getFavorite() + 1);
                    }
                }
            }
        }
        for (Serial serial : serials) {
            for (User user : users) {
                for (String fav : user.getFavoriteMovies()) {
                    if (fav.equals(serial.getTitle())) {
                        serial.setFavorite(
                                serial.getFavorite() + 1);
                    }
                }
            }
        }
    }

<<<<<<< HEAD
    /**
     * It populates the field views for every movie / show with the number
     * of the times the movie / show has been viewed
     */
    public void getViewsPerVideo() {
        for (Movie movie : movies) {
            movie.setViews(0);
            for (User user : users) {
                if (user.getHistory().containsKey(movie.getTitle())) {
                    movie.setViews(movie.getViews()
                            + user.getHistory().get(movie.getTitle()));
                }
            }
        }
        for (Serial serial : serials) {
            serial.setViews(0);
            for (User user : users) {
                if (user.getHistory().containsKey(serial.getTitle())) {
                    serial.setViews(serial.getViews()
                            + user.getHistory().get(serial.getTitle()));
=======
    public void getViewsPerVideo(){
        for(Movie movie : movies){
            movie.setViews(0);
            for(User user : users){
                if(user.getHistory().containsKey(movie.getTitle())){
                    movie.setViews(movie.getViews() +
                            user.getHistory().get(movie.getTitle()));
                }
            }
        }
        for(Serial serial : serials){
            serial.setViews(0);
            for(User user : users){
                if(user.getHistory().containsKey(serial.getTitle())){
                    serial.setViews(serial.getViews() +
                            user.getHistory().get(serial.getTitle()));
>>>>>>> be30c4b7be12ef40ce9c1f0d92b9d017181fc0d0
                }
            }
        }
    }

    /**
     * It adds the genres as key and the number of times it has been found in the data
     * base as value in the Map popularGenres
     */
    public void getNoOfGenres() {
//        add all the genres to the HashMap
        for (Movie movie : movies) {
            for (String genre : movie.getGenres()) {
                if (popularGenres.containsKey(genre)) {
                    popularGenres.put(genre, popularGenres.get(genre) + 1);
                } else {
                    popularGenres.put(genre, 0);
                }
            }
        }

        for (Serial serial : serials) {
            for (String genre : serial.getGenres()) {
                if (popularGenres.containsKey(genre)) {
                    popularGenres.put(genre, popularGenres.get(genre) + 1);
                } else {
                    popularGenres.put(genre, 0);
                }
            }
        }

    }



}

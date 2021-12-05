package database;

import actor.Actor;
import entertainment.Genre;
import user.User;
import video.Movie;
import video.Serial;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private ArrayList<User> users;
    private ArrayList<Actor> actors;
    private ArrayList<Movie> movies;
    private ArrayList<Serial> serials;
    private HashMap<String, Integer> popularGenres;

    public Database(){
        users = new ArrayList<User>();
        actors = new ArrayList<Actor>();
        movies = new ArrayList<Movie>();
        serials = new ArrayList<Serial>();
        popularGenres = new HashMap<String, Integer>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Serial> getSerials() {
        return serials;
    }

    public void setSerials(ArrayList<Serial> serials) {
        this.serials = serials;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void addActor(Actor actor){
        actors.add(actor);
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public void addSerial(Serial serial){
        serials.add(serial);
    }

    public HashMap<String, Integer> getPopularGenres() {
        return popularGenres;
    }

    public void setPopularGenres(HashMap<String, Integer> popularGenres) {
        this.popularGenres = popularGenres;
    }

    public void getFavouritePerVideo(){
        for(Movie movie : movies){
            for(User user : users){
                for(String fav : user.getFavoriteMovies()){
                    if(fav.equals(movie.getTitle())){
                        movie.setFavorite(
                                movie.getFavorite() + 1);
                    }
                }
            }
        }
        for(Serial serial : serials){
            for(User user : users){
                for(String fav : user.getFavoriteMovies()){
                    if(fav.equals(serial.getTitle())){
                        serial.setFavorite(
                                serial.getFavorite() + 1);
                    }
                }
            }
        }
    }

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
                }
            }
        }
    }

    public void getNoOfGenres(){
//        add all the genres to the HashMap
        for(Movie movie : movies){
            for(String genre : movie.getGenres()){
                if(popularGenres.containsKey(genre))
                    popularGenres.put(genre, popularGenres.get(genre) + 1);
                else popularGenres.put(genre, 0);
            }
        }

        for(Serial serial : serials){
            for(String genre : serial.getGenres()){
                if(popularGenres.containsKey(genre))
                    popularGenres.put(genre, popularGenres.get(genre) + 1);
                else popularGenres.put(genre, 0);
            }
        }

    }



}

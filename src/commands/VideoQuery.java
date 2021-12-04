package commands;

import actor.Actor;
import common.Constants;
import database.Database;
import fileio.ActionInputData;
import video.Movie;
import video.Serial;
import video.Video;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class VideoQuery {

    private String toStringS(ArrayList<Serial> videos) {
        String array = "[";
        for(int i = 0; i < videos.size(); i++){
            if(i != videos.size() - 1)
                array = array + videos.get(i).getTitle() +", ";
            else
                array = array + videos.get(i).getTitle();
        }
        array = array + "]";
        return array;
    }
    private String toStringM(ArrayList<Movie> videos) {
        String array = "[";
        for(int i = 0; i < videos.size(); i++){
            if(i != videos.size() - 1)
                array = array + videos.get(i).getTitle() +", ";
            else
                array = array + videos.get(i).getTitle();
        }
        array = array + "]";
        return array;
    }
    private void sortMoviesTitle(ArrayList<Movie> movies, String criteria)
    {
        if(criteria.equals(Constants.ASC)){
            movies.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
        }else {
            movies.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getTitle().compareTo(o1.getTitle());
                }
            });
        }
    }

    private void sortShowTitle(ArrayList<Serial> shows, String criteria)
    {
        if(criteria.equals(Constants.ASC)){
            shows.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
        }else {
            shows.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o2.getTitle().compareTo(o1.getTitle());
                }
            });
        }
    }

    private boolean checkFilters(Movie video, List<List<String>> filters){
//        0 -> year
        if(filters.get(0) != null && filters.get(0).get(0) != null)
            if(video.getYear() != Integer.parseInt(filters.get(0).get(0))){
                return false;
            }
//        1 -> genre
        if(filters.get(1) != null && filters.get(1).get(0) != null){
            for(String genre : filters.get(1)){
                if(!video.getGenres().contains(genre))
                    return false;
            }
        }

//        2 -> actors description => we skip it
//        3 -> awards => we skip it
        return true;
    }

    private boolean checkFilters(Serial video, List<List<String>> filters){
//        0 -> year
        if(filters.get(0) != null && filters.get(0).get(0) != null)
            if(video.getYear() != Integer.parseInt(filters.get(0).get(0))){
                return false;
            }
//        1 -> genre
        if(filters.get(1) != null){
            for(String genre : filters.get(1)){
                if(!video.getGenres().contains(genre))
                    return false;
            }
        }

//        2 -> actors description => we skip it
//        3 -> awards => we skip it
        return true;
    }

    private String favoriteMovieQuery(ActionInputData actionInputData,
                                     Database database, int N){
        ArrayList<Movie> favorite = new ArrayList<>();

        for(Movie video : database.getMovies()){
            if(checkFilters(video, actionInputData.getFilters()) &&
                video.getFavorite() != 0)
                favorite.add(video);
        }

        sortMoviesTitle(favorite, actionInputData.getSortType());

        if(actionInputData.getSortType().equals(Constants.DESC)){
            favorite.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getFavorite() - o1.getFavorite();
                }
            });
        }
        else{
            favorite.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.getFavorite() - o2.getFavorite();
                }
            });
        }

        for(int i = favorite.size() - 1; i >= N; i--){
            favorite.remove(i);
        }
        return toStringM(favorite);
    }

    private String favoriteShowQuery(ActionInputData actionInputData,
                                    Database database,
                                    int N){
        ArrayList<Serial> favorite = new ArrayList<>();

        for(Serial video : database.getSerials()){
            if(checkFilters(video, actionInputData.getFilters()) &&
                video.getFavorite() != 0)
                favorite.add(video);
        }

        sortShowTitle(favorite, actionInputData.getSortType());

        if(actionInputData.getSortType().equals(Constants.DESC)){
            favorite.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o2.getFavorite() - o1.getFavorite();
                }
            });
        }
        else{
            favorite.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o1.getFavorite() - o2.getFavorite();
                }
            });
        }

        for(int i = favorite.size() - 1; i >= N; i--){
            favorite.remove(i);
        }
        return toStringS(favorite);
    }

    private String longestMovieQuery(ActionInputData actionInputData,
                                    Database database, int N){
        ArrayList<Movie> longest = new ArrayList<>();

        for(Movie movie : database.getMovies()){
            if(checkFilters(movie, actionInputData.getFilters()))
                longest.add(movie);
        }

        sortMoviesTitle(longest, actionInputData.getSortType());

        if(actionInputData.getSortType().equals(Constants.DESC)){
            longest.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getDuration() - o1.getDuration();
                }
            });
        }
        else{
            longest.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.getDuration() - o2.getDuration();
                }
            });
        }
        for(int i = longest.size() - 1; i >= N; i--){
            longest.remove(i);
        }
        return toStringM(longest);
    }

    private  String longestShowQuery(ActionInputData actionInputData,
                                    Database database, int N){
        ArrayList<Serial> longest = new ArrayList<>();

        for(Serial serial : database.getSerials()){
            if(checkFilters(serial, actionInputData.getFilters()))
                longest.add(serial);
        }

        sortShowTitle(longest, actionInputData.getSortType());
        if(actionInputData.getSortType().equals(Constants.DESC)){
            longest.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o2.calculateDuration() - o1.calculateDuration();
                }
            });
        }
        else{
            longest.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o1.calculateDuration() - o2.calculateDuration();
                }
            });
        }
        for(int i = longest.size() - 1; i >= N; i--){
            longest.remove(i);
        }
        return toStringS(longest);
    }

    private String mostViewedMovieQuery(ActionInputData actionInputData,
                                     Database database, int N){
        ArrayList<Movie> viewed = new ArrayList<>();

        for(Movie movie : database.getMovies()){
            if(checkFilters(movie, actionInputData.getFilters()) &&
                movie.getViews() != 0)
                viewed.add(movie);
        }
        sortMoviesTitle(viewed, actionInputData.getSortType());

        if(actionInputData.getSortType().equals(Constants.DESC)){
            viewed.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getViews() - o1.getViews();
                }
            });
        }
        else{
            viewed.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.getViews() - o2.getViews();
                }
            });
        }
        for(int i = viewed.size() - 1; i >= N; i--){
            viewed.remove(i);
        }
        return toStringM(viewed);
    }

    private String mostViewedShowQuery(ActionInputData actionInputData,
                                        Database database, int N){
        ArrayList<Serial> viewed = new ArrayList<>();

        System.out.println("----------" + actionInputData.getActionId() + "----------");

        for(Serial serial : database.getSerials()){
            if(checkFilters(serial, actionInputData.getFilters())
                    && serial.getViews() != 0) {
                viewed.add(serial);
                System.out.println(serial.getTitle() + "  " + serial.getViews() );
            }
        }
        sortShowTitle(viewed, actionInputData.getSortType());

        if(actionInputData.getSortType().equals(Constants.DESC)){
            viewed.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o2.getViews() - o1.getViews();
                }
            });
        }
        else{
            viewed.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o1.getViews() - o2.getViews();
                }
            });
        }
        for(int i = viewed.size() - 1; i >= N; i--){
            viewed.remove(i);
        }
        return toStringS(viewed);
    }

    private String ratingShowQuery(ActionInputData actionInputData,
                                       Database database, int N){
        ArrayList<Serial> rating = new ArrayList<>();

        for(Serial serial : database.getSerials()){
            if(checkFilters(serial, actionInputData.getFilters())
                    && serial.getAverageGrade() != 0)
                rating.add(serial);
        }
        sortShowTitle(rating, actionInputData.getSortType());
        if(actionInputData.getSortType().equals(Constants.DESC)){
            rating.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o2.getViews() - o1.getViews();
                }
            });
        }
        else{
            rating.sort(new Comparator<Serial>() {
                @Override
                public int compare(Serial o1, Serial o2) {
                    return o1.getViews() - o2.getViews();
                }
            });
        }
        for(int i = rating.size() - 1; i >= N; i--){
            rating.remove(i);
        }
        return toStringS(rating);
    }

    private String ratingMovieQuery(ActionInputData actionInputData,
                                        Database database, int N){
        ArrayList<Movie> rating = new ArrayList<>();

        for(Movie movie : database.getMovies()){
            if(checkFilters(movie, actionInputData.getFilters()) &&
                    movie.getAverageGrade() != 0)
                rating.add(movie);
        }
        sortMoviesTitle(rating, actionInputData.getSortType());

        if(actionInputData.getSortType().equals(Constants.DESC)){
            rating.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getViews() - o1.getViews();
                }
            });
        }
        else{
            rating.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.getViews() - o2.getViews();
                }
            });
        }
        for(int i = rating.size() - 1; i >= N; i--){
            rating.remove(i);
        }
        return toStringM(rating);
    }

    public String doTheQuery(ActionInputData actionInputData, Database database, int N){
        String message = "Query result: ";
        if(actionInputData.getCriteria().equals("favorite")){
            if(actionInputData.getObjectType().equals(Constants.MOVIES))
                message += favoriteMovieQuery(actionInputData, database, N);
            if(actionInputData.getObjectType().equals(Constants.SHOWS))
                message += favoriteShowQuery(actionInputData, database, N);
        }
        if(actionInputData.getCriteria().equals("longest")){
            if(actionInputData.getObjectType().equals(Constants.MOVIES))
                message += longestMovieQuery(actionInputData, database, N);
            if(actionInputData.getObjectType().equals(Constants.SHOWS))
                message += longestShowQuery(actionInputData, database, N);
        }

        if(actionInputData.getCriteria().equals(Constants.MOST_VIEWED)){
            if(actionInputData.getObjectType().equals(Constants.MOVIES))
                message += mostViewedMovieQuery(actionInputData, database, N);
            if(actionInputData.getObjectType().equals(Constants.SHOWS))
                message += mostViewedShowQuery(actionInputData, database, N);
        }

        if(actionInputData.getCriteria().equals("ratings")){
            if(actionInputData.getObjectType().equals(Constants.MOVIES))
                message += ratingMovieQuery(actionInputData, database, N);
            if(actionInputData.getObjectType().equals(Constants.SHOWS))
                message += ratingShowQuery(actionInputData, database, N);
        }
        return message;
    }
}

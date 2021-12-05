package commands;

import common.Constants;
import database.Database;
import fileio.ActionInputData;
import video.Movie;
import video.Serial;
import video.Video;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VideoQuery {
    private String toStringS(final ArrayList<Serial> videos) {
        StringBuilder array = new StringBuilder("[");
        for (int i = 0; i < videos.size(); i++) {
            if (i != videos.size() - 1) {
                array.append(videos.get(i).getTitle()).append(", ");
            } else {
                array.append(videos.get(i).getTitle());
            }
        }
        array.append("]");

        return array.toString();
    }

    private String toStringM(final ArrayList<Movie> videos) {
        StringBuilder array = new StringBuilder("[");
        for (int i = 0; i < videos.size(); i++) {
            if (i != videos.size() - 1) {
                array.append(videos.get(i).getTitle()).append(", ");
            } else {
                array.append(videos.get(i).getTitle());
            }
        }

        array.append("]");
        return array.toString();
    }
    private void sortMoviesTitle(final ArrayList<Movie> movies, final String criteria) {
        if (criteria.equals(Constants.ASC)) {
            movies.sort(Comparator.comparing(Video::getTitle));
        } else {
            movies.sort((o1, o2) -> o2.getTitle().compareTo(o1.getTitle()));
        }
    }

    private void sortShowTitle(final ArrayList<Serial> shows, final String criteria) {
        if (criteria.equals(Constants.ASC)) {
            shows.sort(Comparator.comparing(Video::getTitle));
        } else {
            shows.sort((o1, o2) -> o2.getTitle().compareTo(o1.getTitle()));
        }
    }

    private boolean checkFilters(final Movie video, final  List<List<String>> filters) {
//        0 -> year
        if (filters.get(0) != null && filters.get(0).get(0) != null) {
            if (video.getYear() != Integer.parseInt(filters.get(0).get(0))) {
                return false;
            }
        }
//        1 -> genre
        if (filters.get(1) != null && filters.get(1).get(0) != null) {
            for (String genre : filters.get(1)) {
                if (!video.getGenres().contains(genre)) {
                    return false;
                }
            }
        }
//        2 -> actors description => we skip it
//        3 -> awards => we skip it
        return true;
    }

    private boolean checkFilters(final Serial video, final List<List<String>> filters) {
//        0 -> year
        if (filters.get(0) != null && filters.get(0).get(0) != null) {
            if (video.getYear() != Integer.parseInt(filters.get(0).get(0))) {
                return false;
            }
        }
//        1 -> genre
        if (filters.get(1) != null) {
            for (String genre : filters.get(1)) {
                if (!video.getGenres().contains(genre)) {
                    return false;
                }
            }
        }
//        2 -> actors description => we skip it
//        3 -> awards => we skip it
        return true;
    }

    private String favoriteMovieQuery(final ActionInputData actionInputData,
                                      final Database database, final int number) {
        ArrayList<Movie> favorite = new ArrayList<>();

        for (Movie video : database.getMovies()) {
            if (checkFilters(video, actionInputData.getFilters())
                    && video.getFavorite() != 0) {
                favorite.add(video);
            }
        }

        sortMoviesTitle(favorite, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.DESC)) {
            favorite.sort((o1, o2) -> o2.getFavorite() - o1.getFavorite());
        } else {
            favorite.sort(Comparator.comparingInt(Video::getFavorite));
        }

        if (favorite.size() > number) {
            favorite.subList(number, favorite.size()).clear();
        }
        return toStringM(favorite);
    }

    private String favoriteShowQuery(final ActionInputData actionInputData,
                                     final Database database, final int number) {
        ArrayList<Serial> favorite = new ArrayList<>();

        for (Serial video : database.getSerials()) {
            if (checkFilters(video, actionInputData.getFilters())
                    && video.getFavorite() != 0) {
                favorite.add(video);
            }
        }

        sortShowTitle(favorite, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.DESC)) {
            favorite.sort((o1, o2) -> o2.getFavorite() - o1.getFavorite());
        } else {
            favorite.sort(Comparator.comparingInt(Video::getFavorite));
        }

        if (favorite.size() > number) {
            favorite.subList(number, favorite.size()).clear();
        }
        return toStringS(favorite);
    }

    private String longestMovieQuery(final ActionInputData actionInputData,
                                     final Database database, final int number) {
        ArrayList<Movie> longest = new ArrayList<>();

        for (Movie movie : database.getMovies()) {
            if (checkFilters(movie, actionInputData.getFilters())) {
                longest.add(movie);
            }
        }

        sortMoviesTitle(longest, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.DESC)) {
            longest.sort((o1, o2) -> o2.getDuration() - o1.getDuration());
        } else {
            longest.sort(Comparator.comparingInt(Movie::getDuration));
        }

        if (longest.size() > number) {
            longest.subList(number, longest.size()).clear();
        }

        return toStringM(longest);
    }

    private  String longestShowQuery(final ActionInputData actionInputData,
                                     final Database database, final int number) {
        ArrayList<Serial> longest = new ArrayList<>();

        for (Serial serial : database.getSerials()) {
            if (checkFilters(serial, actionInputData.getFilters())) {
                longest.add(serial);
            }
        }

        sortShowTitle(longest, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.DESC)) {
            longest.sort((o1, o2) -> o2.calculateDuration() - o1.calculateDuration());
        } else {
            longest.sort(Comparator.comparingInt(Serial::calculateDuration));
        }
        if (longest.size() > number) {
            longest.subList(number, longest.size()).clear();
        }

        return toStringS(longest);
    }

    private String mostViewedMovieQuery(final ActionInputData actionInputData,
                                        final Database database, final int number) {
        ArrayList<Movie> viewed = new ArrayList<>();

        for (Movie movie : database.getMovies()) {
            if (checkFilters(movie, actionInputData.getFilters())
                    && movie.getViews() != 0) {
                viewed.add(movie);
            }
        }

        sortMoviesTitle(viewed, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.DESC)) {
            viewed.sort((o1, o2) -> o2.getViews() - o1.getViews());
        } else {
            viewed.sort(Comparator.comparingInt(Video::getViews));
        }

        if (viewed.size() > number) {
            viewed.subList(number, viewed.size()).clear();
        }

        return toStringM(viewed);
    }

    private String mostViewedShowQuery(final ActionInputData actionInputData,
                                       final Database database, final int number) {
        ArrayList<Serial> viewed = new ArrayList<>();

        for (Serial serial : database.getSerials()) {
            if (checkFilters(serial, actionInputData.getFilters())
                    && serial.getViews() != 0) {
                viewed.add(serial);
            }
        }
        sortShowTitle(viewed, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.DESC)) {
            viewed.sort((o1, o2) -> o2.getViews() - o1.getViews());
        } else {
            viewed.sort(Comparator.comparingInt(Video::getViews));
        }

        if (viewed.size() > number) {
            viewed.subList(number, viewed.size()).clear();
        }

        return toStringS(viewed);
    }

    private String ratingShowQuery(final ActionInputData actionInputData,
                                   final Database database, final int number) {
        ArrayList<Serial> rating = new ArrayList<>();

        for (Serial serial : database.getSerials()) {
            serial.calculateAverageGrade();

            if (checkFilters(serial, actionInputData.getFilters())
                    && serial.getAverageGrade() != 0) {
                rating.add(serial);
            }
        }

        sortShowTitle(rating, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.DESC)) {
            rating.sort((o1, o2) -> o2.getViews() - o1.getViews());
        } else {
            rating.sort(Comparator.comparingInt(Video::getViews));
        }

        if (rating.size() > number) {
            rating.subList(number, rating.size()).clear();
        }

        return toStringS(rating);
    }

    private String ratingMovieQuery(final ActionInputData actionInputData,
                                    final Database database, final int number) {
        ArrayList<Movie> rating = new ArrayList<>();

        for (Movie movie : database.getMovies()) {
            if (checkFilters(movie, actionInputData.getFilters())
                    && movie.getAverageGrade() != 0) {
                rating.add(movie);
            }
        }
        sortMoviesTitle(rating, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.DESC)) {
            rating.sort((o1, o2) -> o2.getViews() - o1.getViews());
        } else {
            rating.sort(Comparator.comparingInt(Video::getViews));
        }

        if (rating.size() > number) {
            rating.subList(number, rating.size()).clear();
        }
        return toStringM(rating);
    }

    /**
     * does the specified Video Query
     * @param actionInputData input data for action
     * @param database contains actors, user, movies and shows
     * @param number represents the maximum number of videos the query should have
     * @return a String - a message following the state of the Video Query
     */
    public String doTheQuery(final ActionInputData actionInputData,
                             final Database database, final int number) {
        String message = "Query result: ";
        if (actionInputData.getCriteria().equals("favorite")) {
            if (actionInputData.getObjectType().equals(Constants.MOVIES)) {
                message += favoriteMovieQuery(actionInputData, database, number);
            }
            if (actionInputData.getObjectType().equals(Constants.SHOWS)) {
                message += favoriteShowQuery(actionInputData, database, number);
            }
        }
        if (actionInputData.getCriteria().equals("longest")) {
            if (actionInputData.getObjectType().equals(Constants.MOVIES)) {
                message += longestMovieQuery(actionInputData, database, number);
            }
            if (actionInputData.getObjectType().equals(Constants.SHOWS)) {
                message += longestShowQuery(actionInputData, database, number);
            }
        }

        if (actionInputData.getCriteria().equals(Constants.MOST_VIEWED)) {
            if (actionInputData.getObjectType().equals(Constants.MOVIES)) {
                message += mostViewedMovieQuery(actionInputData, database, number);
            }
            if (actionInputData.getObjectType().equals(Constants.SHOWS)) {
                message += mostViewedShowQuery(actionInputData, database, number);
            }
        }

        if (actionInputData.getCriteria().equals("ratings")) {
            if (actionInputData.getObjectType().equals(Constants.MOVIES)) {
                message += ratingMovieQuery(actionInputData, database, number);
            }
            if (actionInputData.getObjectType().equals(Constants.SHOWS)) {
                message += ratingShowQuery(actionInputData, database, number);
            }
        }
        return message;
    }
}

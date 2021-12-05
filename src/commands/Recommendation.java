package commands;

import common.Constants;
import database.Database;
import fileio.ActionInputData;
import user.User;
import video.Movie;
import video.Serial;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class Recommendation {
    private final Database database;
    private final ActionInputData actionInputData;

    public Recommendation(final ActionInputData actionInputData, final Database database) {
        this.actionInputData = actionInputData;
        this.database = database;
    }

    private boolean checkFilter(final Movie video, final String genre) {
        return video.getGenres().contains(genre);
    }

    private boolean checkFilter(final Serial video, final String genre) {
        return video.getGenres().contains(genre);
    }

    private static HashMap<String, Integer> sortByValue(final HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    private static HashMap<String, Double> sortByValueD(final HashMap<String, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double>> list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort(Comparator.comparingDouble(Map.Entry::getValue));

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    private String standard(final User user) {
        for (Movie movie : database.getMovies()) {
            if (!user.getHistory().containsKey(movie.getTitle())) {
                return "StandardRecommendation result: " + movie.getTitle();
            }
        }

        for (Serial serial : database.getSerials()) {
            if (!user.getHistory().containsKey(serial.getTitle())) {
                return "StandardRecommendation result: " + serial.getTitle();
            }
        }
        return "StandardRecommendation cannot be applied!";
    }

    private String unseen(final User user) {
        double bestRating = -1;
        String bestUnseenVideo = "";

        for (Movie movie : database.getMovies()) {
            movie.calculateAverageRating();
            if (!user.getHistory().containsKey(movie.getTitle())
                    && bestRating < movie.getAverageGrade()) {
                bestRating = movie.getAverageGrade();
                bestUnseenVideo  = movie.getTitle();
            }
        }

        for (Serial serial :  database.getSerials()) {
            serial.calculateAverageGrade();
            if (!user.getHistory().containsKey(serial.getTitle())
                    && bestRating < serial.getAverageGrade()) {
                bestRating = serial.getAverageGrade();
                bestUnseenVideo = serial.getTitle();
            }
        }
        if (!bestUnseenVideo.equals("")) {
            return "BestRatedUnseenRecommendation result: "
                    + bestUnseenVideo;
        } else {
            return "BestRatedUnseenRecommendation cannot be applied!";
        }
    }

    private String favorite(final User user) {
        int noFav = 0;
        String bestUnseenVideo = "";

        for (Movie movie : database.getMovies()) {
            if (!user.getHistory().containsKey(movie.getTitle())
                    && noFav < movie.getFavorite()) {
                noFav = movie.getFavorite();
                bestUnseenVideo  = movie.getTitle();
            }
        }

        for (Serial serial :  database.getSerials()) {
            if (!user.getHistory().containsKey(serial.getTitle())
                    && noFav < serial.getFavorite()) {
                noFav = serial.getFavorite();
                bestUnseenVideo = serial.getTitle();
            }
        }
        if (noFav > 0) {
            return "FavoriteRecommendation result: "
                    + bestUnseenVideo;
        } else {
            return "FavoriteRecommendation cannot be applied!";
        }
    }


    private String search(final User user) {
        TreeMap<String, Double> sortMap = new TreeMap<>();

//        get all the movies and shows in the hashmap with the required filters
//            used a TreeMap to already sort them by key --> by name
        for (Movie movie : database.getMovies()) {
            movie.calculateAverageRating();
            if (!user.getHistory().containsKey(movie.getTitle())
                    && checkFilter(movie, actionInputData.getGenre())) {
                sortMap.put(movie.getTitle(), movie.getAverageGrade());
            }
        }
        for (Serial serial : database.getSerials()) {
            serial.calculateAverageGrade();
            if (!user.getHistory().containsKey(serial.getTitle())
                    && checkFilter(serial, actionInputData.getGenre())) {
                sortMap.put(serial.getTitle(), serial.getAverageGrade());
            }
        }

//        sort the hashmap depending on the name and then on the rating
        StringBuilder message = new StringBuilder("SearchRecommendation result: [");
        LinkedHashMap<String, Double> sortedByName = new LinkedHashMap<>(sortMap);
        HashMap<String, Double> finalSort;

        finalSort = sortByValueD(sortedByName);
        for (Map.Entry<String, Double> entry : finalSort.entrySet()) {
            message.append(entry.getKey()).append(", ");
        }

        message = new StringBuilder(message.substring(0, message.length() - 2) + "]");

        if (finalSort.size() > 0) {
            return message.toString();
        } else {
            return "SearchRecommendation cannot be applied!";

        }
    }

    private String popular(final User user) {
        HashMap<String, Integer> popular = sortByValue(database.getPopularGenres());

        for (Map.Entry<String, Integer> entry : popular.entrySet()) {
            for (Movie movie : database.getMovies()) {
                if (!(user.getHistory().containsKey(movie.getTitle()))
                        && movie.getGenres().contains(entry.getKey())) {
                    return "PopularRecommendation result: " + movie.getTitle();
                }
            }

            for (Serial serial : database.getSerials()) {
                if (!(user.getHistory().containsKey(serial.getTitle()))
                        && serial.getGenres().contains(entry.getKey())) {
                    return "PopularRecommendation result: " + serial.getTitle();
                }
            }
        }

        return "PopularRecommendation cannot be applied!";
    }

    /**
     * computes the recommendation based on the criteria given
     * @return a String - message following the state of the recommendation
     */
    public String doTheRecommendation() {
        for (User find : database.getUsers()) {
            if (find.getUsername().equals(actionInputData.getUsername())) {
                if (actionInputData.getType().equals(Constants.STANDARD)) {
                    return standard(find);
                }

                if (actionInputData.getType().equals(Constants.BEST_UNSEEN)) {
                    return unseen(find);
                }
                if (find.getSubscriptionType().equals(Constants.PREMIUM)
                        && actionInputData.getType().equals("search")) {
                    return search(find);
                }
                if (find.getSubscriptionType().equals(Constants.PREMIUM)
                        && actionInputData.getType().equals("favorite")) {
                    return favorite(find);
                }
                if (find.getSubscriptionType().equals(Constants.PREMIUM)
                        && actionInputData.getType().equals("popular")) {
                    return popular(find);
                }
            }
        }
        return actionInputData.getType().substring(0, 1).toUpperCase()
                + actionInputData.getType().substring(1) + "Recommendation cannot be applied!";
    }

}

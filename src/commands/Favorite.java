package commands;

import database.Database;
import user.User;
import video.Serial;
import video.Video;

import java.util.ArrayList;

public final class Favorite {
    private User user;
    private String video;

    public Favorite(final User user, final String video) {
        this.user = user;
        this.video = video;
    }

    public String addFavorite(final ArrayList<String> movies, final Database database) {
        String message;
        if (movies.contains(video)) {
            message = "error -> " + video + " is already in favourite list";
        } else {
            if (user.getHistory().containsKey(video)) {
                message = "success -> " + video + " was added as favourite";
                user.getFavoriteMovies().add(video);
                for (Video fav : database.getMovies()) {
                    if (fav.getTitle().equals(video)) {
                        fav.setFavorite(fav.getFavorite() + 1);
                    }
                }
                for (Serial fav : database.getSerials()) {
                    if (fav.getTitle().equals(video)) {
                        fav.setFavorite(fav.getFavorite() + 1);
                    }
                }
            } else {
                message = "error -> " + video + " is not seen";
            }
        }
        return message;
    }
}

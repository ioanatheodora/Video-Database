package commands;

import database.Database;
import user.User;
import video.Movie;
import video.Serial;

import java.util.Map;
import java.util.Objects;

public final class View {
    private final User user;
    private final String video;
    private Database database;

    public View(final User user, final String video, final Database database) {
        this.user = user;
        this.video = video;
        this.database = database;
    }

    public String viewVideo() {
        String message = "";
        if (user.getHistory().containsKey(video)) {
            for (Map.Entry<String, Integer> show : user.getHistory().entrySet()) {
                if (Objects.equals(show.getKey(), video)) {
//                if viewed, we change the number of times it has been viewed
                    show.setValue(show.getValue() + 1);
                    message = "success -> " + video + " was viewed with total views of "
                            + show.getValue();
                }
            }
        } else {
            user.getHistory().put(video, 1);
            message = "success -> " + video + " was viewed with total views of 1";
        }

        for (Movie viewed : database.getMovies()) {
            if (viewed.getTitle().equals(video)) {
                for (String genre : viewed.getGenres()) {
                    if (database.getPopularGenres().containsKey(genre)) {
                        database.getPopularGenres().put(genre,
                                database.getPopularGenres().get(genre) + 1);
                    } else {
                        database.getPopularGenres().put(genre, 0);
                    }
                }
            }
        }

        for (Serial viewed : database.getSerials()) {
            if (viewed.getTitle().equals(video)) {
                for (String genre : viewed.getGenres()) {
                    if (database.getPopularGenres().containsKey(genre)) {
                        database.getPopularGenres().put(genre,
                                database.getPopularGenres().get(genre) + 1);
                    } else {
                        database.getPopularGenres().put(genre, 0);
                    }
                }
            }
        }
        return message;
    }
}
package compute;

import commands.Favorite;
import commands.Rating;
import commands.View;
import database.Database;
import fileio.ActionInputData;
import fileio.Output;
import user.User;

public class ComputeOneCommand {
    private final User user;
    private final ActionInputData actionInputData;
    private final Database database;
    private final org.json.simple.JSONArray jsonArray;

    public ComputeOneCommand(final User user, final ActionInputData actionInputData,
                             final Database database, final org.json.simple.JSONArray jsonArray) {
        this.user = user;
        this.actionInputData = actionInputData;
        this.database = database;
        this.jsonArray = jsonArray;
    }

    /**
     * Verifies what kind of command the action does and sends it further to
     * one of the commands: Favorite, View, Rating
     */
    public void compute() {

        if (actionInputData.getType().equals("favorite")) {
            Favorite favorite = new Favorite(user, actionInputData.getTitle());
            Output output = new Output(favorite.addFavorite(user.getFavoriteMovies(), database));
            output.addResult(actionInputData.getActionId(), jsonArray);
        }
        if (actionInputData.getType().equals("view")) {
            View view = new View(user, actionInputData.getTitle(), database);
            Output output = new Output(view.viewVideo());
            output.addResult(actionInputData.getActionId(), jsonArray);
                database.getViewsPerVideo();
        }

        if (actionInputData.getType().equals("rating")) {
            // the user rates a movie
            if (actionInputData.getSeasonNumber() == 0) {
                Rating rating = new Rating(user, actionInputData.getTitle(),
                        actionInputData.getGrade(), database);
                Output output = new Output(rating.rateMovie());
                output.addResult(actionInputData.getActionId(), jsonArray);
            } else {
                Rating rating = new Rating(user, actionInputData.getTitle(),
                        actionInputData.getSeasonNumber(), actionInputData.getGrade(), database);
                Output output = new Output(rating.rateSerial());
                output.addResult(actionInputData.getActionId(), jsonArray);
            }

        }
    }
}

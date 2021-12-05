package compute;

import commands.Recommendation;
import database.Database;
import fileio.ActionInputData;
import fileio.Output;
import user.User;

public class ComputeOneRecommendation {
    private final ActionInputData actionInputData;
    private final Database database;
    private final org.json.simple.JSONArray jsonArray;

    public ComputeOneRecommendation(final User user, final ActionInputData actionInputData,
                                    final Database database,
                                    final org.json.simple.JSONArray jsonArray) {
        this.actionInputData = actionInputData;
        this.database = database;
        this.jsonArray = jsonArray;
    }

    /**
     * It sends the action further to compute the recommendation and
     * adds the result to the output
     */
    public void compute() {
        Recommendation recommendation = new Recommendation(actionInputData, database);
        Output output = new Output(recommendation.doTheRecommendation());
        output.addResult(actionInputData.getActionId(), jsonArray);
    }
}

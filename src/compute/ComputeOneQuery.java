package compute;

import commands.ActorQuery;
import commands.UserQuery;
import commands.VideoQuery;
import common.Constants;
import database.Database;
import fileio.ActionInputData;
import fileio.Output;

public class ComputeOneQuery {
    private final ActionInputData actionInputData;
    private final Database database;
    private final org.json.simple.JSONArray jsonArray;

    public ComputeOneQuery(final ActionInputData actionInputData,
                           final Database database, final org.json.simple.JSONArray jsonArray) {
        this.actionInputData = actionInputData;
        this.database = database;
        this.jsonArray = jsonArray;
    }

    /**
     * Analyses what kind of query the action does and sends it further to one
     * of the main kinds of queries: Actor Query, Video Query or User Query
     */
    public void compute() {
        if (actionInputData.getObjectType().equals(Constants.ACTORS)) {
            ActorQuery actorQuery = new ActorQuery(database.getActors());
            Output output = new Output(actorQuery.doTheQuery(actionInputData, database));
            output.addResult(actionInputData.getActionId(), jsonArray);
        }
        if (actionInputData.getObjectType().equals(Constants.SHOWS)
                || actionInputData.getObjectType().equals(Constants.MOVIES)) {
            VideoQuery videoQuery = new VideoQuery();
            Output output = new Output(videoQuery.doTheQuery(
                    actionInputData, database, actionInputData.getNumber()));
            output.addResult(actionInputData.getActionId(), jsonArray);
        }
        if (actionInputData.getObjectType().equals("users")) {
            UserQuery userQuery = new UserQuery();
            Output output = new Output(userQuery.doTheQuery(actionInputData, database));
            output.addResult(actionInputData.getActionId(), jsonArray);
        }
    }
}


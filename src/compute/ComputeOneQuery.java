package compute;

import commands.ActorQuery;
import commands.UserQuery;
import commands.VideoQuery;
import common.Constants;
import database.Database;
import fileio.ActionInputData;
import fileio.Output;
import user.User;

import javax.swing.*;

public class ComputeOneQuery {
    private ActionInputData actionInputData;
    Database database;
    org.json.simple.JSONArray jsonArray;

    public ComputeOneQuery(ActionInputData actionInputData, Database database, org.json.simple.JSONArray jsonArray){
        this.actionInputData = actionInputData;
        this.database = database;
        this.jsonArray = jsonArray;
    }

    public void compute(){
        if(actionInputData.getObjectType().equals(Constants.ACTORS)){
            ActorQuery actorQuery = new ActorQuery(database.getActors());
            Output output = new Output(actorQuery.doTheQuery(actionInputData, database));
            output.addResult(actionInputData.getActionId(), jsonArray);
        }
        if(actionInputData.getObjectType().equals(Constants.SHOWS) ||
            actionInputData.getObjectType().equals(Constants.MOVIES)){
            VideoQuery videoQuery = new VideoQuery();
            Output output = new Output(videoQuery.doTheQuery
                    (actionInputData, database, actionInputData.getNumber()));
            output.addResult(actionInputData.getActionId(), jsonArray);
        }
        if(actionInputData.getObjectType().equals("users")){
            UserQuery userQuery = new UserQuery();
            Output output = new Output(userQuery.doTheQuery(actionInputData, database));
            output.addResult(actionInputData.getActionId(), jsonArray);
        }
    }
}


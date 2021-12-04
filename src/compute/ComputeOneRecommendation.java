package compute;

import commands.Recommendation;
import database.Database;
import fileio.ActionInputData;
import fileio.Output;
import user.User;

public class ComputeOneRecommendation{
    private ActionInputData actionInputData;
    Database database;
    org.json.simple.JSONArray jsonArray;

    public ComputeOneRecommendation(User user, ActionInputData actionInputData, Database database, org.json.simple.JSONArray jsonArray){
        this.actionInputData = actionInputData;
        this.database = database;
        this.jsonArray = jsonArray;
    }

    public void compute(){
        Recommendation recommandation = new Recommendation(actionInputData, database);
        Output output = new Output(recommandation.doTheRecommendation());
        output.addResult(actionInputData.getActionId(), jsonArray);
    }
}

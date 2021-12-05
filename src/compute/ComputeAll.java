package compute;

import common.Constants;
import database.AddToDatabase;
import database.Database;
import fileio.ActionInputData;
import fileio.Input;

import user.User;

public class ComputeAll {
    Input input;
    org.json.simple.JSONArray jsonArray;

    public ComputeAll(Input input, org.json.simple.JSONArray jsonArray){
        this.input = input;
        this.jsonArray = jsonArray;
    }

    public void compute(){
        AddToDatabase add = new AddToDatabase(input);
        Database database = add.addAll();

        for(ActionInputData action : input.getCommands()){
//            search for the user with the userName given
            for(User user : database.getUsers()){
                if(user.getUsername().equals(action.getUsername())){
//                    verify what action we are about to perform
                    if(action.getActionType().equals(Constants.COMMAND)) {
                        ComputeOneCommand command = new ComputeOneCommand(user, action, database, jsonArray);
                        command.compute();
                        }
                    if(action.getActionType().equals(Constants.RECOMMENDATION)) {
                        ComputeOneRecommendation recommandation = new ComputeOneRecommendation
                                (user, action, database, jsonArray);
                        recommandation.compute();
                    }
                }
            }
            if(action.getActionType().equals(Constants.QUERY)){
                ComputeOneQuery query = new ComputeOneQuery(action, database, jsonArray);
                database.getViewsPerVideo();
                query.compute();
            }
        }
    }
}

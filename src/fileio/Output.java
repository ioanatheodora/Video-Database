package fileio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Output {
    private String message;

    public Output(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addResult(int id, JSONArray jsonArray){
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("message", message);
        jsonArray.add(object);
    }

}

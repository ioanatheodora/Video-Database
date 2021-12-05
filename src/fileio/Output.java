package fileio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Output {
    private final String message;

    public Output(final String message) {
        this.message = message;
    }

    /**
     * Adds the String result to the JSON Object
     * @param id the id of the action
     * @param jsonArray the jsonArray
     */
    public void addResult(final int id, final JSONArray jsonArray) {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("message", message);
        jsonArray.add(object);
    }

}

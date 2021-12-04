package fileio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class Output {
    private String message;

    public Output(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void addResult(final int id, final JSONArray jsonArray) {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("message", message);
        jsonArray.add(object);
    }
}

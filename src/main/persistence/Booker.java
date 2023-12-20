package persistence;

import org.json.JSONObject;

public interface Booker {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

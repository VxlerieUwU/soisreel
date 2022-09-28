package fr.vxlerieuwu.soisreel.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.vxlerieuwu.soisreel.HTTPRequests;


public class Requests {
    HTTPRequests requests = new HTTPRequests();
    public JSONObject getFeed(String key) throws JSONException, IOException {
        return requests.getUrlWithKey("https://mobile.bereal.com/api/feeds/friends", key);
    }

    public JSONObject getUserProfile(String userID, String key, String firebaseKey) throws JSONException, IOException {
        String jsonRequest = new JSONObject()
                .put("data", new JSONObject().put("uid", userID))
                .toString();
        return requests.postJSONWithKey(jsonRequest,"https://us-central1-alexisbarreyat-bereal.cloudfunctions.net/getUserProfile", key, firebaseKey);
    }

    /*public JSONObject changeProfile(String fullname, String bio, String location, String key) {
        <!-- TODO: get this working, uses PATCH request to https://mobile.bereal.com/api/person/me. -->
    }*/

    public JSONObject getYourProfile(String key) throws JSONException, IOException {
        return requests.getUrlWithKey("https://mobile.bereal.com/api/person/me", key);
    }
}

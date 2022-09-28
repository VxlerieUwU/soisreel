package fr.vxlerieuwu.soisreel;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.ZoneId;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPRequests {
    static String APPVERSION = "0.35.7";
    static String OSVERSION = "12";
    static String PLATFORM = "android";
    static String TIMEZONE = ZoneId.systemDefault().toString();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    public JSONObject getUrlWithKey(String urlString, String key) throws IOException, JSONException {

        Request request = new Request.Builder()
                .header("authorization", key)
                .header("bereal-platform", PLATFORM)
                .header("bereal-os-version", OSVERSION)
                .header("bereal-timezone", TIMEZONE)
                .header("bereal-app-version", APPVERSION)
                .url(urlString)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {
            return new JSONObject(response.body().string());
        }
    }

    public JSONObject postJSONWithKey(String json, String urlString, String key, String firebaseKey) throws IOException, JSONException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .header("authorization", key)
                .header("firebase-instance-id-token", firebaseKey)
                .header("bereal-platform", PLATFORM)
                .header("bereal-os-version", OSVERSION)
                .header("bereal-timezone", TIMEZONE)
                .header("bereal-app-version", APPVERSION)
                .url(urlString)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {
            return new JSONObject(response.body().string());
        }
    }
}

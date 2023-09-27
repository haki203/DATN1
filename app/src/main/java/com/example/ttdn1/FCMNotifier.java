package com.example.ttdn1;
import android.os.AsyncTask;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FCMNotifier {
    public static final String FCM_SERVER_KEY = "AAAAjLzpiyk:APA91bFkZJukX3M-xF5NNDmwWb7XqfE2tBoWj7n6muOC27TtiGF2ndKKqAc4KlSJE_Abs-GSWwKVoRWcKr9v6ZhvVtbmnWTue2iBRd0dCZB-rCtmpsdAnrkN0XNcn1ysT13eJ2zbpH98";
    public static final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";

    public static void sendNotification(final String deviceId, final String title, final String message) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                JSONObject json = new JSONObject();
                try {
                    json.put("to", deviceId);
                    JSONObject notification = new JSONObject();
                    notification.put("title", title);
                    notification.put("body", message);
                    json.put("notification", notification);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody requestBody = RequestBody.create(JSON, json.toString());

                Request request = new Request.Builder()
                        .url(FCM_API_URL)
                        .addHeader("Authorization", "key=" + FCM_SERVER_KEY)
                        .post(requestBody)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String responseString = response.body().string();
                    System.out.println("FCM Response: " + responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}

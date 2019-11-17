package com.example.freeforfun.ui.restCalls;

import android.os.StrictMode;

import com.example.freeforfun.ui.model.User;
import com.example.freeforfun.ui.utils.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserRestCalls {

    private static final String BASE_URL = Paths.BASE_URL;

    public static User login(String username, String password){
        String url = BASE_URL + Paths.LOGIN + "/" + username + "/" + password;

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            URL urlForGetRequest = new URL(url);
            String readLine;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                User user = new Gson().fromJson(response.toString(), User.class);
                return user;
            }
            if (responseCode == HttpURLConnection.HTTP_CREATED){
                return null;
            }
        } catch(IOException ex ){
            ex.printStackTrace();
        }
        return null;
    }
}

package com.example.freeforfun.ui.restCalls;

import android.os.StrictMode;

import com.example.freeforfun.ui.utils.HttpUtils;
import com.example.freeforfun.ui.utils.Paths;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserRestCalls {

    private static final String BASE_URL = Paths.BASE_URL;

    public static String login(String username, String password){
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
                return response.toString();
            }
        } catch(IOException ex ){
            ex.printStackTrace();
        }
        return null;

    }
}

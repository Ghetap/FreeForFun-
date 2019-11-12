package com.example.freeforfun.ui.restCalls;

import com.example.freeforfun.ui.utils.HttpUtils;
import com.example.freeforfun.ui.utils.Paths;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserRestCalls {

    private static final String BASE_URL = Paths.BASE_URL;

    public static String login(String username, String password) throws IOException {
//        String url = BASE_URL + Paths.LOGIN + "/" + username + "/" + password;
//        URL urlForGetRequest = new URL(url);
//        String readLine;
//        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
//        connection.setRequestMethod("POST");
//        //conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream()));
//            StringBuffer response = new StringBuffer();
//            while ((readLine = in .readLine()) != null) {
//                response.append(readLine);
//            } in .close();
//           return "JSON String Result " + response.toString();
//            //GetAndPost.POSTRequest(response.toString());
//        } else {
           return "POST NOT WORKED";
//        }
    }
}

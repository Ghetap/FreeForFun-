package com.example.freeforfun.ui.restCalls;

import android.graphics.Bitmap;
import android.os.StrictMode;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.model.User;
import com.example.freeforfun.ui.utils.Paths;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;

public class UserRestCalls {

    private static final String BASE_URL = Paths.BASE_URL;

    public static User login(String username, String password){
        String url = BASE_URL + Paths.LOGIN + "/" + username + "/" + password;

        if (android.os.Build.VERSION.SDK_INT >= 14)
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
                return new Gson().fromJson(response.toString(), User.class);
            }
            if (responseCode == HttpURLConnection.HTTP_CREATED){
                return null;
            }
        } catch(IOException ex ){
            ex.printStackTrace();
        }
        return null;
    }

    public static String forgotPassword(String email){
        String url = BASE_URL + Paths.FORGOT_PASSWORD + "/" + email;
        if (android.os.Build.VERSION.SDK_INT >= 14)
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
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED){
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

    public static String changePassword(String username, String oldPassword, String newPassword){
        String url = BASE_URL + Paths.CHANGE_PASSWORD+ "/" + username + "/" + oldPassword
                + "/" + newPassword;
        if (android.os.Build.VERSION.SDK_INT >= 14)
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
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED){
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


    public static String register (JSONObject user){
        String url =BASE_URL + Paths.REGISTER;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL urlForGetRequest = new URL(url);
            String readLine;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty( "Content-Type", "application/json" );
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.connect();

            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(user.toString());
            streamWriter.flush();
            streamWriter.close();

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

    public static Integer deleteAccount(String username){
        String url = BASE_URL + Paths.DELETE_ACCOUNT + "/" + username;
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            URL urlForGetRequest = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            return connection.getResponseCode();
        } catch(IOException ex ){
            ex.printStackTrace();
        }
        return -1;
    }

    public static String update(JSONObject user){
        String url =BASE_URL + Paths.UPDATE_PROFILE;
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
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty( "Content-Type", "application/json" );
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.connect();

            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(user.toString());
            streamWriter.flush();
            streamWriter.close();

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

    public static void upload(String username, Bitmap bitmap) throws IOException {
        String url = BASE_URL + Paths.UPLOAD + "/" + username;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] pixels = stream.toByteArray();
        bitmap.recycle();

    }

    public static List<Local> getAllLocals(){
        String url =BASE_URL + Paths.GET_ALL_LOCALS;
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
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty( "Content-Type", "application/json" );
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                Type listType = new TypeToken<List<Local>>(){}.getType();
                List<Local> locals = new Gson().fromJson(response.toString(), listType);
                return locals;
            }
        } catch(IOException ex ){
            ex.printStackTrace();
        }
        return null;
    }
    public static  List<Local> filterLocals(String text){
        String url =BASE_URL + Paths.FILTER_LOCALS + "/" + text;
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
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty( "Content-Type", "application/json" );
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                Type listType = new TypeToken<List<Local>>(){}.getType();
                List<Local> locals = new Gson().fromJson(response.toString(), listType);
                return locals;
            }
        } catch(IOException ex ){
            ex.printStackTrace();
        }
        return null;
    }
}

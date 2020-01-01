package com.example.freeforfun.ui.restCalls;

import android.os.StrictMode;

import com.example.freeforfun.ui.model.EVoteType;
import com.example.freeforfun.ui.model.FavouriteLocals;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.model.User;
import com.example.freeforfun.ui.utils.Paths;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.example.freeforfun.ui.login.MainActivity.loggedUser;

public class VoteRestCalls {
    private static final String BASE_URL = Paths.BASE_URL;

    public static String like(Long localId){

        String url = BASE_URL + Paths.LIKE + "/" + localId + "/" +loggedUser.getId() ;

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

    public static String dislike(Long localId){

        String url = BASE_URL + Paths.DISLIKE + "/" + localId + "/" +loggedUser.getId() ;
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

    public static FavouriteLocals getLocal(Long localId){
        Long userId = loggedUser.getId();
        String url = BASE_URL + Paths.GET_LOCAL + "/" + localId + "/" + userId;

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
                return new Gson().fromJson(response.toString(), FavouriteLocals.class);
            }
            if (responseCode == HttpURLConnection.HTTP_CREATED){
                return null;
            }
        } catch(IOException ex ){
            ex.printStackTrace();
        }
        return null;
    }
    public static FavouriteLocals deleteVote(Long localId){
        Long userId = loggedUser.getId();
        String url = BASE_URL + Paths.DELETE_VOTE + "/" + localId + "/" + userId;

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
                return new Gson().fromJson(response.toString(), FavouriteLocals.class);
            }
            if (responseCode == HttpURLConnection.HTTP_CREATED){
                return null;
            }
        } catch(IOException ex ){
            ex.printStackTrace();
        }
        return null;
    }

    public static List<FavouriteLocals> getAllVotedLocals(){
        String url =BASE_URL + Paths.GET_ALL_VOTED_LOCALS;
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
                Type listType = new TypeToken<List<FavouriteLocals>>(){}.getType();
                List<FavouriteLocals> locals = new Gson().fromJson(response.toString(), listType);
                return locals;
            }
        } catch(IOException ex ){
            ex.printStackTrace();
        }
        return null;
    }
}

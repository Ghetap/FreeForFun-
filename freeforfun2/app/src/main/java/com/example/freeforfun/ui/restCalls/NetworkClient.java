package com.example.freeforfun.ui.restCalls;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static Retrofit retrofit;
    private static String BASE_URL = "http://10.0.2.2:8080/free_for_fun/";

    public static Retrofit getRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        }
        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        NetworkClient.retrofit = retrofit;
    }
}

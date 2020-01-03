package com.example.freeforfun.ui.restCalls;

import com.example.freeforfun.ui.utils.Paths;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static Retrofit retrofit;
    private static String BASE_URL = Paths.BASE_URL+"/";

    public static Retrofit getRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        NetworkClient.retrofit = retrofit;
    }
}

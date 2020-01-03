package com.example.freeforfun.ui.restCalls;

import android.app.DownloadManager;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UploadApis {

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadImage(@Part("username") RequestBody username,@Part MultipartBody.Part part);

}

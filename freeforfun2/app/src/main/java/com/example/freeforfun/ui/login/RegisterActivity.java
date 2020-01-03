package com.example.freeforfun.ui.login;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.inputValidations.UserValidations;
import com.example.freeforfun.ui.restCalls.NetworkClient;
import com.example.freeforfun.ui.restCalls.UploadApis;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.example.freeforfun.ui.utils.FileUtils;
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONObject;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    private CircleImageView profileImage;
    Uri imageUri;
    Bitmap bitmap;
    private static final int PICK_IMAGE = 1;
    private static final int PERMISSION_REQUEST = 100;
    private Button registerButton;
    private EditText username;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private EditText mobilePhone;
    private EditText email;
    private String imageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(RegisterActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST);
        }

        profileImage = findViewById(R.id.profile_image);
        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        firstName = findViewById(R.id.editText_firstName);
        lastName = findViewById(R.id.editText_lastName);
        mobilePhone = findViewById(R.id.editText_mobileNumber);
        email = findViewById(R.id.editText_email);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                JSONObject jsonUser = new JSONObject();
                try {
                    jsonUser.put("firstName",firstName.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("lastName",lastName.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("password",password.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("email",email.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("mobileNumber",mobilePhone.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("username",username.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("role",0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    if(!UserValidations.validateName(jsonUser.getString("firstName")))
                    {
                        showSnackbar("First name must begin with capital letter and contain max 30 chracters" +
                                "!");
                    }
                    else if(!UserValidations.validateROPhoneNumber(jsonUser.getString("mobileNumber")))
                    {
                        showSnackbar("Invalid RO- mobile phone ");
                    }
                    else if(!UserValidations.validateName(jsonUser.getString("lastName")))
                    {
                        showSnackbar("Last name must begin with capital letter and contain max 30 chracters" +
                                "!");

                    } else if(!UserValidations.validateEmail(jsonUser.getString("email")))
                    {
                        showSnackbar("Email is not valid !");

                    } else if(!validatePassword(jsonUser.getString("password")))
                    {
                        showSnackbar("Password must contain at least 4 characters!");

                    }else if(!validateUsername(jsonUser.getString("username")))
                    {
                        showSnackbar("Username can contain only letters,digits, '_' and '.'!." +
                                "Username cannot contain space!");

                    }
                    else if(!UserValidations.validateRole(jsonUser.getString("role")))
                    {
                        showSnackbar("Role for users must be 0!");

                    }
                    else
                    {

                        String message = UserRestCalls.register(jsonUser);
                        uploadImage();
                        if(message != null)
                            showSnackbar(message);
                        else
                            showSnackbar("Registration didn't go well. Try again!");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
      profileImage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent gallery = new Intent();
              gallery.setType("image/*");
              gallery.setAction(Intent.ACTION_GET_CONTENT);
              startActivityForResult(Intent.createChooser(gallery,"select Picture"),PICK_IMAGE);

          }
      });
    }

    public void uploadImage(){
        if(imageUri != null) {
            try {
                File file = FileUtils.from(getApplicationContext(),imageUri);
                RequestBody userPart = RequestBody.create(MultipartBody.FORM, username.getText().toString());
                RequestBody filePart = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)),
                        file);

                MultipartBody.Part part =  MultipartBody.Part.createFormData("file",file.getName(),filePart);
                //create Retrofit instance
//                Retrofit.Builder builder = new Retrofit.Builder().
//                        baseUrl("http://192.168.1.106:8080/free_for_fun/").
//                        addConverterFactory(GsonConverterFactory.create());
//                Retrofit retrofit = builder.build();
                Retrofit retrofit = NetworkClient.getRetrofit();
                UploadApis uploadApis = retrofit.create(UploadApis.class);
                Call<ResponseBody> call = uploadApis.uploadImage(userPart,part);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            showSnackbar(response.toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
//            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void showSnackbar(String messageFromServer){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, messageFromServer, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void showHidePassword(View view){
        if(view.getId()==R.id.show_password_btn){
            if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hidepassword);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.showpassword);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    public boolean validateUsername(String usernameInput){
        if(!UserValidations.isNotEmpty(usernameInput)) {
            username.setError("Username is required!");
        }
        else {
            username.setError(null);
        }
        if(!UserValidations.containsOnlyLettersAndDigits(usernameInput)){
            username.setError("Username can contain only letters,digits, '_' and '.'!");
            return false;
        }
        else {
            username.setError(null);
        }
        if(!UserValidations.doesNotContainSpace(usernameInput)){
            username.setError("Username cannot contain space!");
            return false;
        }
        else {
            username.setError(null);
        }
        return true;
    }

    public boolean validatePassword(String passwordInput){
        if(!UserValidations.isNotEmpty(passwordInput)) {
            password.setError("Password is required!");
            return false;
        }
        else {
            password.setError(null);
        }
        if(!UserValidations.hasAtLeast3Charachters(passwordInput)){
            password.setError("Password must have at least 4 characters!");
            return false;
        }
        else {
            password.setError(null);
        }
        return true;
    }
    public String getPath(Uri uri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getApplicationContext().getContentResolver().query(uri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}

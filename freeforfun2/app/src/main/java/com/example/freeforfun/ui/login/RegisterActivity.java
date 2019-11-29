package com.example.freeforfun.ui.login;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
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
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONObject;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import de.hdodenhof.circleimageview.CircleImageView;


public class RegisterActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    private CircleImageView profileImage;
    Uri imageUri;
    Bitmap bitmap;
    private static final int PICK_IMAGE = 1;
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
                    if( validatePassword(jsonUser.getString("password")) && validateUsername(jsonUser.getString("username"))
                    && UserValidations.validateEmail(jsonUser.getString("email")) &&
                    UserValidations.validateName(jsonUser.getString("firstName")) &&
                    UserValidations.validateName(jsonUser.getString("lastName")) &&
                    UserValidations.validateROPhoneNumber(jsonUser.getString("mobileNumber")) &&
                    UserValidations.validateRole(jsonUser.getString("role"))){

                        String message = UserRestCalls.register(jsonUser);
                        if(message != null)
                            showSnackbar(message);
                        if(bitmap != null) {
                            try {
                                UserRestCalls.upload(username.getText().toString(),bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }else
                        showSnackbar("Registration didn't go well. Try again!");

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
    public void showSnackbar(String messageFromServer){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, messageFromServer, Snackbar.LENGTH_LONG);
        snackbar.show();
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
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

}

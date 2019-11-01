package com.example.freeforfun.ui.login;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.freeforfun.R;
import com.example.freeforfun.ui.inputValidations.UserValidations;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {

//    private LoginViewModel loginViewModel;
    private EditText username;
    private EditText password;
    private Button login;
    private TextView register;
    private TextView forgotPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        login = findViewById(R.id.button_login);
        register = findViewById(R.id.textView_register);
        forgotPassword = findViewById(R.id.textView_forgotPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPasswordIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPasswordIntent);
            }
        });
    }

    public void showHidePassword(View view){
        if(view.getId()==R.id.show_pass_btn){
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

    public void validateUsername(View view){
        String usernameInput = username.getEditableText().toString().trim();
        if(!UserValidations.isNotEmpty(usernameInput)) {
            username.setError("Username is required!");
        }
        else username.setError(null);
        if(!UserValidations.containsOnlyLettersAndDigits(usernameInput)){
            username.setError("Username can contain only letters and digits!");
        }
        else username.setError(null);
        if(!UserValidations.doesNotContainSpace(usernameInput)){
            username.setError("Username cannot contain space!");
        }
        else username.setError(null);
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
//                .get(LoginViewModel.class);
//
//        final EditText usernameEditText = findViewById(R.id.username);
//        final EditText passwordEditText = findViewById(R.id.password);
//        final Button loginButton = findViewById(R.id.login);
//        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
//
//        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
//            @Override
//            public void onChanged(@Nullable LoginFormState loginFormState) {
//                if (loginFormState == null) {
//                    return;
//                }
//                loginButton.setEnabled(loginFormState.isDataValid());
//                if (loginFormState.getUsernameError() != null) {
//                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
//                }
//                if (loginFormState.getPasswordError() != null) {
//                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
//                }
//            }
//        });
//
//        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
//            @Override
//            public void onChanged(@Nullable LoginResult loginResult) {
//                if (loginResult == null) {
//                    return;
//                }
//                loadingProgressBar.setVisibility(View.GONE);
//                if (loginResult.getError() != null) {
//                    showLoginFailed(loginResult.getError());
//                }
//                if (loginResult.getSuccess() != null) {
//                    updateUiWithUser(loginResult.getSuccess());
//                }
//                setResult(Activity.RESULT_OK);
//
//                //Complete and destroy login activity once successful
//                finish();
//            }
//        });
//
//        TextWatcher afterTextChangedListener = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // ignore
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // ignore
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
//            }
//        };
//        usernameEditText.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    loginViewModel.login(usernameEditText.getText().toString(),
//                            passwordEditText.getText().toString());
//                }
//                return false;
//            }
//        });
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
//            }
//        });
//    }
//
//    private void updateUiWithUser(LoggedInUserView model) {
//        String welcome = getString(R.string.welcome) + model.getDisplayName();
//        // TODO : initiate successful logged in experience
//        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
//    }
//
//    private void showLoginFailed(@StringRes Integer errorString) {
//        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
//    }
}

package com.example.reddit_clone_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reddit_clone_android.http.BackendHttpRequests;
import com.example.reddit_clone_android.model.UserRegisterDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout email;
    private TextInputLayout displayName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        username = findViewById(R.id.usernameTXT);
        password = findViewById(R.id.passwordTXT);
        email = findViewById(R.id.emailTXT);
        displayName = findViewById(R.id.displayNameTXT);
    }

    private boolean isValidUsername(String username){
        String regex = "[A-Za-z0-9_]{3,21}$";
        return Pattern.matches(regex, username);
    }

    private boolean isValidPassword(String password){
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        return Pattern.matches(regex, password);
    }

    private boolean isValidEmail(String email){
        String regex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
        return Pattern.matches(regex, email);
    }

    private boolean validateUsername(){
        String usernameValue = username.getEditText().getText().toString().trim();
        if(usernameValue.isEmpty()){
            username.setError("Field can't be empty");
            return false;
        }else if(!isValidUsername(usernameValue)){
            username.setError("Username must be at between 3 and 21 characters and can only use _ as special character");
            return false;
        }else {
            username.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordValue = password.getEditText().getText().toString().trim();
        if(passwordValue.isEmpty()){
            password.setError("Field can't be empty");
            return false;
        }else if(!isValidPassword(passwordValue)){
            password.setError("Password must contain minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character");
            return false;
        }else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailValue = email.getEditText().getText().toString().trim();
        if(emailValue.isEmpty()){
            email.setError("Field can't be empty");
            return false;
        }else if(!isValidEmail(emailValue)){
            email.setError("Not valid email format!");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }

    private boolean validateDisplayName(){
        String displayNameValue = displayName.getEditText().getText().toString().trim();
        if(displayNameValue.isEmpty()){
            displayName.setError("Field can't be empty");
            return false;
        }else if(displayNameValue.length() < 2){
            displayName.setError("Name must be at least 2 characters long");
            return false;
        }else{
            displayName.setError(null);
            return true;
        }
    }

    public void confirmInput(View view){
        if(!validateUsername() | !validatePassword() | !validateEmail() | !validateDisplayName()){
            return;
        }

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername(username.getEditText().getText().toString().trim());
        userRegisterDTO.setPassword(password.getEditText().getText().toString().trim());
        userRegisterDTO.setEmail(email.getEditText().getText().toString().trim());
        userRegisterDTO.setDisplayName(displayName.getEditText().getText().toString().trim());

        Call<UserRegisterDTO> register = BackendHttpRequests.getInstance().getUserService().register(userRegisterDTO);
        register.enqueue(new Callback<UserRegisterDTO>() {
            @Override
            public void onResponse(Call<UserRegisterDTO> call, Response<UserRegisterDTO> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }

            @Override
            public void onFailure(Call<UserRegisterDTO> call, Throwable t) {

            }
        });
    }
}

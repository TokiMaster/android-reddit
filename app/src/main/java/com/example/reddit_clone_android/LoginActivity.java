package com.example.reddit_clone_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reddit_clone_android.http.BackendHttpRequests;
import com.example.reddit_clone_android.model.UserLoginDTO;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout username;
    private TextInputLayout password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
    }

    private boolean validateUsername(){
        String usernameValue = username.getEditText().getText().toString().trim();
        if(usernameValue.isEmpty()){
            username.setError("Field can't be empty");
            return false;
        }
//            else if(!isValidUsername(usernameValue)){
//            username.setError("Username must be at between 3 and 21 characters and can only use _ as special character");
//            return false;
//      }
        else {
            username.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordValue = password.getEditText().getText().toString().trim();
        if(passwordValue.isEmpty()){
            password.setError("Field can't be empty");
            return false;
        }
//        else if(!isValidPassword(passwordValue)){
//            password.setError("Password must contain minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character");
//            return false;
//        }
        else {
            password.setError(null);
            return true;
        }
    }

    public void confirmInput(View view){
        if(!validateUsername() | !validatePassword()){
            return;
        }

        UserLoginDTO userLoginDTO = new UserLoginDTO(username.getEditText().getText().toString().trim(),
                password.getEditText().getText().toString().trim());

        Call<String> userLoginDTOCall = BackendHttpRequests.getInstance().getUserService().login(userLoginDTO);
        userLoginDTOCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    SharedPreferences preferences = getSharedPreferences("nesto", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(String.valueOf(R.string.jwt), response.body());
                    editor.apply();
                    System.out.println(preferences.getString("jwt", ""));
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("jwt", preferences.getString("jwt", ""));
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}

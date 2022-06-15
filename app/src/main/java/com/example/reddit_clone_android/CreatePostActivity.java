package com.example.reddit_clone_android;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reddit_clone_android.http.BackendHttpRequests;
import com.example.reddit_clone_android.model.CreatePostDTO;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity {

    private TextInputLayout title;
    private TextInputLayout text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);
        title = findViewById(R.id.titleTXT);
        text = findViewById(R.id.descriptionTXT);
    }

    private boolean validateTitle(){
        String titleValue = title.getEditText().getText().toString().trim();
        if(titleValue.isEmpty()){
            title.setError("Field can't be empty");
            return false;
        }else {
            title.setError(null);
            return true;
        }
    }

    private boolean validateDescription(){
        String descriptionValue = text.getEditText().getText().toString().trim();
        if(descriptionValue.isEmpty()){
            text.setError("Field can't be empty");
            return false;
        }else {
            text.setError(null);
            return true;
        }
    }

    public void confirmInput(View view){
        if(!validateTitle() | !validateDescription()){
            return;
        }

        CreatePostDTO createPostDTO = new CreatePostDTO(title.getEditText().getText().toString().trim(),
                text.getEditText().getText().toString().trim());

        SharedPreferences preferences = getSharedPreferences("nesto",Context.MODE_PRIVATE);
        String jwt = preferences.getString(String.valueOf(R.string.jwt), "");
        System.out.println("TOKEN: " + jwt);
        Call<CreatePostDTO> createPostDTOCall = BackendHttpRequests.getInstance().getPostService().createPost(jwt, createPostDTO);
        createPostDTOCall.enqueue(new Callback<CreatePostDTO>() {
            @Override
            public void onResponse(Call<CreatePostDTO> call, Response<CreatePostDTO> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(CreatePostActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<CreatePostDTO> call, Throwable t) {

            }
        });
    }
}

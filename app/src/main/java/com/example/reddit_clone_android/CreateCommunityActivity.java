package com.example.reddit_clone_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reddit_clone_android.http.BackendHttpRequests;
import com.example.reddit_clone_android.model.CreateCommunityDTO;
import com.example.reddit_clone_android.model.CreatePostDTO;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCommunityActivity extends AppCompatActivity {

    private TextInputLayout title;
    private TextInputLayout text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_community);
        title = findViewById(R.id.name);
        text = findViewById(R.id.descriptionComm);
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

        CreateCommunityDTO createCommunityDTO = new CreateCommunityDTO(title.getEditText().getText().toString().trim(),
                text.getEditText().getText().toString().trim());

        SharedPreferences preferences = getSharedPreferences("nesto", Context.MODE_PRIVATE);
        String jwt = preferences.getString(String.valueOf(R.string.jwt), "");
        System.out.println("TOKEN: " + jwt);
        Call<CreateCommunityDTO> createPostDTOCall = BackendHttpRequests.getInstance().getCommunityService().createCommunity(jwt, createCommunityDTO);
        createPostDTOCall.enqueue(new Callback<CreateCommunityDTO>() {
            @Override
            public void onResponse(Call<CreateCommunityDTO> call, Response<CreateCommunityDTO> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(CreateCommunityActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<CreateCommunityDTO> call, Throwable t) {

            }
        });
    }
}

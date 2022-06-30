package com.example.reddit_clone_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reddit_clone_android.http.BackendHttpRequests;
import com.example.reddit_clone_android.model.EditPostDTO;
import com.example.reddit_clone_android.model.Post;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPostActivity extends AppCompatActivity {

    private TextInputLayout text;
    private Post post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_post);
        text = findViewById(R.id.postDescription);
        Intent intent = getIntent();
        Long id = intent.getLongExtra("id", 0);
        Call<Post> postCall = BackendHttpRequests.getInstance().getPostService().findPost(id);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                post = response.body();
                text.getEditText().setText(post.getText());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private boolean validateDescription() {
        String textValue = text.getEditText().getText().toString().trim();
        if (textValue.isEmpty()) {
            text.setError("Field can't be empty");
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }

    public void confirmInput(View view) {
        if (!validateDescription()) {
            return;
        }

        EditPostDTO editPostDTO = new EditPostDTO(post.getId(), text.getEditText().getText().toString().trim());

        SharedPreferences preferences = getSharedPreferences("nesto", Context.MODE_PRIVATE);
        String jwt = preferences.getString(String.valueOf(R.string.jwt), "");
        if(editPostDTO.getText() != null){
            post.setText(editPostDTO.getText());
        }

        Call<Post> editPost = BackendHttpRequests.getInstance().getPostService().editPost(jwt, post.getId(), editPostDTO);
        editPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(EditPostActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}


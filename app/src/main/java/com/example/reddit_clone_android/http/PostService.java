package com.example.reddit_clone_android.http;

import com.example.reddit_clone_android.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {

    @GET("api/posts")
    Call<List<Post>> getAllPosts();
}

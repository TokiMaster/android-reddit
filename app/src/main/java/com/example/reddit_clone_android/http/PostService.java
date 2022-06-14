package com.example.reddit_clone_android.http;

import com.example.reddit_clone_android.model.CreatePostDTO;
import com.example.reddit_clone_android.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostService {

    @GET("api/posts")
    Call<List<Post>> getAllPosts();

    @POST("api/posts")
    Call<CreatePostDTO> createPost(@Body CreatePostDTO createPostDTO);
}

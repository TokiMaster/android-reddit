package com.example.reddit_clone_android.http;

import com.example.reddit_clone_android.model.CreatePostDTO;
import com.example.reddit_clone_android.model.EditPostDTO;
import com.example.reddit_clone_android.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostService {

    @GET("api/posts")
    Call<List<Post>> getAllPosts();

    @POST("api/posts")
    Call<CreatePostDTO> createPost(@Header("Authorization") String jwtToken, @Body CreatePostDTO createPostDTO);

    @DELETE("api/posts/{id}")
    Call<Post> deletePost(@Header("Authorization") String jwtToken, @Path("id") Long id);

    @GET("api/posts/{id}")
    Call<Post> findPost(@Path("id") Long id);

    @PUT("api/posts/{id}")
    Call<Post> editPost(@Header("Authorization") String jwtToken, @Path("id") Long id, @Body EditPostDTO editPostDTO);
}

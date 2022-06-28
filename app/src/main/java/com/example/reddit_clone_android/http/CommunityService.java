package com.example.reddit_clone_android.http;

import com.example.reddit_clone_android.model.CommunityDTO;
import com.example.reddit_clone_android.model.CreateCommunityDTO;
import com.example.reddit_clone_android.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommunityService {

    @GET("api/communities")
    Call<List<CommunityDTO>> getAllCommunities();

    @POST("api/communities")
    Call<CreateCommunityDTO> createCommunity(@Header("Authorization") String jwtToken, @Body CreateCommunityDTO createCommunityDTO);

    @GET("api/communities/{id}/posts")
    Call<List<Post>> getCommunitiesPosts(@Path("id") Long id);

    @GET("api/communities/{id}")
    Call<CommunityDTO> findCommunity(@Path("id") Long id);
}

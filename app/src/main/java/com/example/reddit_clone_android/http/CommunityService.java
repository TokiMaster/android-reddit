package com.example.reddit_clone_android.http;

import com.example.reddit_clone_android.model.CommunityDTO;
import com.example.reddit_clone_android.model.CreateCommunityDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CommunityService {

    @GET("api/communities")
    Call<List<CommunityDTO>> getAllCommunities();

    @POST("api/communities")
    Call<CreateCommunityDTO> createCommunity(@Header("Authorization") String jwtToken, @Body CreateCommunityDTO createCommunityDTO);
}

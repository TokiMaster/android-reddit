package com.example.reddit_clone_android.http;

import com.example.reddit_clone_android.model.UserRegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("api/users/register")
    Call<UserRegisterDTO> register(@Body UserRegisterDTO userRegisterDTO);

}

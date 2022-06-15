package com.example.reddit_clone_android.http;

import static java.security.AccessController.getContext;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.reddit_clone_android.LoginActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BackendHttpRequests {

    private static final String BASE_URL = "http://192.168.199.228:8080/";
    private final Retrofit retrofit;
//    static SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//    private static final String JWT = preferences.getString("jwt", "");

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private static BackendHttpRequests instance;

    public static BackendHttpRequests getInstance(){
        if (instance == null) {
            return new BackendHttpRequests();
        }else{
            return instance;
        }
    }

    public BackendHttpRequests(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest  = chain.request().newBuilder()
//                        .addHeader("Authorization", "Bearer " + JWT )
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        }).build();

        Retrofit retroFit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        this.retrofit = retroFit;
        this.postService = retroFit.create(PostService.class);
        this.userService = retroFit.create(UserService.class);
    }

    private PostService postService;

    public PostService getPostService() {
        return postService;
    }

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }
}

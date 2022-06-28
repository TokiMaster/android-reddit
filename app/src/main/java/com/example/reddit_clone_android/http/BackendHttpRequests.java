package com.example.reddit_clone_android.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BackendHttpRequests {

    private static final String BASE_URL = "http://192.168.180.228:8080/";
    private final Retrofit retrofit;

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

        Retrofit retroFit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        this.retrofit = retroFit;
        this.postService = retroFit.create(PostService.class);
        this.userService = retroFit.create(UserService.class);
        this.communityService = retroFit.create(CommunityService.class);
    }

//    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
//        @Override
//        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//            Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
//            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//        }
//    }).create();

    private PostService postService;

    public PostService getPostService() {
        return postService;
    }

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    private CommunityService communityService;

    public CommunityService getCommunityService() {
        return communityService;
    }
}

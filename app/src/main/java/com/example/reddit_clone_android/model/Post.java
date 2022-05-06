package com.example.reddit_clone_android.model;

public class Post {
    private int avatar;
    private String username;
    private String description;
    private int img;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Post(int avatar, String username, String description, int img) {
        this.avatar = avatar;
        this.username = username;
        this.description = description;
        this.img = img;
    }
}

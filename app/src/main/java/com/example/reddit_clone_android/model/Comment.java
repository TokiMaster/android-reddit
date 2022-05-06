package com.example.reddit_clone_android.model;

public class Comment {
    private int avatar;
    private String username;
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment(int avatar, String username, String comment) {
        this.avatar =avatar;
        this.username = username;
        this.comment = comment;
    }
}

package com.example.reddit_clone_android.model;

public class Post {
    private String username;
    private String text;
    private String title;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Post(String username, String text, String title) {
        this.username = username;
        this.text = text;
        this.title = title;
    }
}

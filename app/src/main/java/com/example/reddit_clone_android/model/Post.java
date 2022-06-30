package com.example.reddit_clone_android.model;

public class Post {
    private Long id;
    private String username;
    private String text;
    private String title;
    private int karma;

    public Post(Long id, String username, String text, String title, int karma) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.title = title;
        this.karma = karma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }
}

package com.example.reddit_clone_android.model;

public class CreatePostDTO {

    private Long communityId;
    private String title;
    private String text;
    private String username;

    public CreatePostDTO(){

    }

    public CreatePostDTO(String title, String text) {
        this.communityId = 1L;
        this.title = title;
        this.text = text;
        this.username = "Toki";
    }

    public CreatePostDTO(Long communityId, String title, String text, String username) {
        this.communityId = communityId;
        this.title = title;
        this.text = text;
        this.username = username;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.example.reddit_clone_android.model;

public class CreateCommunityDTO {

    private String name;
    private String description;

    public CreateCommunityDTO() {
    }

    public CreateCommunityDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

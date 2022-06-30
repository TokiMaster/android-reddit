package com.example.reddit_clone_android.model;

public class ReactionDTO {

    private Long postID;
    private ReactionType type;

    public ReactionDTO(Long postID, ReactionType type) {
        this.postID = postID;
        this.type = type;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }
}

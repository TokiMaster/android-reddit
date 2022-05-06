package com.example.reddit_clone_android.model;

import com.example.reddit_clone_android.R;

import java.util.ArrayList;
import java.util.List;

public class Mockup {
    public static List<Post> getPosts(){
        ArrayList<Post> posts = new ArrayList<Post>();
        Post p1 = new Post(R.drawable.ic_launcher_background, "Toki", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Diam quis enim lobortis scelerisque fermentum dui faucibus. Senectus et netus et malesuada fames ac. Nulla pharetra diam sit amet nisl suscipit. Nulla aliquet enim tortor at auctor urna nunc. Enim ut tellus elementum sagittis vitae et leo.", R.drawable.test);
        Post p2 = new Post(R.drawable.ic_launcher_foreground, "Vlaki", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",-1);
        Post p3 = new Post(R.drawable.ic_action_accept, "PeraCar", "Something something",R.drawable.proba);

        posts.add(p1);
        posts.add(p2);
        posts.add(p3);

        return posts;
    }

    public static List<Comment> getComments(){
        ArrayList<Comment> comments = new ArrayList<>();
        Comment c1 = new Comment(R.drawable.ic_launcher_background,"Toki", "Great picture!");
        Comment c2 = new Comment(R.drawable.ic_launcher_foreground,"Vlaki", "Omg soo cuteee");
        Comment c3 = new Comment(R.drawable.ic_action_accept,"Pera", "So fluffy i wanna die");

        comments.add(c1);
        comments.add(c2);
        comments.add(c3);

        return comments;
    }

}

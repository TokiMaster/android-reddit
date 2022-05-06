package model;

import com.example.reddit_clone_android.R;

import java.util.ArrayList;
import java.util.List;

public class Mockup {
    public static List<Post> getPosts(){
        ArrayList<Post> posts = new ArrayList<Post>();
        Post p1 = new Post(R.drawable.ic_launcher_background, "Toki", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Diam quis enim lobortis scelerisque fermentum dui faucibus. Senectus et netus et malesuada fames ac. Nulla pharetra diam sit amet nisl suscipit. Nulla aliquet enim tortor at auctor urna nunc. Enim ut tellus elementum sagittis vitae et leo.", R.drawable.test);
        Post p2 = new Post(R.drawable.ic_launcher_foreground, "Vlaki", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", R.drawable.proba);
        Post p3 = new Post(R.drawable.ic_action_accept, "Tradicionalni u mirnom ambijentu", "Something something",R.drawable.ic_action_network_wifi);

        posts.add(p1);
        posts.add(p2);
        posts.add(p3);

        return posts;
    }
}

package com.example.reddit_clone_android.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.reddit_clone_android.R;

import com.example.reddit_clone_android.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends BaseAdapter {
    private Activity activity;
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public PostAdapter(Activity activity, List<Post> posts){
        this.activity = activity;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return this.posts.size();
    }

    @Override
    public Object getItem(int position) {
        return this.posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View vi = view;
        Post post = this.posts.get(position);

        if(view == null){
            vi = activity.getLayoutInflater().inflate(R.layout.post_list, null);
        }

        TextView username = vi.findViewById(R.id.username);
        TextView description = vi.findViewById(R.id.description);

        username.setText(post.getUsername());
        description.setText(post.getText());

        return vi;
    }
}

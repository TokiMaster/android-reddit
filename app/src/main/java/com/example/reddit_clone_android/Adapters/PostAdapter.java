package com.example.reddit_clone_android.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.reddit_clone_android.EditPostActivity;
import com.example.reddit_clone_android.R;

import com.example.reddit_clone_android.SecondActivity;
import com.example.reddit_clone_android.http.BackendHttpRequests;
import com.example.reddit_clone_android.model.CreatePostDTO;
import com.example.reddit_clone_android.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends BaseAdapter {
    private Activity activity;
    private List<Post> posts;

    public Activity getActivity() {
        return activity;
    }

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
        TextView titleGet = vi.findViewById(R.id.titleGet);
        Button edit = vi.findViewById(R.id.button_edit);
        edit.setText(R.string.edit);
        Button delete = vi.findViewById(R.id.button_delete);
        delete.setText(R.string.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getBaseContext().getSharedPreferences("nesto", Context.MODE_PRIVATE);
                String jwt = preferences.getString(String.valueOf(R.string.jwt), "");
                System.out.println("TOKEN: " + jwt);
                Call<Post> deletePost = BackendHttpRequests.getInstance().getPostService().deletePost(jwt, post.getId());
                deletePost.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {

                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditPostActivity.class);
                intent.putExtra("id", post.getId());
                activity.startActivity(intent);
            }
        });

        username.setText(post.getUsername());
        description.setText(post.getText());
        titleGet.setText(post.getTitle());

        return vi;
    }
}

package com.example.reddit_clone_android.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.reddit_clone_android.DetailActivity;
import com.example.reddit_clone_android.MainActivity;
import com.example.reddit_clone_android.R;

import com.example.reddit_clone_android.model.Mockup;
import com.example.reddit_clone_android.model.Post;

public class PostAdapter extends BaseAdapter {
    private Activity activity;
    public PostAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Mockup.getPosts().size();
    }

    @Override
    public Object getItem(int position) {
        return Mockup.getPosts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View vi = view;
        Post post = Mockup.getPosts().get(position);

        if(view == null){
            vi = activity.getLayoutInflater().inflate(R.layout.post_list, null);
        }

        ImageView avatar = vi.findViewById(R.id.avatar);
        TextView username = vi.findViewById(R.id.username);
        TextView description = vi.findViewById(R.id.description);
        ImageView photo = vi.findViewById(R.id.photo);
        Button button = vi.findViewById(R.id.button_details);

        if(post.getAvatar() != -1){
            avatar.setImageResource(post.getAvatar());
        }

        username.setText(post.getUsername());
        description.setText(post.getDescription());

        if(post.getImg() != -1){
            photo.setImageResource(post.getImg());
            photo.setVisibility(View.VISIBLE);
        }

        button.setText(R.string.action_1);
        LinearLayout linearLayout = vi.findViewById(R.id.card);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("avatar", post.getAvatar());
                intent.putExtra("username", post.getUsername());
                intent.putExtra("description", post.getDescription());
                intent.putExtra("img", post.getImg());
                context.startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("avatar", post.getAvatar());
                intent.putExtra("username", post.getUsername());
                intent.putExtra("description", post.getDescription());
                intent.putExtra("img", post.getImg());
                context.startActivity(intent);
            }
        });

        return vi;
    }
}

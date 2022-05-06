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

import com.example.reddit_clone_android.model.Comment;
import com.example.reddit_clone_android.model.Mockup;
import com.example.reddit_clone_android.model.Post;

public class CommentsAdapter extends BaseAdapter {

    private Activity activity;
    public CommentsAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Mockup.getComments().size();
    }

    @Override
    public Object getItem(int position) {
        return Mockup.getComments().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View vi = view;
        Comment comment = Mockup.getComments().get(position);

        if(view == null){
            vi = activity.getLayoutInflater().inflate(R.layout.comment_list, null);
        }

        ImageView avatar = vi.findViewById(R.id.avatar_comment);
        TextView username = vi.findViewById(R.id.username_comment);
        TextView description = vi.findViewById(R.id.comment_text);

        if(comment.getAvatar() != -1){
            avatar.setImageResource(comment.getAvatar());
        }

        username.setText(comment.getUsername());
        description.setText(comment.getComment());

        return vi;
    }
}

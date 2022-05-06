package com.example.reddit_clone_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reddit_clone_android.fragments.CommentFragment;
import com.example.reddit_clone_android.fragments.FragmentTransition;
import com.example.reddit_clone_android.fragments.PostFragments;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        Intent intent = getIntent();
        ImageView avatar = findViewById(R.id.avatar);
        TextView usernameT = findViewById(R.id.username);
        TextView textView = findViewById(R.id.description);
        ImageView imageView = findViewById(R.id.photo);
        avatar.setImageResource(intent.getIntExtra("avatar", 0));
        String username = intent.getStringExtra("username");
        String text = intent.getStringExtra("description");
        usernameT.setText(username);
        textView.setText(text);
        if(intent.getIntExtra("img",0) != -1){
            imageView.setImageResource(intent.getIntExtra("img", 0));
        }
        FragmentTransition.to(CommentFragment.newInstance(), this, false);
    }
}

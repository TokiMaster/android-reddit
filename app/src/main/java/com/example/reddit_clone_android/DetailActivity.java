package com.example.reddit_clone_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String text = intent.getStringExtra("description");
        TextView usernameT = findViewById(R.id.username);
        TextView textView = findViewById(R.id.description);
        ImageView imageView = findViewById(R.id.photo);
        usernameT.setText(username);
        textView.setText(text);
        imageView.setImageResource(intent.getIntExtra("img", 0));
    }
}

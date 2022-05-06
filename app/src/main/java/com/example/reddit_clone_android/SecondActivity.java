package com.example.reddit_clone_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_map);
        Intent intent = getIntent();
        String text = intent.getStringExtra("description");
        TextView textView = findViewById(R.id.description);
        ImageView imageView = findViewById(R.id.photo);
        imageView.setImageResource(intent.getIntExtra("img", 0));
        textView.setText(text);
//        if(getIntent().hasExtra("byteArray")) {
//            ImageView imageView = findViewById(R.id.details);
//            Bitmap _bitmap = BitmapFactory.decodeByteArray(
//                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
//            imageView.setImageBitmap(_bitmap);
//        }

    }
}

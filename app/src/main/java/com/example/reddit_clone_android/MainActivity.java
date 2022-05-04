package com.example.reddit_clone_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button button = findViewById(R.id.comment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.description);
                String text = textView.getText().toString();
                ImageView imageView = findViewById(R.id.details);
//                imageView.invalidate();
//                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
//                Bitmap _bitmap = drawable.getBitmap();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("description", text);
                intent.putExtra("img", R.drawable.test);
                startActivity(intent);
            }
        });
    }
}

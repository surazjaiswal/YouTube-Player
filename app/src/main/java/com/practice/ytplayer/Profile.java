package com.practice.ytplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    ImageView imageView;
    TextView tv_name, tv_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.imgView);
        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        Bitmap bitmap = intent.getParcelableExtra("img");

        tv_name.setText(name);
        tv_email.setText(email);
        imageView.setImageBitmap(bitmap);


    }
}
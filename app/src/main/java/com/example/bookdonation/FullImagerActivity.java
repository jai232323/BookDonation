package com.example.bookdonation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullImagerActivity extends AppCompatActivity {


    private ImageView FullImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_imager);


        FullImageView=findViewById(R.id.FullImageView);

        String image = getIntent().getStringExtra("image");

        Glide.with(this).load(image).into(FullImageView);
    }
}
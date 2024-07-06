package com.jktech.community_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);

        // Initialize views
        ImageView imageView = findViewById(R.id.abtImgView);
        TextView textView = findViewById(R.id.abtTxtView);

        // You can add validation here if needed
        // For example, checking if the text in TextView is not empty or if ImageView is set to a valid image resource
    }
}


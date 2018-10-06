package com.example.android.movieapponetestone.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movieapponetestone.R;

public class DetailActivity extends AppCompatActivity {

    int id;
    TextView title;
    TextView userRating;
    TextView releaseDate;
    TextView synopsis;
    ImageView posterImage;

    String api_key = "5571cb8edc0c8203b51ddfb985abd954";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.tv_title);
        userRating = findViewById(R.id.user_rating);
        releaseDate = findViewById(R.id.release_date);
        synopsis = findViewById(R.id.synopsis);
        posterImage = findViewById(R.id.poster_image);

        Intent intent = getIntent();
        id = intent.getIntExtra("Movie ID", 0);


    }


}

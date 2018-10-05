package com.example.android.movieapponetestone.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.movieapponetestone.R;
import com.example.android.movieapponetestone.adapter.ModelAdapter;
import com.example.android.movieapponetestone.api.App;
import com.example.android.movieapponetestone.model.popular.Popular;
import com.example.android.movieapponetestone.model.popular.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList <Result> modelArrayList;

    public static final String KEY = "5571cb8edc0c8203b51ddfb985abd954";

    private RecyclerView recyclerView;
    private ModelAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_list);

        recyclerView = findViewById(R.id.rv_model);
        modelArrayList = new ArrayList<>();
        adapter = new ModelAdapter(modelArrayList, getApplicationContext());

        int numberOfColumns = 2;

        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerView.setAdapter(adapter);
        getImages();

    }

    private void getImages() {
        App.getApi().getPopularMovies(KEY).enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                modelArrayList.clear();
                adapter.setData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {

            }
        });
    }
}

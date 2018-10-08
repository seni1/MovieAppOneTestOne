package com.example.android.movieapponetestone.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.movieapponetestone.R;
import com.example.android.movieapponetestone.adapter.ModelAdapter;
import com.example.android.movieapponetestone.api.App;
import com.example.android.movieapponetestone.model.Popular;
import com.example.android.movieapponetestone.model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList <Popular> popularArrayList;

    public static final String KEY = "5571cb8edc0c8203b51ddfb985abd954";

    private RecyclerView recyclerView;
    private ModelAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_list);

        recyclerView = findViewById(R.id.rv_model);

        popularArrayList = new ArrayList<>();

        adapter = new ModelAdapter(popularArrayList, getApplicationContext());

        int numberOfColumns = 2;

        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        recyclerView.setAdapter(adapter);

        getPopular();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.popular:
                getPopular();
                return true;
            case R.id.top_rated:
                getTopRatedMovies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void getPopular() {

    App.getApi().getPopularMovies(KEY).enqueue(new Callback<Result>() {
        @Override
        public void onResponse(Call<Result> call, Response<Result> response) {
            popularArrayList.clear();
            adapter.setData(response.body().getResults());
        }

        @Override
        public void onFailure(Call<Result> call, Throwable t) {

        }
    });
    }

    private void getTopRatedMovies() {

        App.getApi().getTopRatedMovies(KEY).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                popularArrayList.clear();
                adapter.setData(response.body().getResults());

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();

            }
        });
    }
}

package com.yongki.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.yongki.movielist.adapter.RecyclerAdapter;
import com.yongki.movielist.model.BaseAPIResponse;
import com.yongki.movielist.service.ApiService;
import com.yongki.movielist.service.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemListener{

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    TextView name, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        recyclerView = findViewById(R.id.myRecyclerview);

        adapter = new RecyclerAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieService service = ApiService.createService(
            "https://api.themoviedb.org/3/"
        );

        service.getMovieData("3461e2617bb51249335ac494bc3bafab", "", "1", "").enqueue(new Callback<BaseAPIResponse>() {
            @Override
            public void onResponse(Call<BaseAPIResponse> call, Response<BaseAPIResponse> response) {
                adapter.setData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<BaseAPIResponse> call, Throwable t) {

            }
        });
//

    }

    @Override
    public void onItemClick(int position) {

    }
}
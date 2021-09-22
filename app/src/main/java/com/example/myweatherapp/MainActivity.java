package com.example.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.example.myweatherapp.network.ApiManager;
import com.example.myweatherapp.adapter.HourAdapter;
import com.example.myweatherapp.model.Weather;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHour;
    private TextView tvTem;
    private TextView tvStatus;
    private final List<Weather> weatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTem = findViewById(R.id.tvTem);
        tvStatus = findViewById(R.id.tvStatus);

        getHours();

        HourAdapter mAdapter = new HourAdapter(MainActivity.this, weatherList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvHour = findViewById(R.id.rvHour);
        rvHour.setLayoutManager(layoutManager);
        rvHour.setAdapter(mAdapter);
    }

    private void getHours() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiManager service = retrofit.create(ApiManager.class);
        service.getHour().enqueue(new Callback<List<Weather>>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<Weather>> call, @NonNull Response<List<Weather>> response) {
                if (response.body() == null) return;

                List<Weather> listWeather = response.body();
//                HourAdapter adapter = new HourAdapter(MainActivity.this, listWeather);
//                rvHour.setAdapter(adapter);

                Weather Weather = listWeather.get(0);
                tvTem.setText(Weather.getTemperature().getValue().intValue() + "");
                tvStatus.setText(Weather.getIconPhrase());
            }

            @Override
            public void onFailure(@NonNull Call<List<Weather>> call, @NonNull Throwable t) {

            }
        });
    }
}

package com.example.myweatherapp.network;

import com.example.myweatherapp.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    public static String BASE_URL = "http://dataservice.accuweather.com";

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=i7W0fwKIM6LULbbIoqwBO2aq3oMz2A8y&language=vi-vn@metric=true")
    Call<List<Weather>> getHour();

    @GET("/forecasts/v1/daily/12hour/353412?apikey=i7W0fwKIM6LULbbIoqwBO2aq3oMz2A8y&language=vi-vn@metric=true")
    Call<List<Weather>> getDay();

}

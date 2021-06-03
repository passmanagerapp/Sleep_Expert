package com.se.sleepexpert;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicService {
    private final String BASE_URL ="https://data.exploringelectric.site/SleepExpert/";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private final MusicAPi api = retrofit.create(MusicAPi.class);
    Call<List<String>> callRain = api.getRainMusic();
    Call<List<String>> callSea = api.getSeaMusic();
    Call<List<String>> callNature = api.getNatureMusic();
}

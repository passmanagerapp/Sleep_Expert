package com.se.sleepexpert;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MusicAPi {

    @GET("getNature")
    Call<List<String>> getNatureMusic();

    @GET("getRain")
    Call<List<String>> getRainMusic();

    @GET("getSea")
    Call<List<String>> getSeaMusic();
}

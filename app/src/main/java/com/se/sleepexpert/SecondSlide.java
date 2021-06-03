package com.se.sleepexpert;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.se.sleepexpert.databinding.FragmentSecondSlideBinding;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SecondSlide extends Fragment {


    private FragmentSecondSlideBinding binding;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mediaPlayer = new MediaPlayer();
        binding = FragmentSecondSlideBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MusicService service = new MusicService();

        Call<List<String>> call = service.callRain;


        binding.fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNetwork(call, 0);

            }
        });

        binding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNetwork(call, 1);

            }
        });
        binding.fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNetwork(call, 2);

            }
        });
        binding.fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNetwork(call, 3);

            }
        });



    }

    private void callNetwork(Call<List<String>> call, Integer number) {
        call.clone().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
                List<String> musicList = response.body();
                String url = musicList.get(number);
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                    mediaPlayer.start();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("MusicsList", url);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        mediaPlayer.release();
    }
}
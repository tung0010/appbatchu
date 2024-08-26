package com.example.bai.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.bai.databinding.StartscreenBinding;
import com.example.bai.utilities.Constants;
import com.example.bai.utilities.PreferenceManager;

public class MainActivityScreen extends AppCompatActivity {
    StartscreenBinding binding;
    public static MediaPlayer mediaPlayer;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = StartscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(this);
        binding.kyluc.setText(String.valueOf(preferenceManager.getkyluc(Constants.KEY_KYLUC)));
        binding.btnplay.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), PlayScreen.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.kyluc.setText(String.valueOf(preferenceManager.getkyluc(Constants.KEY_KYLUC)));
    }
}
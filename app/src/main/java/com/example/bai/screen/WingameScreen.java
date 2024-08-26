package com.example.bai.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bai.databinding.WingameBinding;

public class WingameScreen extends AppCompatActivity {
    WingameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=WingameBinding.inflate(getLayoutInflater());
        binding.ok.setOnClickListener(view -> {
            finish();
        });
        setContentView(binding.getRoot());
    }
}
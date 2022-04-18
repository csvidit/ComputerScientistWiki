package com.viditkhandelwal.computerscientistbiography;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.viditkhandelwal.computerscientistbiography.databinding.ActivityBiographyBinding;

public class BiographyActivity extends AppCompatActivity {

    ActivityBiographyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biography);
        binding = ActivityBiographyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
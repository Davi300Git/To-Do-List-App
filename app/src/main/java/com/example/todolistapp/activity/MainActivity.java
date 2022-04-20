package com.example.todolistapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todolistapp.R;
import com.example.todolistapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instancia o binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // set na contentView a raiz (root) do binding
        setContentView(binding.getRoot());
    }
}
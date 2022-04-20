package com.example.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistapp.R;
import com.example.todolistapp.databinding.FragmentCadSubtarefaBinding;

public class CadSubtarefaFragment extends Fragment {
    private FragmentCadSubtarefaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCadSubtarefaBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();

    }
}
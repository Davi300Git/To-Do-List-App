package com.example.todolistapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistapp.R;
import com.example.todolistapp.databinding.FragmentDetalheTarefaBinding;
import com.example.todolistapp.model.Tarefa;

public class DetalheTarefaFragment extends Fragment {

    private FragmentDetalheTarefaBinding binding;

    // variável para a tarefa
    Tarefa tarefa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalheTarefaBinding.inflate(getLayoutInflater(), container, false);

        binding.btNovoDetalhe.setOnClickListener(v -> {
            NavHostFragment.findNavController
                    (DetalheTarefaFragment.this).navigate(R.id.action_detalheTarefaFragment_to_cadSubtarefaFragment);
        });

        // verifica se foi passado algo no bundle
        if (getArguments() != null) {
            // recupera a tarefa do bundle
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");

        }
        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}
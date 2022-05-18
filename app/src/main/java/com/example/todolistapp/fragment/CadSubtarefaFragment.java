package com.example.todolistapp.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistapp.R;
import com.example.todolistapp.databinding.FragmentCadSubtarefaBinding;
import com.example.todolistapp.model.Tarefa;

public class CadSubtarefaFragment extends Fragment {
    private FragmentCadSubtarefaBinding binding;
    private Tarefa tarefa;
    // variável para o dialog
    private AlertDialog dialogFoto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCadSubtarefaBinding.inflate(getLayoutInflater(), container, false);

        //habilita o menu
        setHasOptionsMenu(true);

        // instancia o dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.origem_imagem);
        String[] opcoes = {getString(R.string.camera), getString(R.string.galeria)};
        builder.setItems(opcoes, listenerDialog);
        dialogFoto = builder.create();

        // listener do click da imagem
        binding.fotoSubTarefa.setOnClickListener(v -> {
            dialogFoto.show();
        });

        // retornar a view raiz (root) do binding
        return binding.getRoot();

    }
    // listener disparado ao escolher uma opção no dialog
    private DialogInterface.OnClickListener listenerDialog = (dialog, i) -> {

    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_subtarefa, menu);
    }
}
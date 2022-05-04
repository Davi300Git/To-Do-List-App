package com.example.todolistapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistapp.R;
import com.example.todolistapp.adapter.TarefaAdapter;
import com.example.todolistapp.database.AppDatabase;
import com.example.todolistapp.databinding.FragmentPrincipalBinding;
import com.example.todolistapp.model.Tarefa;

import java.util.List;

public class PrincipalFragment extends Fragment {
    private FragmentPrincipalBinding binding;

    // varaável para a lista
    private List<Tarefa> tarefa;

    // variável para o adapter
    private TarefaAdapter adapter;

    // variável para a database
    private AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentPrincipalBinding.inflate(getLayoutInflater(),container, false);

        binding.btNovaTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController
                    (PrincipalFragment.this).navigate(R.id.action_principalFragment3_to_cadTarefaFragment);
        });

       // retorna a view raiz (root) do binding
       return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>> {

        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            super.onPostExecute(tarefas);
        }
    }
}
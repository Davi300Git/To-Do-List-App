package com.example.todolistapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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
    private List<Tarefa> tarefas;

    // variável para o adapter
    private TarefaAdapter adapter;

    // variável para a database
    private AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentPrincipalBinding.inflate(getLayoutInflater(), container, false);

        binding.btNovaTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController
                    (PrincipalFragment.this).navigate(R.id.action_principalFragment3_to_cadTarefaFragment);
        });

        // instancia a database
        database = AppDatabase.getDatabase(getContext());

        //define o layout manager do recycler
        binding.recyclerTarefas.setLayoutManager(new LinearLayoutManager(getContext()));

        // executar a sua asynctask
        new ReadTarefa().execute();

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>> {

        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            // buscar as tarefas e guardar na variável tarefas
            tarefas = database.getTarefaDao().getAll();
            return tarefas;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            // instancia o adapter
            adapter = new TarefaAdapter(tarefas, getContext(), listenerClick);

            //aplicar o adapter no recycler
            binding.recyclerTarefas.setAdapter(adapter);
        }
    }

    // listener para click nas tarefas
    private TarefaAdapter.OnTarefaClickListener listenerClick = (view, tarefa) -> {
        // variável para pendurar a tarefa
        Bundle bundle = new Bundle();

        //pendura a tarefa no bundle
        bundle.putSerializable("tarefa", tarefa);

        //navega para o fragment de detalhes
        NavHostFragment.findNavController(PrincipalFragment.this).navigate
                (R.id.action_principalFragment3_to_detalheTarefaFragment, bundle);
    };

}
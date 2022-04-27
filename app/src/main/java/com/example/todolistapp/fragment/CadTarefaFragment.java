package com.example.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.todolistapp.R;
import com.example.todolistapp.database.AppDatabase;
import com.example.todolistapp.databinding.FragmentCadTarefa1Binding;
import com.example.todolistapp.model.Tarefa;

import java.util.Calendar;

public class CadTarefaFragment extends Fragment {
    private FragmentCadTarefa1Binding binding;

    private DatePickerDialog datePicker;
    // variaveis para ano,mes e dia
    int year, month, day;

    //variavel para obter a data atual
    Calendar dataAtual;

    // variavel para a data formatada
    String dataFormatada = "";

    //variável para a database
    AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //instancia a database
        database = AppDatabase.getDatabase(getContext());

        //instancia o binding
        binding = FragmentCadTarefa1Binding.inflate(getLayoutInflater(), container, false);

        // instanciar a data atual
        dataAtual = Calendar.getInstance();

        //obter ano, mes e dia, data atual
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        //instanciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia ) -> {
         // ao escolher uma data no datePicker, cai aqui
         //passar para as variáveis globais
         year = ano;
         month = mes;
         day = dia;

         //formatar a data
            dataFormatada = String.format("%02d/%02d/%04d", day, month + 1, year);

            //aplica a data formatada no botão
            binding.btSeDatabotao.setText(dataFormatada);

        }, year,month,day);

        //ação do click do botao de seleção da data
        binding.btSeDatabotao.setOnClickListener(v ->{
            datePicker.show();
        });

        // listener do botão salvar
        binding.btSalvar.setOnClickListener(v -> {
            if (binding.etNomeTarefa.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Insira um título", Toast.LENGTH_SHORT).show();
            }else if (binding.etDescricaoTarefa.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Insira uma Descrição", Toast.LENGTH_SHORT).show();
            }else if (dataFormatada == null){
                // ou dataFormatada.isEmpty()
                Toast.makeText(getContext(), "Informe uma Data", Toast.LENGTH_SHORT).show();
            }else{
                //criar o objeto tarefa
                Tarefa tarefa = new Tarefa();
                // popular o objeto tarefa
                tarefa.setTitulo(binding.etNomeTarefa.getText().toString());
                tarefa.setDescricao(binding.etDescricaoTarefa.getText().toString());
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                // criar um Calendar
                Calendar dataPrevista = Calendar.getInstance();
                //muda a data para data escolida no dataPicker
                dataPrevista.set(year, month, day);
                //passa os milisegundos da data para a data prevista
                tarefa.setDataPrevista(dataPrevista.getTimeInMillis());

                //salvar a tarefa
                new InsertTarefa().execute(tarefa);
            }
        });
        // retornar a view raiz (root) do binding
        return binding.getRoot();
    }

    // AsyncTask para inserir Tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            // pegar a tarefa a partir do vetor
            Tarefa t = tarefas[0];
            try {
                // chamar o método para salvar a tarefa
                database.getTarefaDao().inset(t);
                // retornar
                return "ok";
            }catch (Exception erro){
                erro.printStackTrace();
                // retorna a mensagem de erro
                return erro.getMessage();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("ok")){
                Log.w("RESULT", "IIUUUUUPIIIII TAREFA INSERIDA COM SUCESSO");
                Toast.makeText(getContext(), "Tarefa inserida com sucesso", Toast.LENGTH_SHORT).show();
                // voltar ao fragment anterior
                getActivity().onBackPressed();
            }else {
                Log.w("RESULT", result);
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
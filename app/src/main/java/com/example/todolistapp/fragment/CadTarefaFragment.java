package com.example.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.todolistapp.R;
import com.example.todolistapp.databinding.FragmentCadTarefa1Binding;

import java.util.Calendar;

public class CadTarefaFragment extends Fragment {
    private FragmentCadTarefa1Binding binding;

    private DatePickerDialog datePicker;
    // variaveis para ano,mes e dia
    int year, month, day;

    //variavel para obter a data atual
    Calendar dataAtual;

    // variavel para a data formatada
    String dataFormatada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        }, year,month,day);

        //ação do click do botao de seleção da data
        binding.layoutSeDatabotao.setOnClickListener(v ->{
            datePicker.show();
        });

        return binding.getRoot();


    }
}
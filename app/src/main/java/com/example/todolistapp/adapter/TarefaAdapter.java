package com.example.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todolistapp.R;
import com.example.todolistapp.model.Tarefa;

import java.text.SimpleDateFormat;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>{
    // variável para a lista de tarefas
    private List<Tarefa> tarefas;

    // variável para o Context
    private Context context;

    //construtor que recebe os paarametros para o Adapter
    public TarefaAdapter(List<Tarefa> lista, Context context){
        this.tarefas = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflar a view do viewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefas, parent, false);
        // retorna a ViewHolder
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        //obter a tarefa atráves da position
        Tarefa t = tarefas.get(position);

        //trasportar a informação da tarefa para o holder
        holder.tvTitulo.setText(t.getTitulo());

        // formata a data e exibe no textView
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));

        //verifica se está concluida
       if (t.isConcluida()) {
           holder.tvStatus.setText("Finalizada");
           holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.Secondary_500));
       }else {
           holder.tvStatus.setText("Aberta");
           holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.red_300));
       }
    }

    @Override
    public int getItemCount() {
        if (tarefas != null) {
            return  tarefas.size();
        }
        return 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitulo, tvData, tvStatus;

        public TarefaViewHolder(View view) {
            super(view);

            tvTitulo = view.findViewById(R.id.tv_title_ada_tare);
            tvData = view.findViewById(R.id.tv_Data_Prevista);
            tvStatus = view.findViewById(R.id.tv_status_adapter_tarefa);
        }
    }

}

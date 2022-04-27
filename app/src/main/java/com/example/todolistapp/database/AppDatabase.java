package com.example.todolistapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todolistapp.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1 )
public abstract class AppDatabase extends RoomDatabase {
    // variável para acessar a database
    private static AppDatabase database;
    // metodo para a tarefa Dao
    public abstract TarefaDao getTarefaDao();

    public static AppDatabase getDatabase(Context context){
        // verifico se a data batabase é nula
        if (database == null){
            //instancia a database
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ToDoList").build();
        }
        //retorna a database
        return database;
    }
}

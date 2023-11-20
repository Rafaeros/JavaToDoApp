package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> tasks;
    private ListView list;
    private Button button;
    private ArrayAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.taskList);
        button = findViewById(R.id.addTaskButton);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View View){
                additem(View);
            }
        });
        tasks = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        list.setAdapter(itemsAdapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return remove(position);
            }
        });
    }

    private boolean remove(int position){
        Context context = getApplicationContext();
        Toast.makeText(context,"Tarefa Concluida", Toast.LENGTH_LONG).show();
        tasks.remove(position);
        itemsAdapter.notifyDataSetChanged();
        return true;
    }

    private void additem(View view){
        EditText input = findViewById(R.id.editText);
        String itemText = input.getText().toString();

        if(!(itemText.equals(""))){
            itemsAdapter.add(itemText);
            input.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"Digite algum texto...",Toast.LENGTH_LONG).show();
        }
    }
}
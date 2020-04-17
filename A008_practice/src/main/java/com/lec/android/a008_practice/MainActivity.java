package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
     PersonBookAdapter adapter;
     RecyclerView rv;
     EditText etName, etAge, etAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etAdd = findViewById(R.id.etAdd);

        rv = findViewById(R.id.rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);

        adapter = new PersonBookAdapter();

        rv.setAdapter(adapter);

        Button btnadd = findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                etName.getText().toString();
//                etAge.getText().toString();
//                etAdd.getText().toString();
                adapter.addItem(new PersonBook(etName.getText().toString(),etAge.getText().toString(), etAdd.getText().toString()));
                adapter.notifyDataSetChanged();

            }
        });


    }//end onCreate()
}// end Activity

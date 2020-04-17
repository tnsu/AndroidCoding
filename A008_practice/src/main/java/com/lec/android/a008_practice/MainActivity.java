package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
     PersonBookAdapter adapter;
     RecyclerView rv;
     EditText etName, etAge, etAdd;
     RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etAdd = findViewById(R.id.etAdd);
        rg = findViewById(R.id.gr);

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
                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(id);
                adapter.addItem(new PersonBook(etName.getText().toString(),etAge.getText().toString(), etAdd.getText().toString(), rb.getText().toString()));
                adapter.notifyDataSetChanged();

            }
        });


    }//end onCreate()
}// end Activity

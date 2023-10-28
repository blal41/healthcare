package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HealthArticleActivity2 extends AppCompatActivity {
    ListView lst ;
    Button btnBack;
    private  String[][] health_details = {
            {"Booking daily","","","","click more details"},
            {"home care of covid 19","","","","click more detail"},
            {"Stop Smoking","","","","click more detail"},
            {"Menstrual cramps","","","","click more detail"},
            {"Health gut","","","","click more detail"},

    };
    private  int[] image = {
            R.drawable.health1,
            R.drawable.health2,
    R.drawable.health3,
    R.drawable.health4,
    R.drawable.health5,


    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article2);
        btnBack = findViewById(R.id.btnHABack);
        lst =findViewById(R.id.listviewHA);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticleActivity2.this,HomeActivity.class));
            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < health_details.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", health_details[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multilines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(HealthArticleActivity2.this, HealthArticleDetailsActivity.class);
                it.putExtra("text1", health_details[i][0]);
                it.putExtra("text2", image[i]);
                startActivity(it);
            }
        });
    }
}

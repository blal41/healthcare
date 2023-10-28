package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity2 extends AppCompatActivity {
    private  String[][] package5 = {
            {"Package 1 :  Full Body Checkup","","","","999"},
            {"Package 2 :  Blood Glucose Fasting","","","","299"},
            {"Package 3 :  Covid-19 Antibody - Ig5","","","","899"},
            {"Package 4 :  Thyroid  Check","","","","499"},
            {"Package 5 :  Immunity Check","","","","699"},

    };
    private  String[] package_Detail ={
            "Complete Blood Count (CBC)\n"+
            "Blood Chemistry Panel\n"+
            "Urinalysis\n"+
            "Blood Glucose Test\n",
            "Coagulation Tests (PT/INR, PTT)\n"+
            "Liver Function Tests (LFTs)\n"+
            "Kidney Function Tests (BUN, Creatinine)\n"+
            "Lipid Profile\n",
            "Thyroid Function Tests\n"+
            "Electrolyte Panel\n"+
            "C-reactive Protein (CRP)\n"+
            "Erythrocyte Sedimentation Rate (ESR)\n",
            "Blood Culture\n"+
            "Microbiological Cultures\n"+
            "Electrocardiogram (ECG/EKG)\n"+
            "Chest X-ray\n",
            "Imaging Studies (MRI, CT, Ultrasound, etc.)\n"+
            "Serological Tests\n"+
            "Allergy Tests\n"+
            "Hormone Tests\n",

    };
    HashMap<String,String> item;
    ArrayList list;
    Button btngototcart , btnBack;
    ListView listView;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test2);

        btngototcart =findViewById(R.id.btnCheckOut2);
        btnBack =findViewById(R.id.btnLDGoBack);
        listView = findViewById(R.id.listviewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(LabTestActivity2.this,HomeActivity.class));
            }
        });
        list =new ArrayList();
        for(int i=0 ; i<package5.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", package5[i][0]);
            item.put("line2", package5[i][1]);
            item.put("line3", package5[i][2]);
            item.put("line4", package5[i][3]);
            item.put("line5", "cons Fees:" + package5[i][4] + "/-");
            list.add(item);
        }

        sa =new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new  int[]{R.id.line_a ,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =  new Intent(LabTestActivity2.this ,LabTestDetailsActivity2.class);
                it.putExtra("text1", package5[i][0]);
                it.putExtra("text2", package_Detail[i]);
                it.putExtra("text4", package5[i][4]);
                startActivity(it);
            }
        });
        btngototcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity2.this, CartLabActivity2.class));
            }
        });

    }
}
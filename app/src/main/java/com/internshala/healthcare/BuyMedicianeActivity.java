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

public class BuyMedicianeActivity extends AppCompatActivity {
    private String[][] Package = {
            {" Hydrocodone", "", "", "", "50"},
            {" Metformin", "", "", "", "90"},
            {" Losartan", "", "", "", "200"},
            {" Antibiotics", "", "", "", "150"},
            {" Albuterol", "", "", "", "100"},
    };
    private  String[] Package_details = {
            "Hydrocodone was first patented in 1923\n"+"with the long-acting formulation being\n"+"approved in 2013." ,
            "It is a prescribed medicine used to treat type 2 \n"+"iabetes and even prevent it if someone is at a high\n"+"risk of developing the disease by lowering bp",
            "It is used to treat high blood pressure by blocking\n"+" substance in the body that causes the blood\n"+"vessels to tighten.",
            "These are prescribed medicines that fight\n"+"ff bacterial infections. First discovered in 1928\n"+"Alexander Fleming,",
            "This medicine prevents and treats breathing difficult\n"+"wheezing, coughing, and other related\n"+"conditions caused by lung disease",
    };
    HashMap<String ,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack , btngotocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_mediciane);
        lst = findViewById(R.id.listviewBMC);
        btnBack = findViewById(R.id.btnBMCBack);
        btngotocart = findViewById(R.id.btnBMCGoTocart);
        btngotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicianeActivity.this,CartBuymedicianActivity.class));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicianeActivity.this, HomeActivity.class));
            }
        });
        list =new ArrayList();
        for(int i=0 ; i<Package.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", Package[i][0]);
            item.put("line2", Package[i][1]);
            item.put("line3", Package[i][2]);
            item.put("line4", Package[i][3]);
            item.put("line5", "cons Fees:" + Package[i][4] + "/-");
            list.add(item);
        }

        sa =new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new  int[]{R.id.line_a ,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =  new Intent(BuyMedicianeActivity.this ,BuyMedicianDtailsActivity2.class);
                it.putExtra("text1", Package[i][0]);
                it.putExtra("text2", Package_details[i]);
                it.putExtra("text4", Package[i][4]);
                startActivity(it);
            }
        });

    }
}
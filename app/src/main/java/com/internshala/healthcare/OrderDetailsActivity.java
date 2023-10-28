package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {
    private  String[][] order_Details = {};
    HashMap<String,String> item;
    SimpleAdapter sa;
    ListView lst;
    ArrayList list;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btn =findViewById(R.id.btnBMCBack);
        lst = findViewById(R.id.listviewBMC);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });
        database db = new database(getApplicationContext() ,"healthcare",null,1);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbData = db.getOrderData(username);

//        order_Details = new  String[dbData.size()][];
//        for(int i =0;i<order_Details.length;i++){
//            order_Details[i]=new  String[5];
//            String arrData = dbData.get(i).toString();
//            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
//            order_Details[i][0]=strData[0];
//            order_Details[i][1]=strData[1];
//            if(strData[7].compareTo("medicine")==0){
//                order_Details[i][3]="Del: "+strData[4];
//            }
//            else {
//                order_Details[i][3]="Del: "+strData[4]+" "+strData[5];
//            }
//            order_Details[i][2]="Rs. "+strData[6];
//            order_Details[i][4]=strData[7];
//        }
        order_Details = new String[dbData.size()][];
        for (int i = 0; i < order_Details.length; i++) {
            order_Details[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));

            // Check if strData has at least 8 elements (0 to 7) before accessing them
            if (strData.length >= 8) {
                order_Details[i][0] = strData[0];
                order_Details[i][1] = strData[1];

                if ("medicine".equals(strData[7])) {
                    order_Details[i][3] = "Del: " + strData[4];
                } else {
                    order_Details[i][3] = "Del: " + strData[4] + " " + strData[5];
                }

                order_Details[i][2] = "Rs. " + strData[6];
                order_Details[i][4] = strData[7];
            } else {
                // Handle the case where strData doesn't have enough elements
                // You can set default or error values here.
                // For example:
                order_Details[i][0] = "N/A";
                order_Details[i][1] = "N/A";
                order_Details[i][2] = "N/A";
                order_Details[i][3] = "N/A";
                order_Details[i][4] = "N/A";
            }
        }

        list = new ArrayList();
        for(int i=0 ; i<order_Details.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", order_Details[i][0]);
            item.put("line2", order_Details[i][1]);
            item.put("line3", order_Details[i][2]);
            item.put("line4", order_Details[i][3]);
            item.put("line5", order_Details[i][4] );
            list.add(item);
        }
        sa =new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new  int[]{R.id.line_a ,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);


    }
}
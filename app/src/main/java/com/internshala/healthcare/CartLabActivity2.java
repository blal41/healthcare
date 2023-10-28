package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity2 extends AppCompatActivity {
//    EditText ed1;
    SimpleAdapter sa;
    ArrayList list;
    TextView tvTotal;
    ListView lst;
    HashMap<String,String> item;
    private  String[][] packages ={};
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button datebutton , timebutton,btnCheckOut , btnback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab2);

        datebutton= findViewById(R.id.buttonBMCCardDatePicker);
        timebutton= findViewById(R.id.buttonCartTimePicker);
        btnback= findViewById(R.id.btnBMCBack);
        btnCheckOut= findViewById(R.id.btnBMCGoTocart);
        tvTotal=findViewById(R.id.textviewBMCTotalprice);
        lst= findViewById(R.id.listviewBMC);

        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();


        database db = new database(getApplicationContext() ,"healthcare",null,1);
        float totalAmount = 0;
        ArrayList dbData =db.getCartData(username,"lab");
        Toast.makeText(getApplicationContext(),""+dbData,Toast.LENGTH_LONG).show();




        packages = new String[dbData.size()][];
        for(int i =0; i<packages.length;i++){
            packages[i]=new String[5];
        }
        for(int i=0;i<dbData.size();i++){
            String arrData =dbData.get(i).toString();
            String[] strData= arrData.split((java.util.regex.Pattern.quote("$")));
            packages[i][0]=strData[0];
            packages[i][4]= "cost: "+strData[1]+"/-";
            totalAmount = totalAmount+Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total Cost :  "+totalAmount);

        list =new ArrayList();
        for(int i=0 ; i<packages.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }
        sa =new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new  int[]{R.id.line_a ,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(CartLabActivity2.this,LabTestActivity2.class));
            }
        });
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CartLabActivity2.this, LabTestBookActivity.class);
                it.putExtra("price",tvTotal.getText());
                it.putExtra("date",datebutton.getText());
                it.putExtra("time",timebutton.getText());
                startActivity(it);


            }
        });

        initDatePicker();
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        initDTimePicker();
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }
    private  void  initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i3) {
                i1 = i1+1;
                datebutton.setText(i3+"/"+i1+"/"+i);


            }
        };
        Calendar cal =Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);


    }
    private  void  initDTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                i1 = i1+1;
                timebutton.setText(i+":"+i1);

            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }
}
package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1 ,ed2 ,ed3,ed4;
    private DatePickerDialog  datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private  Button datebutton , timebutton,btnBook , btnback;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        tv= findViewById(R.id.textViewBMBapptitle);
        ed1= findViewById(R.id.editTextBMBfullName);
        ed2= findViewById(R.id.editTextBMBAddress);
        ed3= findViewById(R.id.editTextLTBContactNum);
        ed4= findViewById(R.id.editTextBMBpinCode);
        datebutton= findViewById(R.id.buttonBMCCardDatePicker);
        timebutton= findViewById(R.id.buttonCartTimePicker);
        btnBook= findViewById(R.id.buttonLTBBooking);
        btnback= findViewById(R.id.buttonAppBack);



        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);
        Intent it  = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons fees: "+fees+"/-");

        //datepicker
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

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, fFndDoctorActivity.class));
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database db = new database(getApplicationContext() ,"healthcare",null,1);

                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                if(db.checkAppointmentExist(username,title+"=>"+fullname,address,contact,datebutton.getText().toString(),timebutton.getText().toString())==1){
                    Toast.makeText(getApplicationContext(),"Appointment is already booked",Toast.LENGTH_LONG).show();
                }
                else {
                    db.addOrder(username,title+"=>"+fullname,address,contact,0,datebutton.getText().toString(),timebutton.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(),"Your Appointment is Done successfully ",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
                }

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
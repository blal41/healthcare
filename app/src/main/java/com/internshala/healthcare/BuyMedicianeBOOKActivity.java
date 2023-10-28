package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicianeBOOKActivity extends AppCompatActivity {
    EditText edname ,edAddress ,edPin ,edPhoneNum;
    Button btnBooking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_mediciane_bookactivity);
        edname =findViewById(R.id.editTextBMB1fullName);
        edAddress =findViewById(R.id.editTextBMB1Address);
        edPin =findViewById(R.id.editTextBMB1pinCode);
        edPhoneNum =findViewById(R.id.editTextBMB1ContactNum);
        btnBooking =findViewById(R.id.buttonBMB1Booking);

        Intent intent =getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));  //edited
        String date = intent.getStringExtra("date");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                database db = new database(getApplicationContext() ,"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edAddress.getText().toString(),edPhoneNum.getText().toString(),Integer.parseInt(edPin.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(),"Your booking is done successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(BuyMedicianeBOOKActivity.this,HomeActivity.class));

            }
        });
    }
}
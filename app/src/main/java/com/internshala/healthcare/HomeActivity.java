package com.internshala.healthcare;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        String username = sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome"+username,Toast.LENGTH_SHORT).show();

        CardView exit =findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.clear();
                editor.clear();
                startActivity(new Intent(HomeActivity.this , Loginactivity.class));

            }
        });
        CardView finddoctor = findViewById(R.id.cardFindDoctor);
        finddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,fFndDoctorActivity.class));
            }
        });
        CardView labTest = findViewById(R.id.cardLabTest);
        labTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,LabTestActivity2.class));
            }
        });
        CardView orderDetails = findViewById(R.id.cardOrderDetail);
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,OrderDetailsActivity.class));
            }
        });
        CardView buyMediciane =  findViewById(R.id.cardBuyMedicine);
        buyMediciane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,BuyMedicianeActivity.class));
            }
        });
        CardView healtharticle =  findViewById(R.id.cardHealthArticle);
        healtharticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,HealthArticleActivity2.class));
            }
        });
    }
}
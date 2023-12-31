package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class fFndDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffnd_doctor);

        CardView exit =findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(fFndDoctorActivity.this , HomeActivity.class));

            }
        });
        CardView familyPhysician =findViewById(R.id.cardFDFamilyPhysician);
        familyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(fFndDoctorActivity.this , DoctorDetailActivity.class);
                it.putExtra("title","Family Physician");
                startActivity(it);

            }
        });

        CardView dietician =findViewById(R.id.cardFDdIETICIAN);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(fFndDoctorActivity.this , DoctorDetailActivity.class);
                it.putExtra("title","Dietician");
                startActivity(it);

            }
        });

        CardView dentist =findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(fFndDoctorActivity.this , DoctorDetailActivity.class);
                it.putExtra("title","Dentist");
                startActivity(it);

            }
        });

        CardView surgeon =findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(fFndDoctorActivity.this , DoctorDetailActivity.class);
                it.putExtra("title","Surgeon");
                startActivity(it);

            }
        });

        CardView cardiologist =findViewById(R.id.cardFDCardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(fFndDoctorActivity.this , DoctorDetailActivity.class);
                it.putExtra("title","Cardiologist");
                startActivity(it);

            }
        });

    }
}
package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class

LabTestDetailsActivity2 extends AppCompatActivity {
     TextView tvPackageName , tvTotalCost;
     EditText edDetails;
     Button btnAddToCart ,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details2);
        tvPackageName = findViewById(R.id.textviewBMPackageName);
        tvTotalCost =findViewById(R.id.textviewBMTotalCost);
        edDetails =findViewById(R.id.edittextBMTextMultiline);
        btnBack =findViewById(R.id.buttonBMBack);
        btnAddToCart = findViewById(R.id.buttonBMAddToCart);

        edDetails.setKeyListener(null);
        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+intent.getStringExtra("text4")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity2.this,LabTestActivity2.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text4").toString());

                try (database db = new database(getApplicationContext(), "healthcare", null, 1)) {
                    if (db.checkCart(username, product) == 1) {
                        Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();

                    } else {
                        db.addCart(username, product, price, "lab");
                        Toast.makeText(getApplicationContext(), "Recod insert in to cart", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LabTestDetailsActivity2.this, LabTestActivity2.class));

                    }
                }

            }
        });
    }
}
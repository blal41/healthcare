package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicianDtailsActivity2 extends AppCompatActivity {
    TextView tvPackagename ,tvTotalcost;
    EditText edDetails;
    Button btnBack ,btnaddtocart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medician_dtails2);
        tvPackagename = findViewById(R.id.textviewBMDPackageName);
        tvTotalcost =findViewById(R.id.textviewBMDTotalCost);
        edDetails =findViewById(R.id.edittextBMDTextMultiline);
        btnBack =(Button) findViewById(R.id.buttonBMDBack);
        btnaddtocart =(Button) findViewById(R.id.buttonBMDAddToCart);


        edDetails.setKeyListener(null);
        Intent intent = getIntent();
        tvPackagename.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalcost.setText("Total Cost : "+intent.getStringExtra("text4")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicianDtailsActivity2.this,BuyMedicianeActivity.class));

            }
        });

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text4").toString());

                try (database db = new database(getApplicationContext(), "healthcare", null, 1)) {
                    if (db.checkCart(username, product) == 1) {
                        Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();

                    } else {
                        db.addCart(username, product, price, "medicine");
                        Toast.makeText(getApplicationContext(), "Record insert in to cart", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(BuyMedicianDtailsActivity2.this, BuyMedicianeActivity.class));

                    }
                }
            }
        });



    }
}
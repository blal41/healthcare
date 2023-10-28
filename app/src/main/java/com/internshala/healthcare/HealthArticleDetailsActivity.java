package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticleDetailsActivity extends AppCompatActivity {
     ImageView img;
     TextView txt;
     Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_details);
        img = findViewById(R.id.imageview);
        txt = findViewById(R.id.textviewHADtitle);
        btnBack =findViewById(R.id.btnHADBack);

        Intent intent = getIntent();
        txt.setText(intent.getStringExtra("text1"));
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            int resid = bundle.getInt("text2");
            img.setImageResource(resid);
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticleDetailsActivity.this,HealthArticleActivity2.class));
            }
        });
    }
}
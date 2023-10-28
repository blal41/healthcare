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

public class Loginactivity extends AppCompatActivity {

    EditText edUsername ,edPassword ;
    Button btn ;
    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        edUsername = findViewById(R.id.editTextUsername);
        edPassword = findViewById(R.id.editTextPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textNewuser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                database db = new database(getApplicationContext() ,"healthcare",null,1);

                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"please fill the detail",Toast.LENGTH_LONG).show();
                }
                else {
                    if (db.login(username, password) == 1) {
                        Toast.makeText(getApplicationContext(), "Log in Success", Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.apply();
                        startActivity(new Intent(Loginactivity.this ,HomeActivity.class));

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Loginactivity.this , RegisterActivity.class));
            }
        });
    }
}
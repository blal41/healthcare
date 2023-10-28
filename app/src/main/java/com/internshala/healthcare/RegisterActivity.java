package com.internshala.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername ,edPassword ,edConPassword ,edEmail;
    Button btn1 ;
    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        edUsername = (EditText) findViewById(R.id.editTextRegUsername);
        edPassword = (EditText) findViewById(R.id.editTextRegPassword);
        edConPassword = (EditText) findViewById(R.id.editTextRegConPassword);
        edEmail = (EditText) findViewById(R.id.editTextRegEmail);

        btn1 = (Button) findViewById(R.id.buttonRegister);
        tv = (TextView) findViewById(R.id.textHaveaccount);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();

                String password = edPassword.getText().toString();
                String conPassword = edConPassword.getText().toString();
                database db = new database(getApplicationContext() ,"healthcare",null,1);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(RegisterActivity.this , Loginactivity.class));
                    }
                });

                if(username.length()==0 || password.length()==0 || conPassword.length()==0|| email.length()==0) {
                    Toast.makeText(getApplicationContext(),"please fill the detail",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(conPassword)==0) {
                        db.register(username,email,password);
                        if (isValidPassword(password)) {

                            Toast.makeText(getApplicationContext(), "Record inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, Loginactivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "the password must be 8 character having letter and special character", Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password did not matched", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });

    }
    public static boolean isValidPassword(String password)
    {
        boolean isValid = true;
        if (password.length() > 15 || password.length() < 8)
        {
            System.out.println("Password must be less than 20 and more than 8 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            System.out.println("Password must have atLeast one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            System.out.println("Password must have atLeast one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            System.out.println("Password must have atLeast one number");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars ))
        {
            System.out.println("Password must have atLeast one special character among @#$%");
            isValid = false;
        }
        return isValid;
    }
}
package com.example.bookdonation.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookdonation.R;

public class AdminEmailActivity extends AppCompatActivity {


    EditText AdminEmail,AdminPassword;
    Button AdminLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        AdminEmail=findViewById(R.id.AdminEmail);
        AdminPassword=findViewById(R.id.AdminPassword);
        AdminLoginBtn=findViewById(R.id.AdminLoginBtn);

        AdminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = AdminEmail.getText().toString();
                String password = AdminPassword.getText().toString();

                if (email.isEmpty()){
                    AdminEmail.setError("Email Error");
                    AdminEmail.requestFocus();
                }else if (password.isEmpty()){
                    AdminPassword.setError("Password Error");
                    AdminPassword.requestFocus();
                }else if (email.equals("admin23@gmail.com") & password.equals("admin23")){
                    Toast.makeText(AdminEmailActivity.this,"Admin Login Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AdminEmailActivity.this, AdminMainActivity.class));
                    finish();
                }else {
                    Toast.makeText(AdminEmailActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
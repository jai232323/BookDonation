package com.example.bookdonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.bookdonation.Admin.AdminEmailActivity;
import com.example.bookdonation.Admin.AdminMainActivity;
import com.example.bookdonation.DonoteABook.DonoteABookActivity;
import com.example.bookdonation.Find_Donors.Find_DonorsActivity;
import com.example.bookdonation.Make_A_Complaint.Make_A_ComplaintActivity;
import com.google.android.material.card.MaterialCardView;

public class MainModuleActivity extends AppCompatActivity {


    MaterialCardView DonoteABook,Find_Donors,Make_A_Complaint,Admin;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_module);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(R.color.white);
        setSupportActionBar(toolbar);


        DonoteABook=findViewById(R.id.DonoteABook);
        Find_Donors=findViewById(R.id.Find_Donors);
        Make_A_Complaint=findViewById(R.id.Make_A_Complaint);
        Admin=findViewById(R.id.Admin);

        DonoteABook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainModuleActivity.this, DonoteABookActivity.class));
            }
        });
        Find_Donors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainModuleActivity.this, Find_DonorsActivity.class));
            }
        });
        Make_A_Complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainModuleActivity.this, Make_A_ComplaintActivity.class));
            }
        });
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainModuleActivity.this, AdminEmailActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SendFeedback:
                startActivity(new Intent(MainModuleActivity.this, SendFeedbackActivity.class));
                break;
            case R.id.About:
                startActivity(new Intent(MainModuleActivity.this, AboutActivity.class));
                break;
        }
        return true;
    }
}
package com.example.bookdonation.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bookdonation.R;
import com.google.android.material.card.MaterialCardView;

public class AdminMainActivity extends AppCompatActivity {


    MaterialCardView DonoteABook,Make_A_Complaint,Feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        DonoteABook=findViewById(R.id.DonoteABook);
        Make_A_Complaint=findViewById(R.id.Make_A_Complaint);
        Feedback=findViewById(R.id.Feedback);

        DonoteABook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this,AdminDonateActivity.class));
            }
        });
        Make_A_Complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this,AdminMakeAComplaintActivity.class));
            }
        });Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this,AdminFeedbackActivity.class));
            }
        });
    }
}
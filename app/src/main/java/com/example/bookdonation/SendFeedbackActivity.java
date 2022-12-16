package com.example.bookdonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookdonation.DonoteABook.DonoteABookActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SendFeedbackActivity extends AppCompatActivity {


    private EditText Give_Your_FeedBack;
    private Button Submit_Your_FeedBack;

    String feedback;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        pd=new ProgressDialog(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("FeedBack");
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Give_Your_FeedBack=findViewById(R.id.Give_Your_FeedBack);
        Submit_Your_FeedBack=findViewById(R.id.Submit_Your_FeedBack);

        Submit_Your_FeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 feedback = Give_Your_FeedBack.getText().toString();

                if (feedback.isEmpty()){
                    Give_Your_FeedBack.setError("Give Feedback");
                    Give_Your_FeedBack.requestFocus();
                }else {
                    uploadData();
                }
            }
        });
    }

    private void uploadData() {
        pd.setMessage("Your Feedback Uploading...\nPlease Wait...");
        pd.show();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Feedback");

        String UniqueKey = reference.push().getKey();

        HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("UniqueKey",UniqueKey);
        hashMap.put("Feedback",feedback);


        reference.child(UniqueKey).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(SendFeedbackActivity.this,"Feedback Submitted",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SendFeedbackActivity.this, MainModuleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                finish();
            }
        });
    }
}
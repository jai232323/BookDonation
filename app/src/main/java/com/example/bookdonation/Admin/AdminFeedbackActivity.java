package com.example.bookdonation.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bookdonation.Admin.Adapter.FeedbackAdapter;
import com.example.bookdonation.Admin.Module.FeedbackData;
import com.example.bookdonation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminFeedbackActivity extends AppCompatActivity {

    RecyclerView FeedbackRecyclerView;
    LinearLayout FeedbackNOData;

    private ArrayList<FeedbackData> list;
    private FeedbackAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedback);


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


        FeedbackRecyclerView=findViewById(R.id.FeedbackRecyclerView);
        FeedbackNOData=findViewById(R.id.FeedbackNOData);

        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(AdminFeedbackActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        FeedbackRecyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new FeedbackAdapter(list,AdminFeedbackActivity.this);
        FeedbackRecyclerView.setAdapter(adapter);

        getFeedback();

    }

    private void getFeedback() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Feedback");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                if (!snapshot.exists()){
                    FeedbackNOData.setVisibility(View.VISIBLE);
                    FeedbackRecyclerView.setVisibility(View.GONE);
                }else {
                    FeedbackNOData.setVisibility(View.GONE);
                    FeedbackRecyclerView.setVisibility(View.VISIBLE);

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        FeedbackData data = dataSnapshot.getValue(FeedbackData.class);
                        list.add(0, data);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
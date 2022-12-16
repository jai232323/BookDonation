package com.example.bookdonation.InterView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.bookdonation.R;

import java.util.ArrayList;

public class RecyclerView extends AppCompatActivity {

    View recyclerView;
  //  ArrayList<Data1> list;

    RecyclerViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView=findViewById(R.id.recyclerView);
    }
}
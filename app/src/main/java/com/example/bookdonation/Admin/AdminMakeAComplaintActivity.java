package com.example.bookdonation.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bookdonation.Admin.Adapter.AdminMakeAComplaintAdapter;
import com.example.bookdonation.Admin.Module.ComplaintData;
import com.example.bookdonation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminMakeAComplaintActivity extends AppCompatActivity {



    private RecyclerView Literary_FictionRecyclerView,MysteryRecyclerView,ThrillerRecyclerView,HorrorRecyclerView,
            HistoricalRecyclerView,RomanceRecyclerView,WesternRecyclerView,BildungsromanRecyclerView,Speculative_FictionRecyclerView,
            FantasyRecyclerView,DystopianRecyclerView,Realist_LiteratureRecyclerView;

    private LinearLayout LiteraryFictionNoData,MysteryNoData,ThrillerNoData,HorrorNoData,HistoricalNoData,
            RomanceNoData,WesternNoData,BildungsromanNoData,Speculative_FictionNoData,FantasyNoData,DystopianNoData,
            Realist_LiteratureNoData;
    private List<ComplaintData> list1,list2,list3,list4,list5,list6,list7,list8,list9,list10,list11,list12;

    private AdminMakeAComplaintAdapter adapter;

    DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_make_acomplaint);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Book Complaints");
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reference= FirebaseDatabase.getInstance().getReference("ComplaintData");

        Literary_FictionRecyclerView=findViewById(R.id.Literary_FictionRecyclerView);
        MysteryRecyclerView=findViewById(R.id.MysteryRecyclerView);
        ThrillerRecyclerView=findViewById(R.id.ThrillerRecyclerView);
        HorrorRecyclerView=findViewById(R.id.HorrorRecyclerView);
        HistoricalRecyclerView=findViewById(R.id.HistoricalRecyclerView);
        RomanceRecyclerView=findViewById(R.id.RomanceRecyclerView);
        WesternRecyclerView=findViewById(R.id.WesternRecyclerView);
        BildungsromanRecyclerView=findViewById(R.id.BildungsromanRecyclerView);
        Speculative_FictionRecyclerView=findViewById(R.id.Speculative_FictionRecyclerView);
        FantasyRecyclerView=findViewById(R.id.FantasyRecyclerView);
        DystopianRecyclerView=findViewById(R.id.DystopianRecyclerView);
        Realist_LiteratureRecyclerView=findViewById(R.id.Realist_LiteratureRecyclerView);

        LiteraryFictionNoData=findViewById(R.id.LiteraryFictionNoData);
        MysteryNoData=findViewById(R.id.MysteryNoData);
        ThrillerNoData=findViewById(R.id.ThrillerNoData);
        HorrorNoData=findViewById(R.id.HorrorNoData);
        HistoricalNoData=findViewById(R.id.HistoricalNoData);
        RomanceNoData=findViewById(R.id.RomanceNoData);
        WesternNoData=findViewById(R.id.WesternNoData);
        BildungsromanNoData=findViewById(R.id.BildungsromanNoData);
        Speculative_FictionNoData=findViewById(R.id.Speculative_FictionNoData);
        FantasyNoData=findViewById(R.id.FantasyNoData);
        DystopianNoData=findViewById(R.id.DystopianNoData);
        Realist_LiteratureNoData=findViewById(R.id.Realist_LiteratureNoData);

        LiteraryFiction();
        Mystery();
        Thriller();
        Horror();
        Historical();
        Romance();
        Western();
        Bildungsroman();
        Speculative_Fiction();
        Fantasy();
        Dystopian();
        Realist_Literature();



    }

    private void Realist_Literature() {
        dbRef=reference.child("Realist Literature");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list12 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    Realist_LiteratureNoData.setVisibility(View.VISIBLE);
                    Realist_LiteratureRecyclerView.setVisibility(View.GONE);
                }else {
                    Realist_LiteratureNoData.setVisibility(View.GONE);
                    Realist_LiteratureRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Realist Literature".equals(data.getBookGenre())){
                            list12.add(data);
                        }
                    }
                    Realist_LiteratureRecyclerView.setHasFixedSize(true);
                    Realist_LiteratureRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list12,AdminMakeAComplaintActivity.this,"Realist Literature");
                    Realist_LiteratureRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Dystopian() {
        dbRef=reference.child("Dystopian");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list11 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    DystopianNoData.setVisibility(View.VISIBLE);
                    DystopianRecyclerView.setVisibility(View.GONE);
                }else {
                    DystopianNoData.setVisibility(View.GONE);
                    DystopianRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Dystopian".equals(data.getBookGenre())){
                            list11.add(data);
                        }
                    }
                    DystopianRecyclerView.setHasFixedSize(true);
                    DystopianRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list11,AdminMakeAComplaintActivity.this,"Dystopian");
                    DystopianRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Fantasy() {
        dbRef=reference.child("Fantasy");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list10 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    FantasyNoData.setVisibility(View.VISIBLE);
                    FantasyRecyclerView.setVisibility(View.GONE);
                }else {
                    FantasyNoData.setVisibility(View.GONE);
                    FantasyRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Fantasy".equals(data.getBookGenre())){
                            list10.add(data);
                        }
                    }
                    FantasyRecyclerView.setHasFixedSize(true);
                    FantasyRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list10,AdminMakeAComplaintActivity.this,"Fantasy");
                    FantasyRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Speculative_Fiction() {
        dbRef=reference.child("Speculative Fiction");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list9 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    Speculative_FictionNoData.setVisibility(View.VISIBLE);
                    Speculative_FictionRecyclerView.setVisibility(View.GONE);
                }else {
                    Speculative_FictionNoData.setVisibility(View.GONE);
                    Speculative_FictionRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Speculative Fiction".equals(data.getBookGenre())){
                            list9.add(data);
                        }
                    }
                    Speculative_FictionRecyclerView.setHasFixedSize(true);
                    Speculative_FictionRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list9,AdminMakeAComplaintActivity.this,"Speculative Fiction");
                    Speculative_FictionRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Bildungsroman() {
        dbRef=reference.child("Bildungsroman");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list8 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    BildungsromanNoData.setVisibility(View.VISIBLE);
                    BildungsromanRecyclerView.setVisibility(View.GONE);
                }else {
                    BildungsromanNoData.setVisibility(View.GONE);
                    BildungsromanRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Bildungsroman".equals(data.getBookGenre())){
                            list8.add(data);
                        }
                    }
                    BildungsromanRecyclerView.setHasFixedSize(true);
                    BildungsromanRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list8,AdminMakeAComplaintActivity.this,"Bildungsroman");
                    BildungsromanRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Western() {
        dbRef=reference.child("Western");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list7 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    WesternNoData.setVisibility(View.VISIBLE);
                    WesternRecyclerView.setVisibility(View.GONE);
                }else {
                    WesternNoData.setVisibility(View.GONE);
                    WesternRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Western".equals(data.getBookGenre())){
                            list7.add(data);
                        }
                    }
                    WesternRecyclerView.setHasFixedSize(true);
                    WesternRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list7,AdminMakeAComplaintActivity.this,"Western");
                    WesternRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Romance() {
        dbRef=reference.child("Romance");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list6 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    RomanceNoData.setVisibility(View.VISIBLE);
                    RomanceRecyclerView.setVisibility(View.GONE);
                }else {
                    RomanceNoData.setVisibility(View.GONE);
                    RomanceRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Romance".equals(data.getBookGenre())){
                            list6.add(data);
                        }
                    }
                    RomanceRecyclerView.setHasFixedSize(true);
                    RomanceRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list6,AdminMakeAComplaintActivity.this,"Romance");
                    RomanceRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Historical() {
        dbRef=reference.child("Historical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    HistoricalNoData.setVisibility(View.VISIBLE);
                    HistoricalRecyclerView.setVisibility(View.GONE);
                }else {
                    HistoricalNoData.setVisibility(View.GONE);
                    HistoricalRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Historical".equals(data.getBookGenre())){
                            list5.add(data);
                        }
                    }
                    HistoricalRecyclerView.setHasFixedSize(true);
                    HistoricalRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list5,AdminMakeAComplaintActivity.this,"Historical");
                    HistoricalRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Horror() {
        dbRef=reference.child("Horror");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<ComplaintData>();
                if (!dataSnapshot.exists()){
                    HorrorNoData.setVisibility(View.VISIBLE);
                    HorrorRecyclerView.setVisibility(View.GONE);
                }else {
                    HorrorNoData.setVisibility(View.GONE);
                    HorrorRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Horror".equals(data.getBookGenre())){
                            list4.add(data);
                        }
                    }
                    HorrorRecyclerView.setHasFixedSize(true);
                    HorrorRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list4,AdminMakeAComplaintActivity.this,"Horror");
                    HorrorRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Thriller() {
        dbRef=reference.child("Thriller");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<ComplaintData>();
                if (!dataSnapshot.exists()){
                    ThrillerNoData.setVisibility(View.VISIBLE);
                    ThrillerRecyclerView.setVisibility(View.GONE);
                }else {
                    ThrillerNoData.setVisibility(View.GONE);
                    ThrillerRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Thriller".equals(data.getBookGenre())){
                            list3.add(data);
                        }
                    }
                    ThrillerRecyclerView.setHasFixedSize(true);
                    ThrillerRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list3,AdminMakeAComplaintActivity.this,"Thriller");
                    ThrillerRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Mystery() {
        dbRef=reference.child("Mystery");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<ComplaintData>();
                if (!dataSnapshot.exists()){
                    MysteryNoData.setVisibility(View.VISIBLE);
                    MysteryRecyclerView.setVisibility(View.GONE);
                }else {
                    MysteryNoData.setVisibility(View.GONE);
                    MysteryRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Mystery".equals(data.getBookGenre())){
                            list2.add(data);
                        }
                    }
                    MysteryRecyclerView.setHasFixedSize(true);
                    MysteryRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list2,AdminMakeAComplaintActivity.this,"Mystery");
                    MysteryRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LiteraryFiction() {

        dbRef=reference.child("Literary Fiction");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    LiteraryFictionNoData.setVisibility(View.VISIBLE);
                    Literary_FictionRecyclerView.setVisibility(View.GONE);
                }else {
                    LiteraryFictionNoData.setVisibility(View.GONE);
                    Literary_FictionRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ComplaintData data = snapshot.getValue(ComplaintData.class);

                        if ("Literary Fiction".equals(data.getBookGenre())){
                            list1.add(data);
                        }
                    }
                    Literary_FictionRecyclerView.setHasFixedSize(true);
                    Literary_FictionRecyclerView.setLayoutManager(new LinearLayoutManager(AdminMakeAComplaintActivity.this));
                    adapter = new AdminMakeAComplaintAdapter(list1,AdminMakeAComplaintActivity.this,"Literary Fiction");
                    Literary_FictionRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminMakeAComplaintActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
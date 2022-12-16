package com.example.bookdonation.Find_Donors.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookdonation.Find_Donors.Find_DonorsActivity;
import com.example.bookdonation.FullImagerActivity;
import com.example.bookdonation.MainModuleActivity;
import com.example.bookdonation.Make_A_Complaint.Make_A_ComplaintActivity;
import com.example.bookdonation.Modules.BookDonationData;
import com.example.bookdonation.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class FindDonerAdapter extends RecyclerView.Adapter<FindDonerAdapter.ViewHolder> {

    private List<BookDonationData> list;
    private Context context;
    private String bookGenre;

    public FindDonerAdapter(List<BookDonationData> list, Context context, String bookGenre) {
        this.list = list;
        this.context = context;
        this.bookGenre = bookGenre;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.find_doner_adapter,parent,false);

        return new FindDonerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        BookDonationData item = list.get(position);
        holder.BookName.setText(item.getNameofBook()+" | "+item.getBookGenre());
        holder.NameofStudent.setText(item.getNameofStudent());
        holder.Email.setText(item.getEmail());
        holder.MobileNumber.setText(item.getMobileNumber());
        holder.City.setText(item.getCity());
        Glide.with(context).load(item.getBookImage()).into(holder.BookImage);

        holder.BookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImagerActivity.class);
                intent.putExtra("image",item.getBookImage());
                context.startActivity(intent);
            }
        });


        holder.ShareThisBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plane");
                share.putExtra(Intent.EXTRA_TEXT,"BookDonation\n\n"+"Book Genre : "+list.get(position).getBookGenre()+"\n" +
                        "Book Name : " +list.get(position).getNameofBook() + "\n" +
                        "Name : " + list.get(position).getNameofBook() + "\n" +
                        "Mobile Number : " +list.get(position).getMobileNumber() + "\n" +
                        "Email : " +list.get(position).getEmail() + "\n" +
                        "City : " +list.get(position).getCity() + "\n\n"+ "Thank You!!!");
                context.startActivity(share);
            }
        });

        holder.ComplaintThisBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure,you complaint this book\n"+item.getNameofBook()+"  | "+item.getNameofBook());
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseReference reference,dbRef;
                        reference = FirebaseDatabase.getInstance().getReference().
                                child("ComplaintData");

                        dbRef=reference.child(item.getBookGenre());
                        String UniqueKey =item.getUniqueKey();

                        HashMap<String,Object> hashMap1 =new HashMap<>();
                        hashMap1.put("UniqueKey",item.getUniqueKey());
                        hashMap1.put("NameofStudent",item.getNameofBook());
                        hashMap1.put("MobileNumber",item.getMobileNumber());
                        hashMap1.put("Email",item.getEmail());
                        hashMap1.put("City",item.getCity());
                        hashMap1.put("NameofBook",item.getNameofBook());
                        hashMap1.put("BookGenre",item.getBookGenre());
                        hashMap1.put("BookImage",item.getBookImage());




                        dbRef.child(UniqueKey).setValue(hashMap1).
                                addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context,"Successfully Complaint This Book"+"\n"+item.getNameofBook(),Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"You are not Complaint\nThis Book "+item.getNameofBook(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainModuleActivity.class);
                        context.startActivity(intent);
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView BookName,NameofStudent,Email,MobileNumber,City;
        private ImageView BookImage;
        private MaterialCardView ComplaintThisBook,ShareThisBook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            BookName=itemView.findViewById(R.id.BookName);
            BookImage=itemView.findViewById(R.id.BookImage);
            NameofStudent=itemView.findViewById(R.id.NameofStudent);
            Email=itemView.findViewById(R.id.Email);
            MobileNumber=itemView.findViewById(R.id.MobileNumber);
            City=itemView.findViewById(R.id.City);
            ComplaintThisBook=itemView.findViewById(R.id.ComplaintThisBook);
            ShareThisBook=itemView.findViewById(R.id.ShareThisBook);
        }
    }
}

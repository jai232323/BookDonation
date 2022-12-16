package com.example.bookdonation.Admin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookdonation.Find_Donors.Adapter.FindDonerAdapter;
import com.example.bookdonation.FullImagerActivity;
import com.example.bookdonation.Modules.BookDonationData;
import com.example.bookdonation.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdminDonateBookAdapter extends RecyclerView.Adapter<AdminDonateBookAdapter.ViewHolder> {



    private List<BookDonationData> list;
    private Context context;
    private String bookGenre;

    public AdminDonateBookAdapter(List<BookDonationData> list, Context context, String bookGenre) {
        this.list = list;
        this.context = context;
        this.bookGenre = bookGenre;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_bookdoner_adapter,parent,false);

        return new AdminDonateBookAdapter.ViewHolder(view);
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

        holder.Deletethisbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("BookDonation");
                reference.child(item.getBookGenre()).child(item.getUniqueKey()).
                        removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context,item.getNameofBook()+" book Deleted Successfully",Toast.LENGTH_SHORT).show();
                    }
                });
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
        private MaterialCardView ShareThisBook;
        private Button Deletethisbook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            BookName=itemView.findViewById(R.id.BookName);
            BookImage=itemView.findViewById(R.id.BookImage);
            NameofStudent=itemView.findViewById(R.id.NameofStudent);
            Email=itemView.findViewById(R.id.Email);
            MobileNumber=itemView.findViewById(R.id.MobileNumber);
            City=itemView.findViewById(R.id.City);
            ShareThisBook=itemView.findViewById(R.id.ShareThisBook);
            Deletethisbook=itemView.findViewById(R.id.Deletethisbook);

        }
    }
}

package com.example.bookdonation.Admin.Adapter;

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
import com.example.bookdonation.Admin.AdminMainActivity;
import com.example.bookdonation.Admin.Module.ComplaintData;
import com.example.bookdonation.FullImagerActivity;
import com.example.bookdonation.Modules.BookDonationData;
import com.example.bookdonation.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdminMakeAComplaintAdapter extends RecyclerView.Adapter<AdminMakeAComplaintAdapter.ViewHolder> {



    private List<ComplaintData> list;
    private Context context;
    private String bookGenre;

    public AdminMakeAComplaintAdapter(List<ComplaintData> list, Context context, String bookGenre) {
        this.list = list;
        this.context = context;
        this.bookGenre = bookGenre;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_makeacomplaint_adapter,parent,false);

        return new AdminMakeAComplaintAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ComplaintData item = list.get(position);
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


        holder.Deletethisbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("BookDonation").child(item.getBookGenre());
                reference.child(item.getUniqueKey()).removeValue();


                DatabaseReference reference1= FirebaseDatabase.getInstance().getReference("ComplaintData").child(item.getBookGenre());
                reference1.child(item.getUniqueKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context,item.getBookGenre()+" | "+item.getNameofBook()+" \nBook Deleted Successfully",
                                Toast.LENGTH_SHORT).show();
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
        private Button Deletethisbook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            BookName=itemView.findViewById(R.id.BookName);
            BookImage=itemView.findViewById(R.id.BookImage);
            NameofStudent=itemView.findViewById(R.id.NameofStudent);
            Email=itemView.findViewById(R.id.Email);
            MobileNumber=itemView.findViewById(R.id.MobileNumber);
            City=itemView.findViewById(R.id.City);
            Deletethisbook=itemView.findViewById(R.id.Deletethisbook);
        }
    }
}

package com.example.bookdonation.Admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookdonation.Admin.Module.FeedbackData;
import com.example.bookdonation.Modules.BookDonationData;
import com.example.bookdonation.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder>{

    private List<FeedbackData> list;
    private Context context;

    public FeedbackAdapter(List<FeedbackData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_feedback_adapter,parent,false);

        return new FeedbackAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackAdapter.ViewHolder holder, int position) {

        FeedbackData item = list.get(position);

        holder.Feedback.setText(item.getFeedback());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Feedback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Feedback=itemView.findViewById(R.id.Feedback);
        }
    }
}

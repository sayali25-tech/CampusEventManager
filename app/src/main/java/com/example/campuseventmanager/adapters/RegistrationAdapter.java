package com.example.campuseventmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campuseventmanager.R;
import com.example.campuseventmanager.models.Registration;

import java.util.List;

public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationAdapter.RegistrationViewHolder> {

    Context context;
    List<Registration> registrationList;

    public RegistrationAdapter(Context context, List<Registration> registrationList) {
        this.context = context;
        this.registrationList = registrationList;
    }

    @NonNull
    @Override
    public RegistrationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_registration, parent, false);
        return new RegistrationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistrationViewHolder holder, int position) {
        Registration registration = registrationList.get(position);
        holder.tvEventTitle.setText(registration.getEventTitle());
        holder.tvStudentName.setText(registration.getStudentName());
        holder.tvStudentEmail.setText(registration.getStudentEmail());
    }

    @Override
    public int getItemCount() {
        return registrationList.size();
    }

    static class RegistrationViewHolder extends RecyclerView.ViewHolder {
        TextView tvEventTitle, tvStudentName, tvStudentEmail;

        public RegistrationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEventTitle = itemView.findViewById(R.id.tvRegisteredEventTitle);
            tvStudentName = itemView.findViewById(R.id.tvRegisteredStudentName);
            tvStudentEmail = itemView.findViewById(R.id.tvRegisteredStudentEmail);
        }
    }
}

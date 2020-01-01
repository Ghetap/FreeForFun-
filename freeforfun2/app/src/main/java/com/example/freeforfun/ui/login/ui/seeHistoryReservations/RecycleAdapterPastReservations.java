package com.example.freeforfun.ui.login.ui.seeHistoryReservations;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.login.ui.seeReservations.RecycleAdapterReservations;
import com.example.freeforfun.ui.login.ui.seeReservations.ReservationActivity;
import com.example.freeforfun.ui.restCalls.ReservationRestCalls;

import java.util.List;

public class RecycleAdapterPastReservations extends RecyclerView.Adapter<RecycleAdapterPastReservations.ViewHolder>{


    private List<String> reservations;

    public RecycleAdapterPastReservations(List<String> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public RecycleAdapterPastReservations.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item_past_reservation,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterPastReservations.ViewHolder holder, int position) {
        String[] reservationDetails = reservations.get(position).split("\\|");
        int size = reservationDetails[0].length();
        RecycleAdapterReservations.selectedId = reservationDetails[2];
        holder.textViewTitle.setText(reservationDetails[0].substring(0, size-6)
                + reservationDetails[0].substring(size-3, size));
        holder.typeTextView.setText("Number of places: " + reservationDetails[1]);
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewTitle, typeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPastReservation);
            textViewTitle = itemView.findViewById(R.id.textViewTitlePastReservation);
            typeTextView = itemView.findViewById(R.id.typeTextViewPastReservation);
            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });

        }

        @Override
        public void onClick(View v) {
            RecycleAdapterReservations.clickedReservation = ReservationRestCalls
                    .findReservationById(RecycleAdapterReservations.selectedId);
            Intent logoutIntent = new Intent(v.getContext(), ReservationActivity.class);
            v.getContext().startActivity(logoutIntent);
        }
    }
}

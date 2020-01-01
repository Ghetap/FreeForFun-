package com.example.freeforfun.ui.login.ui.seeReservations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.freeforfun.R;
import java.util.List;

public class RecycleAdapterReservations extends RecyclerView.Adapter<RecycleAdapterReservations.ViewHolder>{

    private List<String> reservations;

    public RecycleAdapterReservations(List<String> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public RecycleAdapterReservations.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item_reservation,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterReservations.ViewHolder holder, int position) {
        String[] reservationDetails = reservations.get(position).split("\\|");
        int size = reservationDetails[0].length();
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
            imageView = itemView.findViewById(R.id.imageViewReservation);
            textViewTitle = itemView.findViewById(R.id.textViewTitleReservation);
            typeTextView = itemView.findViewById(R.id.typeTextViewReservation);
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
        }
    }
}

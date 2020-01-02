package com.example.freeforfun.ui.login.ui.seeReservations;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.freeforfun.R;
import com.example.freeforfun.ui.model.Reservation;
import com.example.freeforfun.ui.restCalls.ReservationRestCalls;

import java.util.List;

public class RecycleAdapterReservations extends RecyclerView.Adapter<RecycleAdapterReservations.ViewHolder>{

    private List<String> reservations;
    public static Reservation clickedReservation;
    public static String selectedId;

    RecycleAdapterReservations(List<String> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public RecycleAdapterReservations.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item_reservation,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterReservations.ViewHolder holder, int position) {
        String[] reservationDetails = reservations.get(position).split("\\|");
        int size = reservationDetails[0].length();
        selectedId = reservationDetails[2];
        String titleItem = reservationDetails[0].substring(0, size-6)
                + reservationDetails[0].substring(size-3, size);
        holder.textViewTitle.setText(titleItem);
        String detailsItem = "Number of places: " + reservationDetails[1];
        holder.typeTextView.setText(detailsItem);
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView, deleteImage;
        TextView textViewTitle, typeTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewReservation);
            deleteImage = itemView.findViewById(R.id.imageViewDelete);
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

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create(); //Read Update
                    alertDialog.setTitle("Cancel reservation");
                    alertDialog.setMessage("Are you sure you want to cancel the selected reservation?");
                    alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            String[] reservationDetails = reservations.get(getAdapterPosition()).split("\\|");
                            String id = reservationDetails[2];
                            ReservationRestCalls.deleteReservationById(id);
                            reservations.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        }});

                    alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                        }});
                    alertDialog.show();
            }});
        }

        @Override
        public void onClick(View v) {
            clickedReservation = ReservationRestCalls.findReservationById(selectedId);
            Intent logoutIntent = new Intent(v.getContext(), ReservationActivity.class);
            v.getContext().startActivity(logoutIntent);
        }
    }
}

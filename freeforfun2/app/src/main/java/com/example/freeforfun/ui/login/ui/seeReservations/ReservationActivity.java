package com.example.freeforfun.ui.login.ui.seeReservations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.login.ui.seeHistoryReservations.RecycleAdapterPastReservations;
import com.example.freeforfun.ui.model.Reservation;

import org.w3c.dom.Text;

public class ReservationActivity extends AppCompatActivity {

    private Reservation currentReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Toolbar toolbar = findViewById(R.id.toolbarReservation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Reservation details");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        currentReservation = RecycleAdapterReservations.clickedReservation;
        TextView name = findViewById(R.id.name);
        name.setText("Reservation");
        TextView localName = findViewById(R.id.localName);
        localName.setText(currentReservation.getLocal().getName());
        TextView localAddress = findViewById(R.id.localAddress);
        localAddress.setText(currentReservation.getLocal().getLocation());
        TextView contactNr = findViewById(R.id.contactLocal);
        contactNr.setText(currentReservation.getLocal().getMobileNumber());
        TextView numberPersons = findViewById(R.id.persons);
        numberPersons.setText(currentReservation.getNumberOfPlaces().toString());
        TextView dateAndTime = findViewById(R.id.dateAndTime);
        dateAndTime.setText(currentReservation.getDateTimeReservation());
    }
}
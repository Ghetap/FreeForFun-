package com.example.freeforfun.ui.login.ui.seeLocals;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.model.LocalTable;
import com.example.freeforfun.ui.restCalls.ReservationRestCalls;

import java.util.List;

public class LocalActivity extends AppCompatActivity {

    private Local currentLocal;
    private ImageView pets;
    private ImageView noPets;
    private ImageView wifi;
    private ImageView noWifi;
    private ImageView smoking;
    private ImageView noSmoking;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_local);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        currentLocal = RecycleAdapter.clickedLocal;
        TextView name = findViewById(R.id.name);
        name.setText(currentLocal.getName());
        TextView description = findViewById(R.id.descriptionLocal);
        description.setText(currentLocal.getDescription());
        TextView location = findViewById(R.id.locationLocal);
        location.setText(currentLocal.getLocation());
        TextView mobile = findViewById(R.id.mobileLocal);
        mobile.setText(currentLocal.getMobileNumber());
        TextView timetable = findViewById(R.id.timetableLocal);
        timetable.setText(currentLocal.getTimetable());
        TextView type = findViewById(R.id.typeLocal);
        type.setText(currentLocal.getType().toString().toLowerCase());
        TextView freeTablesView = findViewById(R.id.freeTables);
        List<LocalTable> freeTables = ReservationRestCalls.getFreeTablesForLocalNow(currentLocal.getId());
        StringBuilder tables = new StringBuilder();
        if(freeTables.isEmpty()){
            String noFreePlace = "No free places now";
            freeTablesView.setText(noFreePlace);
        }
        else{
            for(LocalTable table: freeTables){
                tables.append("- a free table with ");
                tables.append(table.getNumberOfPlaces());
                tables.append(" places");
                tables.append("\n");
            }
            freeTablesView.setText(tables);
        }

        pets = findViewById(R.id.petsId);
        noPets = findViewById(R.id.noPetsId);
        wifi = findViewById(R.id.wifiId);
        noWifi = findViewById(R.id.noWifiId);
        smoking = findViewById(R.id.smokingId);
        noSmoking = findViewById(R.id.noSmokingId);

        if(currentLocal.getPetRestriction())
            noPets.setVisibility(View.VISIBLE);
        else
            pets.setVisibility(View.VISIBLE);

        if(currentLocal.getSmokingRestriction())
            noSmoking.setVisibility(View.VISIBLE);
        else
            smoking.setVisibility(View.VISIBLE);

        if(currentLocal.getWifi())
            wifi.setVisibility(View.VISIBLE);
        else
            noWifi.setVisibility(View.VISIBLE);

        ratingBar = findViewById(R.id.rating);
        ratingBar.setRating(currentLocal.getRating());

    }
}
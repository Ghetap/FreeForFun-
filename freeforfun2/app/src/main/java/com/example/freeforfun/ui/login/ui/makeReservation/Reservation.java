package com.example.freeforfun.ui.login.ui.makeReservation;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.freeforfun.R;
import com.example.freeforfun.ui.login.MainActivity;
import com.example.freeforfun.ui.model.EReservationType;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.model.User;
import com.example.freeforfun.ui.restCalls.ReservationRestCalls;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Reservation extends Fragment{

    private CoordinatorLayout coordinatorLayout;
    private ReservationViewModel mViewModel;
    private Spinner localDropDown;
    private Spinner reservationTypeDropDown;
    public static TextView dateText;
    public static TextView timeText;
    private TextView selectedLocal;
    private TextView selectedType;
    private EditText numberPersons;
    private List<Local> locals = UserRestCalls.getAllLocals();
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp currentDate;

    public static Reservation newInstance() {
        return new Reservation();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.reservation_fragment, container, false);
        Button buttonDate = root.findViewById(R.id.button_date);
        Button buttonTime = root.findViewById(R.id.button_time);
        Button buttonReservation = root.findViewById(R.id.button_reservation);
        dateText = root.findViewById(R.id.textDate);
        timeText = root.findViewById(R.id.textTime);
        numberPersons = root.findViewById(R.id.editText_nrPersons);
        localDropDown = root.findViewById(R.id.localChooser);
        reservationTypeDropDown = root.findViewById(R.id.typeChooser);
        coordinatorLayout = root.findViewById(R.id.coordinatorLayout);

        List<String> locals = localNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getActivity().getBaseContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        locals);
        localDropDown.setAdapter(adapter);

        List<String> reservationTypes = typeNames();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>
                (getActivity().getBaseContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        reservationTypes);
        reservationTypeDropDown.setAdapter(adapter2);


        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "Date Picker");
            }
        });
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getFragmentManager(), "Time Picker");
            }
        });

        buttonReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.freeforfun.ui.model.Reservation reservation = createReservation();
                String result = ReservationRestCalls.createReservation(makeJsonReservation(reservation));
                showSnackbar(result);
            }
        });

        localDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocal = (TextView) parent.getChildAt(0);
                if (selectedLocal != null) {
                    selectedLocal.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        reservationTypeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = (TextView) parent.getChildAt(0);
                if (selectedType != null) {
                    selectedType.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReservationViewModel.class);
        // TODO: Use the ViewModel
    }

    private List<String> localNames(){
        if(locals != null) {
            final List<String> localNames = new ArrayList<>();
            for (Local local : locals) {
                localNames.add(local.getName());
            }
            return localNames;
        }
        return Collections.emptyList();
    }

    private List<String> typeNames(){
        EReservationType[] types = new EReservationType[20];
        types = EReservationType.values();
        List<String> typeNames = new ArrayList<>();
        for(int i = 0; i< types.length; i++){
            typeNames.add(types[i].name()
                    .replaceAll("_"," ")
                    .toLowerCase());
        }
        return typeNames;
    }

    private com.example.freeforfun.ui.model.Reservation createReservation(){
        Local reservatedLocal = new Local();
        for(Local local: locals){
            if(local.getName().equals(selectedLocal.getText().toString())){
                reservatedLocal = local;
                break;
            }
        }
        int numberOfPlaces = Integer.parseInt(numberPersons.getText().toString());
        String[] date = dateText.getText().toString().split("\\.");
        String reservationDate = date[2] + "-" + date[1] + "-" + date[0] + " " +
                                timeText.getText().toString() + ":00";
        String reservationType = selectedType.getText()
                                    .toString().toUpperCase().replace(" ","_");
        currentDate = new Timestamp(System.currentTimeMillis());
        EReservationType[] types = EReservationType.values();
        EReservationType type = EReservationType.TWO_THREE_HOURS;
        for(int i = 0; i< types.length; i++){
            if (types[i].name().equals(reservationType)){
                type = types[i];
                break;
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(reservationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        com.example.freeforfun.ui.model.Reservation reservation = new com.example.freeforfun.ui.model.Reservation();
        reservation.setId(null);
        reservation.setUser(MainActivity.loggedUser);
        reservation.setLocal(reservatedLocal);
        reservation.setNumberOfPlaces(numberOfPlaces);
        reservation.setTable(null);
        reservation.setDateTimeCreation(currentDate.toString()
                .substring(0,currentDate.toString().length()-4));
        reservation.setDateTimeReservation(timestamp.toString()
                .substring(0, timestamp.toString().length()-2));
        reservation.setReservationType(type);
        reservation.setDateTimeLeave(null);
        return reservation;
    }

    private void showSnackbar(String text){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout,
                        text, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.MAGENTA);
        snackbar.show();
    }

    private JSONObject makeJsonReservation(com.example.freeforfun.ui.model.Reservation reservation){
        JSONObject jsonReservation = new JSONObject();
        try {
            jsonReservation.put("id", null);
            jsonReservation.put("user", makeJsonUser(reservation.getUser()));
            jsonReservation.put("local", makeJsonLocal(reservation.getLocal()));
            jsonReservation.put("numberOfPlaces", reservation.getNumberOfPlaces());
            jsonReservation.put("table", reservation.getTable());
            jsonReservation.put("dateTimeCreation", reservation.getDateTimeCreation());
            jsonReservation.put("dateTimeReservation", reservation.getDateTimeReservation());
            jsonReservation.put("reservationType", reservation.getReservationType());
            jsonReservation.put("dateTimeLeave", reservation.getDateTimeLeave());
            return jsonReservation;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject makeJsonUser(User user){
        JSONObject jsonUser = new JSONObject();
        try{
            jsonUser.put("id", user.getId());
            jsonUser.put("firstName", user.getFirstName());
            jsonUser.put("lastName", user.getLastName());
            jsonUser.put("password", user.getPassword());
            jsonUser.put("email", user.getEmail());
            jsonUser.put("mobileNumber", user.getMobileNumber());
            jsonUser.put("username", user.getUsername());
            jsonUser.put("role", user.getRole());
            return jsonUser;
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject makeJsonLocal(Local local){
        JSONObject jsonLocal = new JSONObject();
        try {
            jsonLocal.put("id", local.getId());
            jsonLocal.put("name", local.getName());
            jsonLocal.put("location", local.getLocation());
            jsonLocal.put("mobileNumber", local.getMobileNumber());
            jsonLocal.put("timetable", local.getTimetable());
            jsonLocal.put("rating", local.getRating());
            jsonLocal.put("description", local.getDescription());
            jsonLocal.put("smokingRestriction", local.getSmokingRestriction());
            jsonLocal.put("petRestriction", local.getPetRestriction());
            jsonLocal.put("wifi", local.getWifi());
            jsonLocal.put("owner", makeJsonUser(local.getOwner()));
            return jsonLocal;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
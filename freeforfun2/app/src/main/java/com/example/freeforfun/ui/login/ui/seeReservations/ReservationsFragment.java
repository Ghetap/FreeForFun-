package com.example.freeforfun.ui.login.ui.seeReservations;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.login.MainActivity;
import com.example.freeforfun.ui.model.Reservation;
import com.example.freeforfun.ui.restCalls.ReservationRestCalls;

import java.util.ArrayList;
import java.util.List;

public class ReservationsFragment extends Fragment {

    private ReservationsViewModel mViewModel;
    RecyclerView recyclerView;
    RecycleAdapterReservations recycleAdapter;
    List<String> reservationsList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       mViewModel = ViewModelProviders.of(this).get(ReservationsViewModel.class);
       View root = inflater.inflate(R.layout.reservations_fragment, container, false);
       setHasOptionsMenu(true);
       reservationsList = new ArrayList<>();
       reservationsList = createList();
       recyclerView = root.findViewById(R.id.recycleViewReservations);
       recycleAdapter = new RecycleAdapterReservations(reservationsList);
       recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
       recyclerView.setAdapter(recycleAdapter);
       DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL);
       recyclerView.addItemDecoration(dividerItemDecoration);
       return root;
    }

    private List<String> createList() {
        List<String> reservationDetails = new ArrayList<>();
        List<Reservation> reservations = ReservationRestCalls.getAllFutureReservationsOfAnUser(
                MainActivity.loggedUser.getId());
        for(Reservation reservation: reservations){
            String details = reservation.getLocal().getName() +
                    "-" +
                    reservation.getDateTimeReservation() +
                    "|" +
                    reservation.getNumberOfPlaces();
            reservationDetails.add(details);
        }
        return reservationDetails;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReservationsViewModel.class);
        // TODO: Use the ViewModel
    }

}

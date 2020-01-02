package com.example.freeforfun.ui.login.ui.favouriteLocals;

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
import com.example.freeforfun.ui.login.ui.seeLocals.RecycleAdapter;
import com.example.freeforfun.ui.model.EVoteType;
import com.example.freeforfun.ui.model.FavouriteLocalCompositeKey;
import com.example.freeforfun.ui.model.FavouriteLocals;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.restCalls.ReservationRestCalls;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.example.freeforfun.ui.restCalls.VoteRestCalls;

import java.util.ArrayList;
import java.util.List;

import static com.example.freeforfun.ui.login.MainActivity.loggedUser;

public class FavouriteLocalsFragment extends Fragment {

    private FavouriteLocalsViewModel mViewModel;
    List<String> localsList;
    RecyclerView recyclerView;
    RecycleAdapterFavouriteLocals recycleAdapterFavouriteLocals;
    List<FavouriteLocals> favouriteLocals;
    List<String> listOfVotes;
    public static FavouriteLocalsFragment newInstance() {
        return new FavouriteLocalsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.favourite_locals_fragment, container, false);
        recyclerView = root.findViewById(R.id.recycleView);
        localsList = createListOfNames();
        recycleAdapterFavouriteLocals = new RecycleAdapterFavouriteLocals(localsList,listOfVotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        recyclerView.setAdapter(recycleAdapterFavouriteLocals);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        return root;
    }

    public List<String> createListOfNames(){
        favouriteLocals = VoteRestCalls.getAllVotedLocals();
        listOfVotes = new ArrayList<>();
        List<String> listofNames = new ArrayList<>();
        List<Local> locals = UserRestCalls.getAllLocals();
        for(Local local:locals){
            List<Local> freeLocals = ReservationRestCalls.getFreeLocalsNow(local.getId());
            EVoteType vote = getVoteType(new FavouriteLocalCompositeKey(local.getId(),loggedUser.getId()));
                if( vote != null && vote.equals(EVoteType.UPVOTE)){
                    listOfVotes.add("Liked");
                    String localName = local.getName();
                    if(!freeLocals.isEmpty()){
                        localName = localName + ",F";
                    }
                    else{
                        localName = localName + ",N";
                    }
                    listofNames.add(localName);
                }
        }
        return listofNames;
    }
    boolean contains(FavouriteLocalCompositeKey id){
        for(FavouriteLocals fav:this.favouriteLocals){
            if(fav.getId().getLocalId().equals(id.getLocalId()) && fav.getId().getUserId().equals(id.getUserId()))
                return true;
        }
        return false;
    }
    EVoteType getVoteType(FavouriteLocalCompositeKey id){
        for(FavouriteLocals fav:this.favouriteLocals){
            if(fav.getId().getLocalId().equals(id.getLocalId()) && fav.getId().getUserId().equals(id.getUserId()))
                return fav.getVoteType();
        }
        return null;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FavouriteLocalsViewModel.class);
        // TODO: Use the ViewModel
    }

}

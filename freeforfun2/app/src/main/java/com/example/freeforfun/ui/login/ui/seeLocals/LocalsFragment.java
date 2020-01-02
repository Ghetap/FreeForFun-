package com.example.freeforfun.ui.login.ui.seeLocals;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.freeforfun.R;
import com.example.freeforfun.ui.model.EVoteType;
import com.example.freeforfun.ui.model.FavouriteLocalCompositeKey;
import com.example.freeforfun.ui.model.FavouriteLocals;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.example.freeforfun.ui.restCalls.VoteRestCalls;

import java.util.ArrayList;
import java.util.List;

import static com.example.freeforfun.ui.login.MainActivity.loggedUser;


public class LocalsFragment extends Fragment  {

    private LocalsViewModel slideshowViewModel;
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;
    List<String> localsList;
    List<String>localType;
    List<FavouriteLocals> favouriteLocals;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(LocalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_locals, container, false);

        setHasOptionsMenu(true);
        localsList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recycleView);
        localsList = createListOfNames();
        localType = createListOfType();
        recycleAdapter = new RecycleAdapter(localsList,localType,favouriteLocals);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        recyclerView.setAdapter(recycleAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        return root;
    }

    public List<String> createListOfType(){
        List<String> listOfTypes = new ArrayList<>();
        List<Local> locals = UserRestCalls.getAllLocals();
        for(Local local:locals){
            listOfTypes.add(local.getType().toString());
        }
        return listOfTypes;
    }
    public List<String> createListOfNames(){
        favouriteLocals = VoteRestCalls.getAllVotedLocals();
        List<String> listofNames = new ArrayList<>();
        List<Local> locals = UserRestCalls.getAllLocals();
        for(Local local:locals){
                if(getVoteType(new FavouriteLocalCompositeKey(local.getId(),loggedUser.getId())) != null){
                    if(getVoteType(new FavouriteLocalCompositeKey(local.getId(),loggedUser.getId())).equals(EVoteType.UPVOTE))
                        listofNames.add(local.getName()+'U');
                    if(getVoteType(new FavouriteLocalCompositeKey(local.getId(),loggedUser.getId())).equals(EVoteType.DOWNVOTE))
                        listofNames.add(local.getName()+'D');
                }
                else{
                    listofNames.add(local.getName());
                }
            }

        return listofNames;
    }

    EVoteType getVoteType(FavouriteLocalCompositeKey id){
        for(FavouriteLocals fav:this.favouriteLocals){
            if(fav.getId().getLocalId().equals(id.getLocalId()) && fav.getId().getUserId().equals(id.getUserId()))
                return fav.getVoteType();
        }
        return null;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.main_menu,menu);
        super.onCreateOptionsMenu(menu,inflater);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recycleAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
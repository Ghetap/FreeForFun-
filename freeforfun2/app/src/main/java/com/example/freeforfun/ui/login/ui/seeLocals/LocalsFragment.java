package com.example.freeforfun.ui.login.ui.seeLocals;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.restCalls.UserRestCalls;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class LocalsFragment extends Fragment {

    private LocalsViewModel slideshowViewModel;
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;
    List<String> localsList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(LocalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_locals, container, false);
        setHasOptionsMenu(true);
        localsList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recycleView);
        localsList = createListOfNames();
        recycleAdapter = new RecycleAdapter(localsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        recyclerView.setAdapter(recycleAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        return root;
    }


    public List<String> createListOfNames(){
        List<String> listofNames = new ArrayList<>();
        List<Local> locals = UserRestCalls.getAllLocals();
        for(Local local:locals){
            listofNames.add(local.getName());
        }
        return listofNames;
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
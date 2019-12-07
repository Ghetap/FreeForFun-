package com.example.freeforfun.ui.login.ui.seeLocals;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.restCalls.UserRestCalls;

import org.json.JSONArray;

import java.util.ArrayList;


public class LocalsFragment extends Fragment {

    private LocalsViewModel slideshowViewModel;
    private ArrayList<LocalResource> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText cardViewEditText;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        createExampleList();
        slideshowViewModel =
                ViewModelProviders.of(this).get(LocalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_locals, container, false);
        mRecyclerView = root.findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        cardViewEditText = root.findViewById(R.id.search);
        cardViewEditText.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        return root;
    }
    private void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new LocalResource("One"));
        mExampleList.add(new LocalResource("Two"));
        mExampleList.add(new LocalResource("Three"));
        mExampleList.add(new LocalResource("Four"));
        mExampleList.add(new LocalResource("Five"));
        mExampleList.add(new LocalResource("Six"));
        mExampleList.add(new LocalResource("Seven"));
        mExampleList.add(new LocalResource( "Eight"));
        mExampleList.add(new LocalResource( "Nine"));
    }

    private void populateList(){
        ArrayList<LocalResource> entireList = new ArrayList<>();
        StringBuilder stringBuilder = UserRestCalls.getAllLocals();
        String[] lines = stringBuilder.toString().split("\\n");
        for(String s: lines){
            System.out.println(s);
            entireList.add(new LocalResource(s));
        }
        mExampleList.addAll(entireList);
    }
    private void filter(String text) {
        ArrayList<LocalResource> filteredList = new ArrayList<>();

        for (LocalResource item : mExampleList) {
            if (item.getmText1().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);

//        StringBuilder stringBuilder = UserRestCalls.filterLocals(text);
//        String[] lines = stringBuilder.toString().split("\\n");
//        for(String s: lines){
//            System.out.println(s);
//            filteredList.add(new LocalResource(s));
//        }
//        mAdapter.filterList(filteredList);
    }
}
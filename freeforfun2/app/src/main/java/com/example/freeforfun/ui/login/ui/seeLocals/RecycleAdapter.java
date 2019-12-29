package com.example.freeforfun.ui.login.ui.seeLocals;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.login.LogoutActivity;
import com.example.freeforfun.ui.login.MainActivity;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.restCalls.UserRestCalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecycleAdapter";
    private List<String> localsList;
    private List<String> typeList;
    private List<String> localsListAll;
    private List<String> copyTypeList;
    private List<Local> locals = UserRestCalls.getAllLocals();
    public static Local clickedLocal;

    public RecycleAdapter(List<String> localsList,List<String> typeList) {
        this.typeList = typeList;
        this.localsList = localsList;
        this.copyTypeList = new ArrayList<>();
        this.localsListAll = new ArrayList<>(localsList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewtitle.setText(localsList.get(position));
        if(position < typeList.size())
            holder.typeTextView.setText(typeList.get(position).toLowerCase());
    }

    @Override
    public int getItemCount() {
        return localsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredLisr = new ArrayList<>();
            List<String> types = new ArrayList<>(Arrays.asList("pub", "restaurant", "club", "terrace",
                    "confectiontery", "fast food", "sky bar", "pizzeria"));
            if(constraint.toString().isEmpty()){
                filteredLisr.addAll(localsListAll);
                locals = UserRestCalls.getAllLocals();
                copyTypeList.clear();
                for(String local:filteredLisr){
                    for(Local local1:locals){
                        if(local.equals(local1.getName())){
                            copyTypeList.add(local1.getType().toString());
                        }
                    }
                }
                typeList.clear();
                typeList.addAll(copyTypeList);
            }
            else{
                if(types.contains(constraint)){

                    List<Local> locals = new ArrayList<>();
                    locals = UserRestCalls.filterLocals(constraint.toString().toUpperCase());
                    for(Local local:locals){
                        filteredLisr.add(local.getName());
                    }
                    copyTypeList.clear();
                    for(String local:filteredLisr){
                        for(Local local1:locals){
                            if(local.equals(local1.getName())){
                                copyTypeList.add(local1.getType().toString());
                            }
                        }
                    }
                    typeList.clear();
                    typeList.addAll(copyTypeList);
                }
                else{
                    List<Local> locals = new ArrayList<>();
                    locals = UserRestCalls.getAllLocals();
                    for(String local:localsListAll){
                            if(local.toLowerCase().contains(constraint.toString().toLowerCase())){
                                filteredLisr.add(local);
                            }
                    }
                    copyTypeList.clear();
                    for(String local:filteredLisr){
                        for(Local local1:locals){
                            if(local.equals(local1.getName())){
                                copyTypeList.add(local1.getType().toString());
                            }
                        }
                    }
                    typeList.clear();
                    typeList.addAll(copyTypeList);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredLisr;
            return filterResults;
        }

        //run on a ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            localsList.clear();
            localsList.addAll((Collection<? extends String>) results.values);
            notifyDataSetChanged();
        }
    };
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textViewtitle, typeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewtitle = itemView.findViewById(R.id.textViewTitle);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    localsList.remove(getAdapterPosition());
                    typeList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });

        }

        @Override
        public void onClick(View v) {
            String localName = localsList.get(getAdapterPosition());
            for(Local local: locals){
                if(local.getName().equals(localName)) {
                    clickedLocal = local;
                    break;
                }
            }
//            Toast.makeText(v.getContext(),localsList.get(getAdapterPosition()),Toast.LENGTH_SHORT).show();
            Intent logoutIntent = new Intent(v.getContext(), LocalActivity.class);
            v.getContext().startActivity(logoutIntent);
    }
}
}

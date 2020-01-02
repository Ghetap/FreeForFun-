package com.example.freeforfun.ui.login.ui.seeLocals;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.freeforfun.R;
import com.example.freeforfun.ui.model.EVoteType;
import com.example.freeforfun.ui.model.FavouriteLocals;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.restCalls.ReservationRestCalls;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.example.freeforfun.ui.restCalls.VoteRestCalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecycleAdapter";
    private List<String> localsList;
    private List<String> typeList;
    private List<String> localsListAll;
    private List<String> copyTypeList;
    private boolean once = false;
    private List<Local> locals = UserRestCalls.getAllLocals();
    public static Local clickedLocal;
    private List<FavouriteLocals> favouriteLocals;

    public RecycleAdapter(List<String> localsList,List<String> typeList, List<FavouriteLocals> favouriteLocals) {
        this.typeList = typeList;
        this.localsList = localsList;
        this.copyTypeList = new ArrayList<>();
        this.localsListAll = new ArrayList<>(localsList);
        this.favouriteLocals = new ArrayList<>(favouriteLocals);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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
        String[] localDetails = localsList.get(position).split(",");
        int len = localDetails[0].length();
        char c = localDetails[0].charAt(len-1);
        String freePlaces = localDetails[1];
        if(c == 'U'){
            String text = localsList.get(position).substring(0,len-1);
            holder.textViewtitle.setText(text);
            localsList.set(position,text);
            holder.likeImage.setVisibility(View.INVISIBLE);
            holder.likeImageVoted.setVisibility(View.VISIBLE);
        }else if (c=='D'){
            String text = localsList.get(position).substring(0,len-1);
            holder.textViewtitle.setText(text);
            localsList.set(position,text);

            holder.dislikeImage.setVisibility(View.INVISIBLE);
            holder.dislikeImageVoted.setVisibility(View.VISIBLE);
        }else
            holder.textViewtitle.setText(localsList.get(position));
        if(position < typeList.size()){
            holder.typeTextView.setText(typeList.get(position).toLowerCase());
        }
        if(freePlaces.equals("F")){
            holder.freePlaces.setVisibility(View.VISIBLE);
            holder.notFreePlaces.setVisibility(View.INVISIBLE);
        }
        else{
            holder.freePlaces.setVisibility(View.INVISIBLE);
            holder.notFreePlaces.setVisibility(View.VISIBLE);
        }
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

                    List<Local> locals;
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
                    List<Local> locals;
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
        ImageView imageView,likeImage,dislikeImage,likeImageVoted,dislikeImageVoted,
                freePlaces, notFreePlaces;
        TextView textViewtitle, typeTextView;
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewtitle = itemView.findViewById(R.id.textViewTitle);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            likeImage = itemView.findViewById(R.id.like);
            dislikeImage = itemView.findViewById(R.id.dislike);
            dislikeImageVoted = itemView.findViewById(R.id.dislike_voted);
            likeImageVoted = itemView.findViewById(R.id.like_voted);
            freePlaces = itemView.findViewById(R.id.availableImage);
            notFreePlaces = itemView.findViewById(R.id.notAvailableImage);
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

            likeImage.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    String localName = localsList.get(getAdapterPosition());
                    for(Local local: locals){
                        if(local.getName().equals(localName)) {
                            clickedLocal = local;
                            break;
                        }
                    }
                    //Drawable highlight = v.getResources().getDrawable( R.drawable.circle);
                    FavouriteLocals local = VoteRestCalls.getLocal(clickedLocal.getId());
                    if(local ==null){
                        VoteRestCalls.like(clickedLocal.getId());
                        likeImage.setVisibility(View.INVISIBLE);
                        likeImageVoted.setVisibility(View.VISIBLE);
                    }else {
                        VoteRestCalls.deleteVote(clickedLocal.getId());
                        VoteRestCalls.like(clickedLocal.getId());
                        dislikeImage.setVisibility(View.VISIBLE);
                        dislikeImageVoted.setVisibility(View.INVISIBLE);
                        likeImage.setVisibility(View.INVISIBLE);
                        likeImageVoted.setVisibility(View.VISIBLE);
                    }
                }
            });
            dislikeImage.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    String localName = localsList.get(getAdapterPosition());
                    for(Local local: locals){
                        if(local.getName().equals(localName)) {
                            clickedLocal = local;
                            break;
                        }
                    }
                    FavouriteLocals local = VoteRestCalls.getLocal(clickedLocal.getId());
                    if(local == null ){
                        VoteRestCalls.dislike(clickedLocal.getId());
                        dislikeImage.setVisibility(View.INVISIBLE);
                        dislikeImageVoted.setVisibility(View.VISIBLE);
                    }else {
                        VoteRestCalls.deleteVote(clickedLocal.getId());
                        VoteRestCalls.dislike(clickedLocal.getId());
                        likeImage.setVisibility(View.VISIBLE);
                        likeImageVoted.setVisibility(View.INVISIBLE);
                        dislikeImage.setVisibility(View.INVISIBLE);
                        dislikeImageVoted.setVisibility(View.VISIBLE);
                    }
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
            Intent logoutIntent = new Intent(v.getContext(), LocalActivity.class);
            v.getContext().startActivity(logoutIntent);
    }
}
}

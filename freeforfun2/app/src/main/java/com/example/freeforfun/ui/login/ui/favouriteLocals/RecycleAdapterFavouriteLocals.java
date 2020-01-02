package com.example.freeforfun.ui.login.ui.favouriteLocals;


import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.login.ui.seeLocals.LocalActivity;
import com.example.freeforfun.ui.model.EVoteType;
import com.example.freeforfun.ui.model.FavouriteLocals;
import com.example.freeforfun.ui.model.Local;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.example.freeforfun.ui.restCalls.VoteRestCalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RecycleAdapterFavouriteLocals extends RecyclerView.Adapter<RecycleAdapterFavouriteLocals.ViewHolder> {

    private List<String> localsList;
    private List<Local> locals = UserRestCalls.getAllLocals();
    public static Local clickedLocal;
    private List<String> listOfVotes;
    public RecycleAdapterFavouriteLocals(List<String> localsList, List<String> listOfVotes) {
        this.localsList = localsList;
        this.listOfVotes = new ArrayList<>(listOfVotes);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item_favouritelocals,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewtitle.setText(localsList.get(position));
        holder.typeVoteTextView.setText(listOfVotes.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return localsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textViewtitle, typeVoteTextView;
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewtitle = itemView.findViewById(R.id.textViewTitle);
            typeVoteTextView = itemView.findViewById(R.id.typeVoteTextView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    localsList.remove(getAdapterPosition());
                    listOfVotes.remove(getAdapterPosition());
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
            Intent logoutIntent = new Intent(v.getContext(), LocalActivity.class);
            v.getContext().startActivity(logoutIntent);
    }
}
}

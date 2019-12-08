package com.example.freeforfun.ui.login.ui.seeLocals;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.freeforfun.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecycleAdapter";
    List<String> localsList;
    List<String> localsListAll;

    public RecycleAdapter(List<String> localsList) {
        this.localsList = localsList;
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
        holder.rowCountTextView.setText(String.valueOf(position));
        holder.textViewtitle.setText(localsList.get(position));

       // holder.imageView.setImage();
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
            if(constraint.toString().isEmpty()){
                filteredLisr.addAll(localsListAll);
            }
            else{
                for(String local:localsListAll){
                    if(local.toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredLisr.add(local);
                    }
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
        TextView textViewtitle, rowCountTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewtitle = itemView.findViewById(R.id.textViewTitle);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    localsList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),localsList.get(getAdapterPosition()),Toast.LENGTH_SHORT).show();
        }
    }
}

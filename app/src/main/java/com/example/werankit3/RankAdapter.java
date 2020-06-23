package com.example.werankit3;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class RankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String ranks[] = {};
    Vector<String> rVector;

    public RankAdapter(Vector<String> r) {
        this.rVector = r;
        rVector.toArray(ranks);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);
        return new RankHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String rank = (rVector.elementAt(position));
        ((RankHolder)holder).setDetails(rank);
    }

    @Override
    public int getItemCount() {
        return rVector.size();
    }

    public class RankHolder extends RecyclerView.ViewHolder {

        private TextView txtRank;

        public RankHolder(View itemView) {
            super(itemView);
            txtRank = itemView.findViewById(R.id.textRank);
        }

        public void setDetails(String r) {
            String rank = String.format("%s.", r);
            txtRank.setText(rank);
        }
    }
}

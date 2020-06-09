package com.example.werankit3;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModifyListPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_HEADER = 1;
    private static int TYPE_ITEM = 2;
    private Context context;
    private ArrayList<ModifyListPageItem> items;

    public ModifyListPageAdapter(Context context, ArrayList<ModifyListPageItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_modlist_header, parent, false);
            return new HeaderViewHolder(view);
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.item_modlist_header, parent, false);
            return new ObjectViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER)
            ((HeaderViewHolder)holder).SetHeaderDetails(items.get(position));
        else
            ((ObjectViewHolder)holder).SetItemDetails(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(items.get(position).getRank())) {
            return TYPE_HEADER;
        }
        else
            return TYPE_ITEM;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private TextView txtDescription;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtModListTitle);
            txtDescription = itemView.findViewById(R.id.txtModListDescription);
        }

        private void SetHeaderDetails(ModifyListPageItem item) {
            txtTitle.setText(item.getTitle());
            txtDescription.setText(item.getDescription());
        }
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {

        private TextView txtRank;
        //private Spinner spinner;

        public ObjectViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRank = itemView.findViewById(R.id.modlist_rank);
            //spinner = itemView.findViewById(R.id.static_spinner);
        }

        private void SetItemDetails(ModifyListPageItem item) {
            txtRank.setText(item.getRank());
        }
    }
}

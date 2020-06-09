package com.example.werankit3;

import android.content.Context;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_HEADER = 1;
    private static int TYPE_LABEL = 2;
    private static int TYPE_ITEM = 3;
    private Context context;
    private ArrayList<AccountPageItem> items;

    public AccountPageAdapter(Context context, ArrayList<AccountPageItem> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_account_header, parent, false);
            return new HeaderViewHolder(view);
        }
        else if (viewType == TYPE_LABEL) {
            view = LayoutInflater.from(context).inflate(R.layout.item_account_label, parent, false);
            return new LabelViewHolder(view);
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.item_homelist, parent, false);
            return new ListViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER)
            ((HeaderViewHolder)holder).SetHeaderDetails(items.get(position));
        else if (getItemViewType(position) == TYPE_LABEL)
            ((LabelViewHolder)holder).SetLabelDetails(items.get(position));
        else
            ((ListViewHolder)holder).SetListDetails(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (!TextUtils.isEmpty(items.get(position).getAccountName()))
            return TYPE_HEADER;
        else if (TextUtils.isEmpty(items.get(position).getDescription()))
            return TYPE_LABEL;
        else
            return TYPE_ITEM;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView txtAccountName;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAccountName = itemView.findViewById(R.id.txtAccountName);
        }

        private void SetHeaderDetails(AccountPageItem item) {
            txtAccountName.setText(item.getAccountName());
        }
    }

    class LabelViewHolder extends RecyclerView.ViewHolder {

        private TextView txtLabel;

        public LabelViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLabel = itemView.findViewById((R.id.txtLabel));
        }

        private void SetLabelDetails(AccountPageItem item) {
            txtLabel.setText(item.getTitle());
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private TextView txtDescription;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }

        private void SetListDetails(AccountPageItem item) {
            txtTitle.setText(item.getTitle());
            txtDescription.setText(item.getDescription());
        }
    }
}

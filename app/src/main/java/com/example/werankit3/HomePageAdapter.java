package com.example.werankit3;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_HEADING = 1;
    private static int TYPE_ITEM = 2;
    private static int TYPE_BANNER = 3;
    private Context context;
    private ArrayList<HomePageItem> items;
    private OnListItemListener mOnListItemListener;


    public HomePageAdapter(Context context, ArrayList<HomePageItem> items, OnListItemListener onListItemListener) {
        this.context = context;
        this.items = items;
        this.mOnListItemListener = onListItemListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADING) {
            view = LayoutInflater.from(context).inflate(R.layout.item_heading, parent, false);
            return new HeadingViewHolder(view);
        }
        else if (viewType == TYPE_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.item_homelist, parent, false);
            return new ItemViewHolder(view, mOnListItemListener);
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.item_homepage_banner, parent, false);
            return new BannerViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_ITEM)
            ((ItemViewHolder)holder).SetItemDetails(items.get(position));
        else if (getItemViewType(position) == TYPE_HEADING) {
            ((HeadingViewHolder)holder).SetHeadingDetails(items.get(position));
        }
        else {
            ((BannerViewHolder) holder).SetBannerDetails(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(items.get(position).getDescription())) {
            return TYPE_HEADING;
        }
        else if (TextUtils.isEmpty(items.get(position).getTitle())){
            return TYPE_BANNER;
        }
        else
            return TYPE_ITEM;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtTitle;
        private TextView txtDescription;
        // private ImageView image;
        OnListItemListener onListItemListener;

        public ItemViewHolder(@NonNull View itemView, OnListItemListener onListItemListener) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            // image = itemView.findViewById(R.id.image);
            this.onListItemListener = onListItemListener;

            itemView.setOnClickListener(this);
        }

        private void SetItemDetails(HomePageItem item) {
            txtTitle.setText(item.getTitle());
            txtDescription.setText(item.getDescription());
        }

        @Override
        public void onClick(View v) {
            onListItemListener.OnListItemClick(getAdapterPosition());

        }
    }

    class HeadingViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;

        public HeadingViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }

        private void SetHeadingDetails(HomePageItem heading) {
            txtTitle.setText(heading.getTitle());
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        private void SetBannerDetails(HomePageItem heading) {

        }
    }

    public interface OnListItemListener {
        void OnListItemClick(int position);
    }
}

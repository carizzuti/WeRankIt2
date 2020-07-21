package com.example.werankit3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werankit3.utils.JSONHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModifyListPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {

    private static int TYPE_HEADER = 1;
    private static int TYPE_ITEM = 2;
    private List<ModifyListPageItem> items;

    private final StartDragListener mStartDragListener;

    public ModifyListPageAdapter(List<ModifyListPageItem> items, StartDragListener startDragListener) {
        mStartDragListener = startDragListener;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        /*if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_modify_list2, parent, false);
            return new HeaderViewHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modlist_object, parent, false);
            return new ItemViewHolder(view);
        }*/

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modlist_object, parent, false);
        return new ItemViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        /*if (getItemViewType(position) == TYPE_HEADER)
            ((HeaderViewHolder)holder).SetHeaderDetails(items.get(position));
        else {
            ((ItemViewHolder) holder).SetItemDetails(items.get(position));

            ((ItemViewHolder) holder).dragImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mStartDragListener.requestDrag(holder);
                    }
                    return false;
                }
            });
        }*/

        ((ItemViewHolder) holder).SetItemDetails(items.get(position));

        ((ItemViewHolder) holder).dragImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.requestDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        if (!TextUtils.isEmpty(items.get(position).getDescription())) {
            return TYPE_HEADER;
        }
        else
            return TYPE_ITEM;
    }*/

    /*public class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private TextView txtDescription;
        private ImageView myImage;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtModListTitle);
            txtDescription = itemView.findViewById(R.id.txtModListDescription);
            myImage = itemView.findViewById(R.id.modlist_image);
        }

        private void SetHeaderDetails(ModifyListPageItem item) {
            txtTitle.setText(item.getTitle());
            txtDescription.setText(item.getDescription());
            myImage.setImageResource(item.getImage());
        }
    }*/

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private ImageView itemImage, dragImage;
        View rowView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtItemName);
            itemImage = itemView.findViewById(R.id.item_image);
            dragImage = itemView.findViewById(R.id.drag_image);
            rowView = itemView;
        }

        private void SetItemDetails(ModifyListPageItem item) {
            txtName.setText(item.getTitle());
            itemImage.setImageResource(item.getImage());
        }
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(items, i, i + 1);
            }
        }
        else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(items, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(ModifyListPageAdapter.ItemViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(ModifyListPageAdapter.ItemViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.WHITE);
    }

    public List<ModifyListPageItem> updateList() {
        return items;
    }
}

package com.example.werankit3;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class ModifyListActivity extends AppCompatActivity implements StartDragListener {

    RecyclerView recyclerViewList, recyclerViewRank;
    ModifyListPageAdapter mAdapterList;
    RankAdapter mAdapterRank;
    ItemTouchHelper touchHelper;

    //ArrayList<ModifyListPageItem> items = new ArrayList<>();

    String s1[];
    int images[] = { R.drawable.res_evil_logo, R.drawable.res_evil_1, R.drawable.res_evil_2, R.drawable.res_evil_3,
            R.drawable.res_evil_4, R.drawable.res_evil_5, R.drawable.res_evil_6, R.drawable.res_evil_7};
    Vector<String> ranks = new Vector<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_list2);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.message:
                        startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.createlist:
                        startActivity(new Intent(getApplicationContext(), CreateListActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        s1 = getResources().getStringArray(R.array.resident_evil_games);
        recyclerViewList = findViewById(R.id.recyclerViewModifyList);
        recyclerViewRank = findViewById(R.id.recyclerViewRank);

        createHeader();
        createList();

        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRank.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createHeader() {
        TextView title = findViewById(R.id.txtModListTitle);
        TextView description = findViewById(R.id.txtModListDescription);
        ImageView image = findViewById(R.id.modlist_image);

        title.setText(s1[0]);
        description.setText(s1[1]);
        image.setImageResource(images[0]);
    }

    public void createList() {
        ArrayList<ModifyListPageItem> items = new ArrayList<>();

        ModifyListPageItem item;

        /*item = new ModifyListPageItem();
        item.setTitle(s1[0]);
        item.setDescription(s1[1]);
        item.setImage(images[0]);
        items.add(item);*/

        for (int i = 2; i < s1.length; i++) {
            item = new ModifyListPageItem();
            ranks.add(String.valueOf(i - 1));

            item.setTitle(s1[i]);
            item.setImage(images[i - 1]);
            items.add(item);
        }

        mAdapterList = new ModifyListPageAdapter(items, this);
        mAdapterRank = new RankAdapter(ranks);

        ItemTouchHelper.Callback callback = new ItemMoveCallback(mAdapterList);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewList);

        recyclerViewList.setAdapter(mAdapterList);
        recyclerViewRank.setAdapter(mAdapterRank);
    }

    @Override
    public void requestDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }
}

package com.example.werankit3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ModifyListActivity extends Activity {

    private RecyclerView recyclerView;

    String s1[];
    int images[] = { R.drawable.res_evil_logo, R.drawable.res_evil_1, R.drawable.res_evil_2, R.drawable.res_evil_3,
            R.drawable.res_evil_4, R.drawable.res_evil_5, R.drawable.res_evil_6, R.drawable.res_evil_7};

    //Spinner staticSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_list2);

        s1 = getResources().getStringArray(R.array.resident_evil_games);

/*        LayoutInflater inflater = getLayoutInflater();
        View tmpView;
        tmpView = inflater.inflate(R.layout.item_modlist_object, null);
        getWindow().addContentView(tmpView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));*/

        //staticSpinner = getWindow().findViewById(R.id.static_spinner);

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

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewModifyList);
        createList();
    }

    private void createList() {
        ArrayList<ModifyListPageItem> items = new ArrayList<>();

        ModifyListPageItem item;

        item = new ModifyListPageItem();
        item.setTitle(s1[0]);
        item.setDescription(s1[1]);
        item.setImage(images[0]);
        items.add(item);

        for (int i = 2; i < s1.length; i++) {
            item = new ModifyListPageItem();
            int rank = i - 1;

            item.setRank(rank + ". ");
            item.setTitle(s1[i]);
            item.setImage(images[i - 1]);
            items.add(item);
        }

        // set adapter
        ModifyListPageAdapter adapter = new ModifyListPageAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

/*    private Spinner createSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.resident_evil_games,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        return staticSpinner;
    }*/
}

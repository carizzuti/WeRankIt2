package com.example.werankit3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        return true;
                }
                return false;
            }
        });

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewHome);
        createList();
    }

    private void createList() {
        ArrayList<HomePageItem> items = new ArrayList<>();

        HomePageItem item = new HomePageItem();
        items.add(item);

        item = new HomePageItem();
        item.setTitle("Developer Picks");
        items.add(item);

        item = new HomePageItem();
        item.setTitle("Star Wars Main Films");
        item.setDescription("Ranking the numbered Star Wars movies");
        item.setUserCreated(false);
        items.add(item);

        item = new HomePageItem();
        item.setTitle("Final Fantasy Games");
        item.setDescription("Ranking all Final Fantasy games");
        item.setUserCreated(false);
        items.add(item);

        item = new HomePageItem();
        item.setTitle("Popular User Created Lists");
        items.add(item);

        item = new HomePageItem();
        item.setTitle("Resident Evil Games");
        item.setDescription("Ranking all numbered Resident Evil games");
        item.setUserCreated(true);
        items.add(item);

        item = new HomePageItem();
        item.setTitle("Seasons of The Office");
        item.setDescription("Ranking all seasons of The Office");
        item.setUserCreated(true);
        items.add(item);

        // set adapter
        HomePageAdapter adapter = new HomePageAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void BottomNavigation(R.id id) {

    }
}


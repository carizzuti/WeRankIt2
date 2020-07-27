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

public class AccountActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.account);

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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAccount);
        createList();
    }

    private void createList() {
        ArrayList<AccountPageItem> items = new ArrayList<>();

        AccountPageItem item = new AccountPageItem();
        items.add(item);

        item = new AccountPageItem();
        item.setAccountName(MainActivity.USER_ID);
        items.add(item);

        item = new AccountPageItem();
        item.setTitle("Created Lists");
        items.add(item);

        item = new AccountPageItem();
        item.setTitle("Resident Evil Games");
        item.setDescription("Ranking all Resident Evil games");
        item.setUserCreated(true);
        items.add(item);

        item = new AccountPageItem();
        item.setTitle("Modified Lists");
        items.add(item);

        item = new AccountPageItem();
        item.setTitle("Star Wars Main Films");
        item.setDescription("Ranking the numbered Star Wars movies");
        item.setUserCreated(false);
        items.add(item);

        item = new AccountPageItem();
        item.setTitle("Final Fantasy Games");
        item.setDescription("Ranking all Final Fantasy games");
        item.setUserCreated(false);
        items.add(item);

        // set adapter
        AccountPageAdapter adapter = new AccountPageAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}

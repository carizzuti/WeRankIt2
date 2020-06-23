package com.example.werankit3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<HomePageItem> items = new ArrayList<>();
    HomePageItem item = new HomePageItem();

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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewHome);
        items.add(item);
        createList();
    }

    private void createList() {
        HomePageItem item = new HomePageItem();
        items.add(item);

        item = new HomePageItem();
        item.setTitle("Developer Picks");
        items.add(item);

        parseJSON(false);



        item = new HomePageItem();
        item.setTitle("Popular User Created Lists");
        items.add(item);

        parseJSON(true);

        // set adapter
        HomePageAdapter adapter = new HomePageAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void parseJSON(boolean userCreated) {
        JSONObject obj;

        try {
            if (userCreated)
                obj = new JSONObject(loadJSONFromAsset("user_created_lists.json"));
            else
                obj = new JSONObject(loadJSONFromAsset("dev_created_lists.json"));

            JSONArray listArray = obj.getJSONArray("lists");

            for (int i = 0; i <listArray.length(); i++) {
                JSONObject listDetail = listArray.getJSONObject(i);

                item = new HomePageItem();
                item.setTitle(listDetail.getString("title"));
                item.setDescription(listDetail.getString("description"));
                item.setUserCreated(listDetail.getBoolean("userCreated"));
                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset(String filename) {
        String json = null;

        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}



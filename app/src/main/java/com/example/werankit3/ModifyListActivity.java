package com.example.werankit3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

public class ModifyListActivity extends AppCompatActivity implements StartDragListener {

    private static int TYPE_HEADER = 1;
    private static int TYPE_ITEM = 2;
    RecyclerView recyclerViewList, recyclerViewRank;
    ModifyListPageAdapter mAdapterList;
    RankAdapter mAdapterRank;
    ItemTouchHelper touchHelper;

    ArrayList<ModifyListPageItem> items = new ArrayList<>();
    ModifyListPageItem item;

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

        Intent intent = getIntent();
        boolean userCreatedList = intent.getBooleanExtra(MainActivity.EXTRA_USER_CREATED, false);
        int list_id = intent.getIntExtra(MainActivity.EXTRA_ID, 1);

        recyclerViewList = findViewById(R.id.recyclerViewModifyList);
        recyclerViewRank = findViewById(R.id.recyclerViewRank);

        ViewGroup.LayoutParams params = recyclerViewList.getLayoutParams();
        recyclerViewList.setLayoutParams(params);

        params = recyclerViewRank.getLayoutParams();
        recyclerViewRank.setLayoutParams(params);

        createHeader(list_id, userCreatedList);
        createList(list_id, userCreatedList);

        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRank.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createHeader(int list_id, boolean userCreated) {
        parseJSON(TYPE_HEADER, userCreated, list_id);
    }

    public void createList(int list_id, boolean userCreated) {

        parseJSON(TYPE_ITEM, userCreated, list_id);

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

    private void parseJSON(int viewType, boolean userCreated, int list_id) {
        JSONObject obj;

        try {

            if (userCreated)
                obj = new JSONObject(loadJSONFromAsset("user_created_lists.json"));
            else
                obj = new JSONObject(loadJSONFromAsset("dev_created_lists.json"));

            JSONArray listArray = obj.getJSONArray("lists");

            //for (int i = 0; i < listArray.length(); i++) {
                JSONObject listDetail = listArray.getJSONObject(list_id - 1);

                // header
                if (viewType == 1) {
                    TextView title = findViewById(R.id.txtModListTitle);
                    TextView description = findViewById(R.id.txtModListDescription);
                    ImageView image = findViewById(R.id.modlist_image);

                    title.setText(listDetail.getString("title"));
                    description.setText(listDetail.getString("description"));
                }
                // list item
                else {
                    JSONObject listItem = listDetail.getJSONObject("listObjects");

                    for (int i = 0; i < listItem.length(); i++) {

                        item = new ModifyListPageItem();
                        ranks.add(String.valueOf(i + 1));

                        item.setTitle(listItem.getString("object" + (i+1)));
                        //item.setImage(images[i - 1]);
                        items.add(item);
                    }
                }
            //}
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

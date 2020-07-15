package com.example.werankit3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Vector;

import static java.lang.System.out;

public class ModifyListActivity extends AppCompatActivity implements StartDragListener {

    private static int TYPE_HEADER = 1;
    private static int TYPE_ITEM = 2;
    RecyclerView recyclerViewList, recyclerViewRank;
    ModifyListPageAdapter mAdapterList;
    RankAdapter mAdapterRank;
    ItemTouchHelper touchHelper;
    Button btnSave;

    ArrayList<ModifyListPageItem> items = new ArrayList<>();
    ModifyListPageItem item;

    Vector<String> ranks = new Vector<String>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        btnSave = findViewById(R.id.button3);

        createHeader(list_id, userCreatedList);
        createList(list_id, userCreatedList);

        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRank.setLayoutManager(new LinearLayoutManager(this));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                saveToFile();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createHeader(int list_id, boolean userCreated) {
        parseJSON(TYPE_HEADER, userCreated, list_id);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
                    item = new ModifyListPageItem();

                    TextView title = findViewById(R.id.txtModListTitle);
                    TextView description = findViewById(R.id.txtModListDescription);
                    ImageView image = findViewById(R.id.modlist_image);

                    item.setTitle(listDetail.getString("title"));
                    item.setDescription(listDetail.getString("description"));

                    title.setText(item.getTitle());
                    description.setText(item.getTitle());

                    items.add(item);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveToFile() {
        JSONObject obj = new JSONObject();
        JSONObject listObject = new JSONObject();

        try {
            obj.put("list_id", 2);
            obj.put("title", (items.get(0).getTitle()));
            obj.put("description", (items.get(0).getDescription()));
            obj.put("userCreated", (items.get(0).isUserCreated()));
            obj.put("creator", "crizzuti94");

            org.json.simple.JSONArray list = new org.json.simple.JSONArray();

            for (int i = 1; i < items.size(); i++) {
                list.add(listObject.put("object" + i, items.get(i).getTitle()));
            }

            obj.put("listObjects", list);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fOut = openFileOutput("myJSON.json", MODE_APPEND | MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(obj.toString());
            osw.flush();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

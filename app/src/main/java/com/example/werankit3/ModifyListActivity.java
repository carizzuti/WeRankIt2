package com.example.werankit3;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
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

import com.example.werankit3.utils.JSONHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static java.lang.System.out;

public class ModifyListActivity extends AppCompatActivity implements StartDragListener {

    private static int TYPE_HEADER = 1;
    private static int TYPE_ITEM = 2;
    private static final String TAG = "JSONHelper";

    RecyclerView recyclerViewList, recyclerViewRank;
    ModifyListPageAdapter mAdapterList;
    RankAdapter mAdapterRank;
    ItemTouchHelper touchHelper;
    Button btnSave;

    List<ModifyListPageItem> listItems = new ArrayList<>();
    List<ModifyListPageItem> fullList = new ArrayList<>();
    ModifyListPageItem item;
    ModifyListPageItem listHead;

    FirebaseAuth firebaseAuth;

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

        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        boolean userCreatedList = intent.getBooleanExtra(MainActivity.EXTRA_USER_CREATED, false);
        String listName = intent.getStringExtra(MainActivity.EXTRA_NAME);
        int list_id = intent.getIntExtra(MainActivity.EXTRA_ID, 1);

        recyclerViewList = findViewById(R.id.recyclerViewModifyList);
        recyclerViewRank = findViewById(R.id.recyclerViewRank);
        btnSave = findViewById(R.id.button3);

        createHeader(list_id, userCreatedList, listName);
        createList(list_id, userCreatedList, listName);

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
    private void createHeader(int list_id, boolean userCreated, String name) {
        parseJSON(TYPE_HEADER, userCreated, list_id, name);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createList(int list_id, boolean userCreated, String name) {

        parseJSON(TYPE_ITEM, userCreated, list_id, name);

        mAdapterList = new ModifyListPageAdapter(listItems, this);
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
    private void parseJSON(int viewType, boolean userCreated, int list_id, String listName) {
      String fileName = firebaseAuth.getCurrentUser().getEmail() + "_" + listName + "_" + "modified_list.json";
      File file = new File(getFilesDir(), fileName);

      if (file.exists()) {
          fullList = JSONHelper.importFromJSON(this, fileName);

          if (viewType == 1) {
              listHead = new ModifyListPageItem();

              TextView title = findViewById(R.id.txtModListTitle);
              TextView description = findViewById(R.id.txtModListDescription);

              listHead.setTitle(fullList.get(0).getTitle());
              listHead.setDescription(fullList.get(0).getDescription());

              title.setText(listHead.getTitle());
              description.setText(listHead.getTitle());
          }
          else {
              for (int j = 1; j < fullList.size(); j++) {
                  item = new ModifyListPageItem();
                  ranks.add(String.valueOf(j));

                  item.setTitle(fullList.get(j).getTitle());
                  listItems.add(item);
              }
          }
      }
      else {

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
                  listHead = new ModifyListPageItem();

                  TextView title = findViewById(R.id.txtModListTitle);
                  TextView description = findViewById(R.id.txtModListDescription);
                  ImageView image = findViewById(R.id.modlist_image);

                  listHead.setTitle(listDetail.getString("title"));
                  listHead.setDescription(listDetail.getString("description"));

                  title.setText(listHead.getTitle());
                  description.setText(listHead.getTitle());

                  fullList.add(listHead);
              }
              // list item
              else {
                  JSONObject listItem = listDetail.getJSONObject("listObjects");

                  for (int j = 0; j < listItem.length(); j++) {
                      item = new ModifyListPageItem();
                      ranks.add(String.valueOf(j + 1));

                      item.setTitle(listItem.getString("object" + (j + 1)));
                      //item.setImage(images[i - 1]);*/
                      fullList.add(item);
                      listItems.add(item);
                  }
              }
              //}
          } catch (JSONException e) {
              e.printStackTrace();
          }
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
        fullList = mAdapterList.updateList();
        fullList.add(0, listHead);
        boolean result = JSONHelper.exportToJson(this, fullList, firebaseAuth.getCurrentUser().getEmail() + "_" + listHead.getTitle() + "_modified_list.json");

        if (result) {
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
        }
    }
}

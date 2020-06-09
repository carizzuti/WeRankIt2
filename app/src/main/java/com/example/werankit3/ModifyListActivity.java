package com.example.werankit3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModifyListActivity extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_list2);

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewModifyList);
        createList();
    }

    private void createList() {
        ArrayList<ModifyListPageItem> items = new ArrayList<>();

        ModifyListPageItem item = new ModifyListPageItem();
        items.add(item);

        item = new ModifyListPageItem();
        item.setTitle("Resident Evil Games");
        item.setDescription("Ranking all Resident Evil games");
        items.add(item);

        item = new ModifyListPageItem();
        item.setRank("1.");
        //item.setSpinner(CreateSpinner());
        items.add(item);

        item = new ModifyListPageItem();
        item.setRank("2.");
        //item.setSpinner(CreateSpinner());
        items.add(item);

        item = new ModifyListPageItem();
        item.setRank("3.");
        //item.setSpinner(CreateSpinner());
        items.add(item);

        // set adapter
        ModifyListPageAdapter adapter = new ModifyListPageAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

/*    private Spinner CreateSpinner() {
        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.resident_evil_games,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        return staticSpinner;
    }*/
}

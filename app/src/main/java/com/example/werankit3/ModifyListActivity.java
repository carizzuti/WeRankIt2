package com.example.werankit3;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModifyListActivity extends Activity {

    private RecyclerView recyclerView;
    //Spinner staticSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_list2);

/*        LayoutInflater inflater = getLayoutInflater();
        View tmpView;
        tmpView = inflater.inflate(R.layout.item_modlist_object, null);
        getWindow().addContentView(tmpView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));*/

        //staticSpinner = getWindow().findViewById(R.id.static_spinner);

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
        item.setTitle("Resident Evil Games");
        item.setDescription("Ranking all Resident Evil games");
        items.add(item);

        item = new ModifyListPageItem();
        item.setRank("1.");
        //item.setSpinner(createSpinner());
        items.add(item);

        item = new ModifyListPageItem();
        item.setRank("2.");
        //item.setSpinner(createSpinner());
        items.add(item);

        item = new ModifyListPageItem();
        item.setRank("3.");
        //item.setSpinner(createSpinner());
        items.add(item);

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

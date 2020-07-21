package com.example.werankit3.utils;

import android.content.Context;

import com.example.werankit3.ModifyListPageItem;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JSONHelper {

    private static final String TAG = "JSONHelper";

    public static boolean exportToJson(Context context, List<ModifyListPageItem> itemList, String fileName) {
        ListItems listData = new ListItems();
        listData.setListItems(itemList);

        Gson gson = new Gson();
        String jsonString = gson.toJson(listData);

        FileOutputStream fos = null;
        File file = new File(context.getFilesDir(), fileName);

        try {
            fos = new FileOutputStream(file);
            fos.write(jsonString.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static List<ModifyListPageItem> importFromJSON(Context context, String fileName) {
        FileReader reader = null;

        try {
            File file = new File(context.getFilesDir(), fileName);
            reader = new FileReader(file);

            Gson gson = new Gson();
            ListItems listItems = gson.fromJson(reader, ListItems.class);
            return listItems.getListItems();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    static class ListItems {
        List<ModifyListPageItem> listItems;

        public List<ModifyListPageItem> getListItems() {
            return listItems;
        }

        public void setListItems(List<ModifyListPageItem> listItems) {
            this.listItems = listItems;
        }
    }

}

package com.example.werankit3;

import android.widget.Spinner;

public class ModifyListPageItem {

    private String title;
    private String description;
    private String rank;
    private Spinner spinner;

    public ModifyListPageItem()
    {

    }

    public ModifyListPageItem(String title, String desc, String rank, Spinner spinner) {
        this.title = title;
        this.description = desc;
        this.rank = rank;
        this.spinner = spinner;

    }

    public void setTitle(String title) {this.title=title;}
    public void setDescription(String desc) {this.description=desc;}
    public void setRank(String rank) {this.rank=rank;}
    public void setSpinner(Spinner spinner) {this.spinner=spinner;}


    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public String getRank() {return rank;}
    public Spinner getSpinner() {return spinner;}
}

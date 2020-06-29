package com.example.werankit3;

import android.widget.Spinner;

public class ModifyListPageItem {

    private String title;
    private String description;
    private String rank;
    private int image;
    private int list_id;
    private boolean userCreated;

    public ModifyListPageItem()
    {

    }

    public ModifyListPageItem(String title, String desc, String rank, int img) {
        this.title = title;
        this.description = desc;
        this.rank = rank;
        this.image = img;
    }

    public void setTitle(String title) {this.title=title;}
    public void setDescription(String desc) {this.description=desc;}
    public void setRank(String rank) {this.rank=rank;}
    public void setImage(int imgRes) {this.image=imgRes;}
    public void setList_id(int list_id) {this.list_id=list_id;}
    public void setUserCreated(boolean userCreated) {this.userCreated=userCreated;}

    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public String getRank() {return rank;}
    public int getImage() {return image;}
    public int getList_id() {return list_id;}
    public boolean isUserCreated() {return userCreated;}
}

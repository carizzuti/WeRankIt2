package com.example.werankit3;

import android.media.Image;
import android.widget.ImageView;

public class HomePageItem {

    //private Image img;
    private String title;
    private String description;
    private boolean userCreated;
    private int list_id;

    public HomePageItem()
    {

    }

    public HomePageItem(String title, String desc, boolean userCreated, int list_id) {
        //this.img = img;
        this.title = title;
        this.description = desc;
        this.userCreated = userCreated;
        this.list_id = list_id;
    }

    //public void setImg(Image img) {this.img=img;}
    public void setTitle(String title) {this.title=title;}
    public void setDescription(String desc) {this.description=desc;}
    public void setUserCreated(boolean userCreated) {this.userCreated=userCreated;}
    public void setList_id(int list_id) {this.list_id=list_id;}

    //public Image getImg() {return img;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public boolean isUserCreated() {return userCreated;}
    public int getList_id() {return list_id;}
}

package com.example.werankit3;

import android.media.Image;
import android.widget.ImageView;

public class HomePageItem {

    //private Image img;
    private String title;
    private String description;
    private boolean userCreated;

    public HomePageItem()
    {

    }

    public HomePageItem(String title, String desc, boolean userCreated) {
        //this.img = img;
        this.title = title;
        this.description = desc;
        this.userCreated = false;
    }

    //public void setImg(Image img) {this.img=img;}
    public void setTitle(String title) {this.title=title;}
    public void setDescription(String desc) {this.description=desc;}
    public void setUserCreated(boolean userCreated) {this.userCreated = userCreated;}

    //public Image getImg() {return img;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public boolean isUserCreated() {return userCreated;}
}

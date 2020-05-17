package com.example.werankit3;

import android.media.Image;
import android.widget.ImageView;

public class HomePageItem {

    private Image img;
    private String title;
    private String description;
    private boolean userCreated;

    public HomePageItem()
    {

    }

    public HomePageItem(Image img, String title, String desc) {
        this.img = img;
        this.title = title;
        this.description = desc;
    }

    public void setImg(Image img) {this.img=img;}
    public void setTitle(String title) {this.title=title;}
    public void setDescription(String desc) {this.description=desc;}
    public void setUserCreated(boolean userCreated) {this.userCreated = userCreated;}

    public Image getImg() {return img;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public boolean isUserCreated() {return userCreated;}
}

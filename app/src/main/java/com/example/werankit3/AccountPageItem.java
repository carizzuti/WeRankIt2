package com.example.werankit3;

public class AccountPageItem {

    private String accountName;
    private String title;
    private String description;
    private boolean userCreated;

    public AccountPageItem()
    {

    }

    public AccountPageItem(String accountName, String title, String desc, boolean userCreated) {
        this.accountName = accountName;
        this.title = title;
        this.description = desc;
        this.userCreated = false;
    }

    public void setAccountName(String accountName) {this.accountName=accountName;}
    public void setTitle(String title) {this.title=title;}
    public void setDescription(String desc) {this.description=desc;}
    public void setUserCreated(boolean userCreated) {this.userCreated = userCreated;}

    public String getAccountName() {return accountName;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public boolean isUserCreated() {return userCreated;}
}

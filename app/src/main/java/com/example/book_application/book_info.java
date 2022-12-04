package com.example.book_application;

public class book_info {
    String AUTHOR;
    String DESCRIPTION;
    String IMG;
    String ID;
    String URL;
    String TITLE;
    boolean collapsed;


    public String getID() {
        return ID;
    }

    public book_info(String AUTHOR, String DESCRIPTION, String IMG, String ID, String URL,String TITLE) {
        this.AUTHOR = AUTHOR;
        this.DESCRIPTION = DESCRIPTION;
        this.IMG = IMG;
        collapsed = false;
        this.ID = ID;
        this.URL = URL;
        this.TITLE = TITLE;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }
    public void setcollapsed(boolean collapsed){
        this.collapsed = collapsed;
    }
}

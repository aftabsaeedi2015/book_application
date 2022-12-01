package com.example.book_application;

public class book_info {
    String book_author;
    String book_discription;
    String book_img_link;
    int  book_id;
    String book_url;
    boolean collapsed;

    public int getBook_id() {
        return book_id;
    }

    public book_info(String book_author, String book_discription, String book_img_link, int book_id,String book_url) {
        this.book_author = book_author;
        this.book_discription = book_discription;
        this.book_img_link = book_img_link;
        collapsed = false;
        this.book_id = book_id;
        this.book_url = book_url;
    }

    public String getBook_author() {
        return book_author;
    }

    public String getBook_discription() {
        return book_discription;
    }

    public void setBook_img_link(String book_img_link) {
        this.book_img_link = book_img_link;
    }
    public void setcollapsed(boolean collapsed){
        this.collapsed = collapsed;
    }
}

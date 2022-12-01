package com.example.book_application;

import java.util.ArrayList;

public class utils {
    private static utils instance ;
    private static ArrayList<book_info> all_books;
    private static ArrayList<book_info> favorite_books;
    private static ArrayList<book_info> finished_books;
    private utils(){
        if(all_books==null){
            all_books = new ArrayList<>();
        }
        if(favorite_books==null){
            favorite_books = new ArrayList<>();
        }
        if(finished_books==null){
            finished_books = new ArrayList<>();
        }
        initData();

    }

    public static utils getInstance() {
        if(instance==null){
            return instance = new utils();
        }
        else
        {
            return instance;
        }
    }

//here we are adding all the books to the all_books array
    private void initData() {
        book_info book = new book_info("aftab","this is an amazing book","https://edit.org/images/cat/book-covers-big-2019101610.jpg",1,"url");
        book_info book1 = new book_info("waheed","this is an amazing book","https://rukminim1.flixcart.com/image/612/612/jpfsnm80/book/8/9/0/process-implementation-through-5s-original-imafbnxzqyr6cggd.jpeg?q=70",2,"url");
        book_info book2 = new book_info("najeeb","this is an amazing book","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1329578342l/13490389.jpg",3,"url");
        book_info book3= new book_info("attaullah","this is an amazing book","https://images.theconversation.com/files/364159/original/file-20201019-17-rski0e.JPG?ixlib=rb-1.1.0&q=45&auto=format&w=1000&fit=clip",4,"url");
        book_info book4 = new book_info("saeedi","this is an amazing book","https://www.lilydalebooks.com.au/Catalog/Image/WebImage-20190806-14822-atar-text-guide-unpolished-gem.PNG?w=438&h=576",5,"url");
        book_info book5 = new book_info("john","this is an amazing book","https://n1.sdlcdn.com/imgs/d/o/t/Pung-Chow-the-Game-of-SDL622834302-1-fd93f.jpg",6,"url");
        all_books.add(book);
        all_books.add(book1);
        all_books.add(book2);
        all_books.add(book3);
        all_books.add(book4);
        all_books.add(book5);
    }

//    here starts the getters
public static ArrayList<book_info> getAll_books() {
    return all_books;
}


    public static ArrayList<book_info> getFavorite_books() {
        return favorite_books;
    }

    public static ArrayList<book_info> getFinished_books() {
        return finished_books;
    }




//    deleting a bookbyid when deletbtn is clicked
    public  void delet_by_id(int bookid){
        for(book_info b: favorite_books){
            if(b.getBook_id()==bookid){
                favorite_books.remove(b);
            }
        }
    }
    //    when user trys to create instance of this class if there was no instance previously then it returns an instance otherwise it returns the previous one

//    for adding a book
    public void addbook(book_info book){
        all_books.add(book);
    }

    public book_info getbookbyid(int book_id){
        for(book_info b: all_books){
            if(b.getBook_id()==book_id){
                return b;
            }
        }
        return null;

    }
    public boolean addtofavorite(book_info book){
        if (favorite_books.add(book)){;
        return true;
    }
        else
            return false;

}
    public boolean addtofinished(book_info book){
        if (finished_books.add(book)){;
            return true;
        }
        else
            return false;

    }
}

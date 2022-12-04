package com.example.book_application;

import org.json.JSONArray;
import org.json.JSONObject;

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
//        pass the books here
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
    public ArrayList<book_info> initData(String json) {
        all_books = new ArrayList<>();
        all_books = getBooksFromJsonResult(json);
        return all_books;
    }

    public static ArrayList<book_info> getBooksFromJsonResult(String json) {
        final String ID = "id";
        final String TITLE = "title";
        final String SUB_TITLE = "subTitle";
        final String AUTHORS = "authors";
        final String ITEMS = "items";
        final String VOLUME_INFO = "volumeInfo";
        try {
            JSONObject jsonBooks = new JSONObject(json);
            JSONArray booksArray = jsonBooks.getJSONArray(ITEMS);
            int numOfBooks = booksArray.length();
            System.out.println(numOfBooks);
            for (int i = 0; i < numOfBooks; i++) {
                JSONObject jsonBook = booksArray.getJSONObject(i);
                JSONObject jsonVolumeInfo = jsonBook.getJSONObject("volumeInfo");
                JSONObject jsonImages = jsonVolumeInfo.getJSONObject("imageLinks");
                JSONObject jsonBookUrl = jsonBook.getJSONObject("accessInfo");
                JSONObject pdf = jsonBookUrl.getJSONObject("pdf");
                book_info book = new book_info(
                        jsonVolumeInfo.getString(AUTHORS),
                        jsonVolumeInfo.getString("description"),
                       jsonImages.getString("thumbnail"),
                      jsonBook.getString("id"),
                        "url",
                        jsonVolumeInfo.getString("title"));
                all_books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Log.e("book1", String.valueOf(all_books.get(0)));
        return all_books;
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
    public  void delet_by_id(String bookid){
        for(book_info b: favorite_books){
            if(b.getID()==bookid){
                favorite_books.remove(b);
            }
        }
    }
    //    when user trys to create instance of this class if there was no instance previously then it returns an instance otherwise it returns the previous one

//    for adding a book
    public void addbook(book_info book){
        all_books.add(book);
    }

    public book_info getbookbyid(String book_id){
        for(book_info b: all_books){
            if(b.getID().equals(book_id)){
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

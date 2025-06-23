package com.example;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main
{
    public static void gsonJsonOps(List<Book> books) throws ParseException
    {
        Gson gson = new Gson();
        String json = gson.toJson(books);
        System.out.println("Books JSON:\n" + json);

        Type bookListType = new TypeToken<List<Book>>(){}.getType();
        List<Book> restoredBooks = gson.fromJson(json, bookListType);
        System.out.println("Restored Books:\n" + restoredBooks);
    }
    public static void main(String[] args) throws ParseException
    {
        System.out.println("Hello world!");

        List<Book> books = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        books.add(new Book(1, "Story", "Robert Mckee", 3, sdf.parse("2022-04-21")));
        books.add(new Book(2, "Sapiens", "Yuval Harari", 4, sdf.parse("2022-05-05")));
        books.add(new Book(3, "1974", "George Orwell", 21, sdf.parse("2022-11-21")));
        books.add(new Book(4, "Deus", "Yuval Harari", 7, sdf.parse("2022-05-05")));
        books.add(new Book(5, "The Three Body Problem", "Cixian Lu", 11, sdf.parse("2022-06-15")));
        gsonJsonOps(books);
    }
}

// mvn clean compile assembly:single 
// or 
// mvn package 
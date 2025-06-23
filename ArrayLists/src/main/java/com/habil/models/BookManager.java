package main.java.com.habil.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookManager
{
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book)
    {
        books.add(book);
    }

    public Book getBook(int index)
    {
        if (index >= 0 && index < books.size())
        {
            return books.get(index);
        }
        return null;
    }

    public void updateBook(int index, String newName)
    {
        if (index >= 0 && index < books.size())
        {
            Book book = books.get(index);
            book.setBookName(newName);
        }
    }

    public void deleteBookByIndex(int index)
    {
        if (index >= 0 && index < books.size())
        {
            books.remove(index);
        }
    }

    public void deleteBookById(int bkId)
    {
        books.removeIf(book -> book.getBookId() == bkId);
    }

    public void displayBooks()
    {
        books.forEach(System.out::println);
    }

    public void displaySortedByName(boolean ascending)
    {
        books.stream()
        .sorted(ascending ? Comparator.comparing(Book::getBookName) : Comparator.comparing(Book::getBookName).reversed())
        .forEach(System.out::println);
    }

    public void filterByAuthor(String bkAuthor)
    {
        books.stream()
        .filter(book -> book.getBookAuthor().equalsIgnoreCase(bkAuthor))
        .forEach(System.out::println);
    }
}

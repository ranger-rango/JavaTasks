package main.java.com.habil.models;

import java.util.Date;

public class Book
{

    private int bookId;
    private String bookName;
    private String bookAuthor;
    private int numberOfCopies;
    private Date datePublished;

    public Book(int bookId, String bookName, String bookAuthor, int numberOfCopies, Date datePublished)
    {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.numberOfCopies = numberOfCopies;
        this.datePublished = datePublished;
    }

    public int getBookId()
    {
        return bookId;
    }
    public String getBookName()
    {
        return bookName;
    }
    public String getBookAuthor()
    {
        return bookAuthor;
    }
    public int getNumberOfCopies()
    {
        return numberOfCopies;
    }
    public Date getDatePublished()
    {
        return datePublished;
    }

    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }
    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }
    public void setBookAuthor(String bookAuthor)
    {
        this.bookAuthor = bookAuthor;
    }
    public void setNumberOfCopies(int numberOfCopies)
    {
        this.numberOfCopies = numberOfCopies;
    }
    public void setDatePublished(Date datePublished)
    {
        this.datePublished = datePublished;
    }

    @Override
    public String toString()
    {
        return String.format("Book[%d, %s, %s, %d, %s]", bookId, bookName, bookAuthor, numberOfCopies, datePublished.toString());
    }

}

package main.java.com.habil.app;

import java.text.SimpleDateFormat;

import main.java.com.habil.models.Book;
import main.java.com.habil.models.BookManager;

public class BookApp
{
    public static void main(String[] args) throws Exception
    {
        BookManager manager = new BookManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        manager.addBook(new Book(1, "Story", "Robert Mckee", 3, sdf.parse("2022-04-21")));
        manager.addBook(new Book(2, "Sapiens", "Yuval Harari", 4, sdf.parse("2022-05-05")));
        manager.addBook(new Book(3, "1974", "George Orwell", 21, sdf.parse("2022-11-21")));
        manager.addBook(new Book(4, "Deus", "Yuval Harari", 7, sdf.parse("2022-05-05")));
        manager.addBook(new Book(5, "The Three Body Problem", "Cixian Lu", 11, sdf.parse("2022-06-15")));


        System.out.println("Displaying All Books:");
        manager.displayBooks();
        System.out.println();

        System.out.println("Get book by Index: ");
        System.out.println(manager.getBook(1));
        System.out.println();

        System.out.println("Update Book Name:");
        manager.updateBook(3, "Homo Deus");
        System.out.println();

        System.out.println("Deleting Book by Id:");
        manager.deleteBookById(1);
        System.out.println();

        System.out.println("Deleting Book by Index:");
        manager.deleteBookByIndex(1);
        System.out.println();

        System.out.println("Displaying Sorted Books");
        manager.displaySortedByName(true);
        System.out.println();

        System.out.println("Displaying Books Sorted by Author");
        manager.filterByAuthor("Yuval Harari");




    }
}

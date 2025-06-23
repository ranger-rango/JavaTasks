package main.java.com.habil.app;

import main.java.com.habil.models.IntegerHandler;
// import main.java.com.habil.models.IntegerHandler;
import main.java.com.habil.models.Item;
import main.java.com.habil.models.StringHandler;

public class GenericsApp
{
    public static <T> void printArrayContents(T[] array)
    {
        for (T item : array)
        {
            System.out.println(item);
        }
    }
    public static void main(String[] args)
    {
        System.out.println("Generic class Item<T> ...");
        Item<String> str = new Item<String>("The quick brown fox jumps over the lazy dog");
        System.out.println(str.getObject());
        System.out.println(str);

        Item<Double> pi = new Item<>();
        pi.setObject(22.000 / 7.000);
        System.out.println(pi.getObject());
        System.out.println();

        System.out.println("Generic Method printArratContents(T[] array) ...");
        Integer[] numsArray = {1, 2, 3, 4, 5};
        String[] strArray = {"uno", "dos", "tres", "quatro"};

        printArrayContents(numsArray);
        printArrayContents(strArray);
        System.out.println();

        System.out.println("Generic Interface DataHandler<T> and classes StringHandler/IntegerHandler ...");
        IntegerHandler num = new IntegerHandler();
        StringHandler revUpperStr = new StringHandler();
        System.out.println(num.processData(50));
        System.out.println(revUpperStr.processData("pallindrome"));

    }
}

package main.java.com.habil.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import main.java.com.habil.model.FileChecker;
import main.java.com.habil.model.FilePathParser;

public class SerialiseList
{
    public static void main(String[] args)
    {
        String filePath = FilePathParser.filePathParser(args, "--file");
        List <String> fruits = Arrays.asList("apple", "mango", "banana");

        if (FileChecker.fileChecker(filePath))
        {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath)))
            {
                oos.writeObject(fruits);
                System.out.println("Fruits List Serialised");
                System.out.println();
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        
    }

}


// maps are serialised in the same way ase lists
// for custom classes: public class Person implements Serializable 

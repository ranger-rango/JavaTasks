package main.java.com.habil.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import main.java.com.habil.model.FilePathParser;

public class DeserialiseList
{
    public static void main(String[] args)
    {
        String filePath = FilePathParser.filePathParser(args, "--file");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath)))
        {
            List <?> fruits = (List<?>) ois.readObject();
            System.out.println("Fruit Object Deserialised: ");
            System.out.println(fruits);
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

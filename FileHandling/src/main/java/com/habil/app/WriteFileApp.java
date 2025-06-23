package main.java.com.habil.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import main.java.com.habil.model.FileChecker;
import main.java.com.habil.model.FilePathParser;

public class WriteFileApp
{

    public static void bufferedWriter(String filePath)
    {
        System.out.println("Writing with BufferedWriter and FileWriter ");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath)))
        {
            bufferedWriter.write("Hello, ni hau, marhaba ?");
            bufferedWriter.newLine();
            bufferedWriter.write("nice to meet you");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Exiting ...");
        System.out.println();
    }

    public static void printWriter(String filePath)
    {
        System.out.println("Writing with PrintWriter ");

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, true)))
        {
            printWriter.println();
            printWriter.println("Written with PrintWriter");
            printWriter.println("Noice :)");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Exiting ...");
        System.out.println();
    }
    public static void main(String[] args)
    {
        String filePath = FilePathParser.filePathParser(args, "--file");

        if (FileChecker.fileChecker(filePath))
        {
            bufferedWriter(filePath);
            printWriter(filePath);
        }

    }
}

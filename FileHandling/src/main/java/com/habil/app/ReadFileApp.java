package main.java.com.habil.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import main.java.com.habil.model.FilePathParser;

public class ReadFileApp
{
    public static void bufferedReader(String filePath)
    {
        System.out.println("Reading with BufferedReader and FileReader");
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Exiting ...");
        System.out.println();
    }

    public static void scannerReader(String filePaath)
    {
        System.out.println("Reading with Scanner");
        try (Scanner scanner = new Scanner(new File(filePaath)))
        {
            while (scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
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
        bufferedReader(filePath);
        scannerReader(filePath);

    }
}
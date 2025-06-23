package main.java.com.habil.model;

import java.io.File;
import java.io.IOException;

public class FileChecker
{
    public static boolean fileChecker (String filePath)
    {
        if (filePath == null)
        {
            System.out.println("Error: Missing --file <filename> cmd argument");
            return false;
        }
        File file = new File(filePath);

        try
        {
            if (file.createNewFile())
            {
                System.out.println("File Created: " + file.getName());
            }
            else
            {
                System.out.println("File exists, Safe to proceed");
            }
            return true;

        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

    }
}

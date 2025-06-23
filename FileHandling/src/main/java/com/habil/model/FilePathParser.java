package main.java.com.habil.model;

public class FilePathParser
{
    public static String filePathParser(String[] args, String flag)
    {
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals(flag))
            {
                return args[i + 1];
            }
        }

        return null;
    }
}

package main.java.com.habil.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class PrivateMtdPropertiesScanner 
{
    public static void main(String[] args)
    {
        String privateMtdString = "^\\s*private\\s+(static\\s+)?[\\w<>\\[\\]]+\\s+(\\w+)\\s*\\([^)]*\\)\\s*\\{?";
        String privatePropertyString = "^\\s*private\\s+(static\\s+|final\\s+|static\\s+final\\s+|final\\s+static\\s+)?[\\w<>\\[\\]]+\\s+\\w+\\s*=?.*";
        Pattern privateMtdPattern = Pattern.compile(privateMtdString);
        Pattern privatePropertyPattern = Pattern.compile(privatePropertyString);

        String filePath = "/java/JavaTasks/Regexes1/ExampleFile.java";
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)))
        {
            while ((line = bufferedReader.readLine()) != null)
            {
                if (privateMtdPattern.matcher(line).find())
                {
                    System.out.println("Private Method: " + line.trim());
                }
                else if (privatePropertyPattern.matcher(line).find())
                {
                    System.out.println("Private Property: " + line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

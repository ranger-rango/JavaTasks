package main.java.com.habil.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLVarScanner
{
    public static void main(String[] args)
    {
        String htmlVarString = "\\w*\\s*(\\[\\w+\\])";
        Pattern htmlVarPattern = Pattern.compile(htmlVarString);
        Matcher htmlVarMatcher;

        String filePath = "/java/JavaTasks/Regexes1/workflow-approval.html";
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)))
        {
            while ((line = bufferedReader.readLine()) != null)
            {
                htmlVarMatcher = htmlVarPattern.matcher(line);
                if (htmlVarMatcher.find())
                {
                    System.out.println(line);
                    System.out.println("Variable: " + htmlVarMatcher.group(1));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

package main.java.com.habil.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParserApp
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String filePath = "/java/JavaTasks/Regexes2/example.log";
        String dateAccessType = null;
        boolean containsStringPassword;
        boolean isRepeatAction;
        String firstDate = null;
        String lastDate = null;
        String prevAction = null;
        String prevUser = null;

        String dateString = ".*(\\d{4}-\\d{2}-\\d{2}).*";
        Pattern datePattern = Pattern.compile(dateString);
        Matcher dateMatcher;
        while (true)
        {
            if (dateAccessType == null)
            {
                System.out.print("Date Access Type: \n1. BETWEEN-INCLUSIVE\n2.AND\n");
                dateAccessType = scanner.nextLine();
                if (!(dateAccessType.equals("1") || dateAccessType.equals("2")))
                {
                    dateAccessType = null;
                    continue;
                }

            }
            if (firstDate == null || lastDate == null)
            {
                System.out.print("First Date: ");
                firstDate = scanner.nextLine();
                dateMatcher = datePattern.matcher(firstDate);
                if (!(dateMatcher.find() && (dateMatcher.group(1).matches(firstDate))))
                {
                    firstDate = null;
                    continue;
                }
                System.out.print("Last Date: ");
                lastDate = scanner.nextLine();
                dateMatcher = datePattern.matcher(lastDate);
                if (!(dateMatcher.find() && (dateMatcher.group(1).matches(lastDate))))
                {
                    lastDate = null;
                    continue;
                }
            }

            break;
        }

        String passwordString = ".*(?=.*password).*";
        Pattern passwordPattern = Pattern.compile(passwordString);
        // String repeatedActions = "User:\\s*(\\w+),\\s*Action:\\s*(.+)\\s*\\n.*User:\\s*\\1,\\s*Action:\\s*\\2";
        // Pattern repeatedActionsPattern = Pattern.compile(repeatedActions);
        String logGroupsString = "\\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})\\] (\\w+) User: (\\w+), Action: (.+)";
        Pattern logGroupsStringPattern = Pattern.compile(logGroupsString);
        Matcher logGroupsStringMatcher;
        String timeStamp = null;
        String logLevel = null;
        String userName = null;
        String actionDescription = null;
        String headerFormat = "%-22s %-10s %-12s %-55s %-20s %-15s%n";
        String rowFormat = "%-22s %-10s %-12s %-55s %-20b %-15b%n";

        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)))
        {
            System.out.printf(headerFormat, "TimeStamp", "LogLevel", "UserName", "ActionDescription", "ContainsPassword", "IsRepeatAction");

            while ((line = bufferedReader.readLine()) != null)
            {
                dateMatcher = datePattern.matcher(line);
                if (dateMatcher.find())
                {
                    if (dateAccessType.equals("2") && !(dateMatcher.group(1).equals(firstDate) || dateMatcher.group(1).equals(lastDate)))
                    {
                        continue;
                    }
                }
                containsStringPassword = false;
                isRepeatAction = false;
                logGroupsStringMatcher = logGroupsStringPattern.matcher(line);

                if (passwordPattern.matcher(line).find())
                {
                    containsStringPassword = true;
                }

                // if (repeatedActionsPattern.matcher(line).find())
                // {
                //     isRepeatAction = true;
                // }

                if (logGroupsStringMatcher.find())
                {
                    timeStamp = logGroupsStringMatcher.group(1);
                    logLevel = logGroupsStringMatcher.group(2);
                    userName = logGroupsStringMatcher.group(3);
                    actionDescription = logGroupsStringMatcher.group(4);

                    if (userName.equals(prevUser) && actionDescription.equals(prevAction))
                    {
                        isRepeatAction = true;
                    }
                    prevUser = userName;
                    prevAction = actionDescription;
                }

                System.out.printf(rowFormat, timeStamp, logLevel, userName, actionDescription, containsStringPassword, isRepeatAction);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}

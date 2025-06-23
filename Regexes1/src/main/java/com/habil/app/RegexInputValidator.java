package main.java.com.habil.app;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexInputValidator
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String name = null;
        String email = null;
        String mobileNum = null;

        while (true)
        {
            try
            {
                if (name == null)
                {
                    Pattern namePattern = Pattern.compile("^[a-zA-Z\s]+$");
                    System.out.print("Name: ");
                    name = scanner.nextLine();
                    Matcher nameMatcher = namePattern.matcher(name);
                    System.out.println("Name Correct? " + nameMatcher.matches());
                    if (!nameMatcher.matches())
                    {
                        name = null;
                        continue;
                    }
                }

                if (email == null)
                {
                    Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
                    System.out.print("Email: ");
                    email = scanner.nextLine();
                    Matcher emailMatcher = emailPattern.matcher(email);
                    System.out.println("Email Correct? " + emailMatcher.matches());
                    if (!emailMatcher.matches())
                    {
                        email = null;
                        continue;
                    }
                }

                if(mobileNum == null)
                {
                    Pattern mobileNumPattern = Pattern.compile("^(?:\\+254|254|0)(7\\d{8}|1\\d{8})$");
                    System.out.print("Mobile Number: ");
                    mobileNum = scanner.nextLine();
                    Matcher mobileNumMatcher = mobileNumPattern.matcher(mobileNum);
                    System.out.println("Mobile Number Correct? " + mobileNumMatcher.matches());
                    if (!mobileNumMatcher.matches())
                    {
                        mobileNum = null;
                        continue;
                    }
                }

                break;
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
                continue;
            }
            
        }
        scanner.close();
    }
}

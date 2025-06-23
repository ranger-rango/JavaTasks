package main.java.com.habil.app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SquareWithErrChck
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            try
            {
                System.out.println("Enter an int value for num: ");
                int num = scanner.nextInt();
                System.out.println("number squared = " + (num * num));
                break;
            }
            catch (InputMismatchException e)
            {
                System.err.println("Input must be of type int: " + e.getMessage());
                scanner.next();
                continue;
            }
        }

        scanner.close();
    }
}

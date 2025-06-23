package com.habil.calculator;
import java.util.Scanner;
import com.habil.mathlib.MathOperations;
import java.util.InputMismatchException;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String operation = "";
		double numx;
		double numy;

		while (true) 
		{
			System.out.print("Operation; +, -, * OR / : ");
			operation = scanner.next();
			if (operation.equals("+") || operation.equals("-") || operation.equals("/") || operation.equals("*"))
				break;
			else
			{
				System.out.println("Invalid operation selected. Please Try Again.");
				continue;
			}
		}

		while (true)
		{
			try
			{
				System.out.print("Numx: ");
				numx = scanner.nextDouble();
				break;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Numx must be a number, Please Try Again");
				scanner.next();
				continue;
			}
		}

		while (true)
		{
			try
			{
				System.out.print("Numy: ");
				numy = scanner.nextDouble();
				break;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Numy must be a number, Please Try Again");
				scanner.next();
				continue;
			}
		}

		switch (operation)
		{
			case "+": System.out.println(MathOperations.add(numx, numy));
				  break;
			case "-": System.out.println(MathOperations.sub(numx, numy));
				  break;
			case "*": System.out.println(MathOperations.mult(numx, numy));
				  break;
			case "/":  System.out.println(MathOperations.div(numx, numy));
				   break;
		}

			
		scanner.close();
	}
}

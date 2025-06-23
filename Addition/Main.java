import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Num1: ");
		int num1 = scanner.nextInt();
		System.out.print("Num2: ");
		int num2 = scanner.nextInt();

		System.out.println("Result: " + (num1 + num2));

		scanner.close();
	}
}

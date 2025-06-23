package main.java.com.habil.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.com.habil.model.EmployeeDataException;
import main.java.com.habil.model.EmployeeInformation;

public class EmployeeSalary
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        EmployeeInformation employee = new EmployeeInformation();

        String empNum = null;
        String empName = null;
        Double netSal = null;

        while (true)
        {
            try
            {
                if (empNum == null)
                {
                    System.out.println("Employee Number: ");
                    empNum = scanner.nextLine();
                    employee.setEmployeeNumber(empNum);
                }

                System.out.println(empNum);

                if (empName == null)
                {
                    System.out.println("Employee Name: ");
                    empName = scanner.nextLine();
                    employee.setEmployeeName(empName);
                }

                if (netSal == null)
                {
                    System.out.println("Employee Net Salary: ");
                    String netSalString = scanner.nextLine();
                    netSal = Double.parseDouble(netSalString);
                    employee.setNetSalary(netSal);
                }
                break;
            }
            catch (EmployeeDataException e)
            {
                System.err.println(e.getMessage());
                if (e.getMessage().contains("EmployeeNumber")) empNum = null;
                if (e.getMessage().contains("EmployeeName")) empName = null;
                continue;
            }
            catch (IllegalArgumentException e)
            {
                System.err.println(e.getMessage());
                netSal = null;
                continue;
            }
            catch (InputMismatchException e)
            {
                System.err.println(e.getMessage());
                netSal = null;
                continue;
            }
        }

        scanner.close();

        System.out.println(employee);
    }
}

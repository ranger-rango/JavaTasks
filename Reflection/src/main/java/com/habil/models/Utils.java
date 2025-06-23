package main.java.com.habil.models;

public class Utils
{
    public static void displayEmployeeDetails(Employeee employee)
    {
        System.out.printf("%-10s %-20s %-30s %-15s%n", "Employee Id", "Employee Number", "Name", "Deparatment");
        System.out.printf("%-10d %-20s %-30s %-15s%n", employee.getEmployeeId(), employee.getEmployeeNumber(), employee.getName(), employee.getDepartment());
    }
}

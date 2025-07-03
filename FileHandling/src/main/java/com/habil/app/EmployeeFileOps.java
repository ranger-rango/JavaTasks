package main.java.com.habil.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.com.habil.model.EmployeeProperties;
import main.java.com.habil.model.FileChecker;
import main.java.com.habil.model.FilePathParser;

public class EmployeeFileOps
{
    public static void serialiseObjectFile(String filePath, List<EmployeeProperties> employees)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath)))
        {
            oos.writeObject(employees);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<EmployeeProperties> deserialiseObjectFile(String filePath)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath)))
        {
            List <EmployeeProperties> employeeList = (List <EmployeeProperties>) ois.readObject();
            return employeeList;

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args)
    {
        String filePath = FilePathParser.filePathParser(args, "--file");
        boolean fileExists = FileChecker.fileChecker(filePath);

        if (!fileExists)
        {
            System.err.println("File does not exist and could not be created");
            System.exit(1);
        }
        
        System.out.println("Create a new Employee? y/n");
        EmployeeProperties employee = null;
        Scanner scanner = new Scanner(System.in);

        if (scanner.nextLine().equalsIgnoreCase("y"))
        {
            System.out.print("Employee Number: ");
            String empNum = scanner.nextLine();
            System.out.print("Employee Name: ");
            String empName = scanner.nextLine();
            System.out.print("Net Salary: ");
            double netSalary = scanner.nextDouble();
            employee = new EmployeeProperties(empNum, empName, netSalary);
        }
        scanner.close();
        
        if ((new File(filePath).length() == 0) && (employee != null))
        {
            List<EmployeeProperties> employees = new ArrayList<>();
            employees.add(employee);
            System.out.println("Serialising new Employee");
            serialiseObjectFile(filePath, employees);

        }
        else if ((new File(filePath).length() > 0))
        {
            List<EmployeeProperties> employeesInFile = deserialiseObjectFile(filePath);
            System.out.println("Current Employee List : ");
            employeesInFile.forEach(emp -> System.out.println(emp.getEmployeeNumber() + ", " + emp.getEmployeeName() + ", " + emp.getNetSalary()));
            // System.out.println(employeesInFile);
            if (employee != null)
            {
                System.out.println("Adding new Employee to Serialised List ...");
                employeesInFile.add(employee);
                serialiseObjectFile(filePath, employeesInFile);
            }
            
        }
        else
        {
            System.out.println("File is empty and/or no new employee was added");
        }

        

    }
}

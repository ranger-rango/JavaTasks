package main.java.com.habil.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.habil.model.EmployeeDetails;
import main.java.com.habil.model.EmployeeManager;

public class Maps1
{
    public static void main(String[] args)
    {
        Map<String, List<EmployeeDetails>> depMap = new HashMap<>();
        EmployeeManager employeeManager = new EmployeeManager(depMap);

        EmployeeDetails employee1 = new EmployeeDetails(1, "101", "Ayrton Senna", "Racing");
        EmployeeDetails employee2 = new EmployeeDetails(2, "102", "Tama Nisa", "HR");
        EmployeeDetails employee3 = new EmployeeDetails(3, "103", "Jared Lil", "Support");
        EmployeeDetails employee4 = new EmployeeDetails(4, "104", "Merk Smith", "Racing");
        EmployeeDetails employee5 = new EmployeeDetails(5, "105", "Jordan Coleman", "Engineering");
        EmployeeDetails employee6 = new EmployeeDetails(6, "106", "Val Valentine", "Comms");

        employeeManager.addEmployee(employee1);
        employeeManager.addEmployee(employee2);
        employeeManager.addEmployee(employee3);
        employeeManager.addEmployee(employee4);
        employeeManager.addEmployee(employee5);
        employeeManager.addEmployee(employee6);

        employeeManager.displayEmployeesByDepartment();

        employeeManager.getEmployee(1);
        employeeManager.getEmployee("102");
        employeeManager.getEmployee("Merk smith");

        EmployeeDetails updatedEmployee4 = new EmployeeDetails(4, "104", "Merk Smith", "R&D");
        EmployeeDetails updatedEmployee6 = new EmployeeDetails(6, "106", "Valerie Valentine", "Comms");
        employeeManager.updateEmployee(updatedEmployee4);
        employeeManager.updateEmployee(updatedEmployee6);

        employeeManager.deleteEmployee(3);

        System.out.println();
        System.out.println();
        employeeManager.displayEmployeesByDepartment();



    }
}

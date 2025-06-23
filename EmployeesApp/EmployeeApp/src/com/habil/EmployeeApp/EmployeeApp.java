package com.habil.EmployeeApp;

import com.habil.FullTimeEmployee.FullTimeEmployee;
import com.habil.PartTimeEmployee.PartTimeEmployee;

public class EmployeeApp {
    public static void main(String[] args) {
        FullTimeEmployee ft = new FullTimeEmployee(1, "Naima", 100_000, 20_000);
        PartTimeEmployee pt = new PartTimeEmployee(2, "Salahudin", 500, 50);

        ft.displayEmployeeDetails();
        ft.calculateSalary();

        System.out.println();

        pt.displayEmployeeDetails();
        pt.calculateSalary();
    }
}

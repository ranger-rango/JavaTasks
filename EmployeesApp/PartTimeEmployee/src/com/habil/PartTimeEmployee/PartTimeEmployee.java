package com.habil.PartTimeEmployee;

import com.habil.Employees.Employees;

public class PartTimeEmployee extends Employees
{
    float hourlyRate;
    int hoursWorked;

    public PartTimeEmployee(int empNum, String empName, float hourlyRate, int hoursWorked)
    {
        this.empNum = empNum;
        this.empName = empName;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    @Override
    public void calculateSalary()
    {
        netSalary = hourlyRate * hoursWorked;
        System.out.println("Part-Time Employee Salary: " + netSalary);
    }
}

package com.habil.FullTimeEmployee;

import com.habil.Employees.Employees;

public class FullTimeEmployee extends Employees
{
    float monthlySalary;
    float monthlyAllowance;

    public FullTimeEmployee(int empNum, String empName, float monthlySalary, float monthlyAllowance)
    {
        this.empNum = empNum;
        this.empName = empName;
        this.monthlySalary = monthlySalary;
        this.monthlyAllowance = monthlyAllowance;
    }

    @Override
    public void calculateSalary()
    {
        netSalary = monthlySalary + monthlyAllowance;
        System.out.println("Full-Time Employee Salary: " + netSalary);
    }
}

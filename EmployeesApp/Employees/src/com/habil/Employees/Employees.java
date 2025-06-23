package com.habil.Employees;

public abstract class Employees
{
    protected int empNum;
    protected String empName;
    protected float netSalary;

    public abstract void calculateSalary();

    public void displayEmployeeDetails()
    {
        System.out.println("Employee Number: " + empNum);
        System.out.println("Employee Name: " + empName);
    }
}
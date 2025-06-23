package main.java.com.habil.model;

import java.io.Serializable;

public class EmployeeProperties implements Serializable
{
    String employeeNumber;
    String employeeName;
    double netSalary;
    public EmployeeProperties(String employeeNumber, String employeeName, double netSalary) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.netSalary = netSalary;
    }

    public String getEmployeeNumber()
    {
        return employeeNumber;
    }
    public String getEmployeeName()
    {
        return employeeName;
    }
    public double getNetSalary()
    {
        return netSalary;
    }

    public void setEmployeeNumber(String employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }
    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }
    public void setNetSalary(double netSalary)
    {
        this.netSalary = netSalary;
    }


}

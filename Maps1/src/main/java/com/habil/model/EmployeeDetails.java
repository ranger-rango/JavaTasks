package main.java.com.habil.model;

public class EmployeeDetails
{
    private int employeeId;
    private String employeeNumber;
    private String name;
    private String department;
    public EmployeeDetails(int employeeId, String employeeNumber, String name, String department) {
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.department = department;
    }
    public int getEmployeeId() 
    {
        return employeeId;
    }
    public String getEmployeeNumber()
    {
        return employeeNumber;
    }
    public String getName()
    {
        return name;
    }
    public String getDepartment()
    {
        return department;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }
    public void setEmployeeNumber(String employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }

    
}

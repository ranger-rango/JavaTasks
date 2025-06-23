package main.java.com.habil.models;

public class Employeee
{
    private int employeeId;
    @Description(info = "This is the identifier assigned to the employee by the organisation.")
    private String employeeNumber;
    private String name;
    @Description(info = "This indicates the department that the employee works in.")
    private String department;

    public Employeee(int employeeId, String employeeNumber)
    {
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
    }

    public Employeee(int employeeId, String employeeNumber, String name, String department)
    {
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

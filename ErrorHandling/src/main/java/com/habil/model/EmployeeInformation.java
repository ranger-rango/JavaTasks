package main.java.com.habil.model;

public class EmployeeInformation
{
    private String employeeNumber;
    private String employeeName;
    private double netSalary;

    public EmployeeInformation()//String employeeNumber, String employeeName, double netSalary)
    {
        // this.employeeNumber = employeeNumber;
        // this.employeeName = employeeName;
        // this.netSalary = netSalary;
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

    public void setEmployeeNumber(String employeeNumber) throws EmployeeDataException
    {
        if (employeeNumber.length() > 5)
        {
            throw new EmployeeDataException("EmployeeDataException : EmployeeNumber cannot have more than 5 characters: " + employeeNumber);
        }
        else
        {
            this.employeeNumber = employeeNumber;
        }
    }
    public void setEmployeeName(String employeeName) throws EmployeeDataException
    {
        if (employeeName.length() < 3)
        {
            throw new EmployeeDataException("EmployeeDataException : EmployeeName cannot have less than 3 characters: " + employeeName);
        }
        else
        {
            this.employeeName = employeeName;
        }
    }
    public void setNetSalary(double netSalary)
    {
        if (netSalary < 0)
        {
            throw new IllegalArgumentException("NetSalary must be a Positive value: " + netSalary);
        }
        else
        {
            this.netSalary = netSalary;
        }
    }

    @Override
    public String toString() {
        return "EmployeeInformation [employeeNumber=" + employeeNumber + ", employeeName=" + employeeName
                + ", netSalary=" + netSalary + "]";
    }

    

    
}

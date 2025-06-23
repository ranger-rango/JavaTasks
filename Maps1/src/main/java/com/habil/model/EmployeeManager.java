package main.java.com.habil.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EmployeeManager
{
    Map <String, List<EmployeeDetails>> depEmployee;

    public EmployeeManager(Map<String, List<EmployeeDetails>> depEmployee)
    {
        this.depEmployee = depEmployee;
    }
    
    public void addEmployee(EmployeeDetails employee)
    {
        Objects.requireNonNull(employee, "Employee cannot be null");
        String department = employee.getDepartment();
        // if (depEmployee.containsKey(department))
        // {
        //     depEmployee.get(department).add(employee);
        // }
        // else
        // {
        //     List<EmployeeDetails> newList = new ArrayList<>();
        //     newList.add(employee);
        //     depEmployee.put(department, newList);
        // }
        depEmployee.computeIfAbsent(department, k -> new ArrayList<>()).add(employee);

    }

    public Optional<EmployeeDetails> getEmployee(Object searchValue)
    {
        return depEmployee.values().stream()
        .flatMap(List::stream)
        .filter(e -> 
        (searchValue instanceof Integer && e.getEmployeeId() == (Integer) searchValue) ||
        (searchValue instanceof String && (
            e.getEmployeeNumber().equalsIgnoreCase((String) searchValue) || 
            e.getName().equalsIgnoreCase((String) searchValue)))
        )
        .findFirst();
    }

    public boolean updateEmployee(EmployeeDetails updatedEmployee)
    {
        Objects.requireNonNull(updatedEmployee, "Updated Employee cannot be null");

        return depEmployee.entrySet().stream()
        .filter(entry -> entry.getValue().stream().anyMatch(e -> e.getEmployeeId() == updatedEmployee.getEmployeeId()))
        .findFirst()
        .map(entry -> 
        {
            List<EmployeeDetails> employees = entry.getValue();

            employees.removeIf(e -> e.getEmployeeId() == updatedEmployee.getEmployeeId());
            depEmployee.computeIfAbsent(updatedEmployee.getDepartment(), k -> new ArrayList<>())
            .add(updatedEmployee);

            return true;
        }
        )
        .orElse(false);
    }

    public boolean deleteEmployee(int empId)
    {
        return depEmployee.entrySet().stream()
        .filter(entry -> entry.getValue().stream().anyMatch(e -> e.getEmployeeId() == empId))
        .findFirst()
        .map(entry -> 
        {
            List<EmployeeDetails> employees = entry.getValue();
            String department = entry.getKey();

            employees.removeIf(e -> e.getEmployeeId() == empId);
            if (employees.isEmpty())
            {
                depEmployee.remove(department);
            }

            return true;
        }
        )
        .orElse(false);
    }

    public void displayEmployeesByDepartment()
    {
        depEmployee.forEach((department, employees) -> 
        {
            System.out.println("Department: " + department);
            employees.forEach(employee -> 
            {
                System.out.println("empID: " + employee.getEmployeeId() + ", empNum: " + employee.getEmployeeNumber() + ", Name: " + employee.getName());
            }
            );
        }
        );
    }

    public void displayEmployeesByIdOrder()
    {
        depEmployee.values().stream()
        .flatMap(List::stream)
        .sorted(Comparator.comparing(EmployeeDetails::getEmployeeId))
        .forEach(employee -> 
        System.out.println("ID: " + employee.getEmployeeId() + "Number: " + employee.getEmployeeNumber() + "Name: " + employee.getName() + "Department: " + employee.getDepartment())
        );
    }

    public void displayEmployeesByDepartmentFilter(String depName)
    {
        depEmployee.values().stream()
        .flatMap(List::stream)
        .filter(employee -> employee.getDepartment().equalsIgnoreCase(depName))
        .forEach(employee -> 
        System.out.println("ID: " + employee.getEmployeeId() + "Number: " + employee.getEmployeeNumber() + "Name: " + employee.getName() + "Department: " + employee.getDepartment())
        );
    }

    public void employeeCount()
    {
        int total = depEmployee.entrySet().stream()
        .map(entry ->
        {
            String department = entry.getKey();
            int count = entry.getValue().size();
            System.out.println("Department: " + department + " - " + count + "employee(s)");
            return count;
        })
        .reduce(0, Integer::sum);

        System.out.println("Total Employees: " + total);
    }
}
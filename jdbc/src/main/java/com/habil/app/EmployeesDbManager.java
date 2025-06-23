package com.habil.app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

import com.habil.models.DbSelectQuery;
import com.habil.models.DbUpdateQuery;
import com.habil.models.HikariConnection;

public class EmployeesDbManager
{

    public static void insert(Connection connection) throws Exception
    {
        String sql = """
                INSERT INTO employees (department_id, status_id, job_title_id, job_role_id, employee_email, password_hash, surname, first_name, middle_name, dob, created_At, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        List<Object> params = List.of(1, 2, 4, 4, "shiming@fromjava.java", "hash042", "Ming", "Shi", "B", Date.valueOf("1994-05-03"), LocalDateTime.now(), LocalDateTime.now());

        DbUpdateQuery.updateOperation(connection, sql, params);
        
    }
    public static void update(Connection connection) throws Exception
    {
        String sql = """
                UPDATE employees
                SET surname = ?
                WHERE employee_id = ?;
                """;
        List<Object> params = List.of("DuPoint", 2);
        DbUpdateQuery.updateOperation(connection, sql, params);
    }

    public static void delete(Connection connection) throws Exception
    {
        String sql = """
                DELETE
                FROM company
                WHERE company_id = ?;
                """;

        List<Object> params = List.of(5);
        DbUpdateQuery.updateOperation(connection, sql, params);
    }

    public static void displayTable(Connection connection) throws Exception
    {
        String sql = """
                SELECT employee_email, surname, first_name
                FROM employees;
                """;

        List<Object> params = List.of();

        ResultSet resultSet = DbSelectQuery.readOperation(connection, sql, params);
        while (resultSet.next())
        {
            String empEmail = resultSet.getString("employee_email");
            String surname = resultSet.getString("surname");
            String firstName = resultSet.getString("first_name");

            System.out.println(empEmail + ", " + surname + ", " + firstName);
        }

    }
    public static void main(String[] args) throws Exception
    {
        Connection connection = HikariConnection.getConnection();
        // insert(connection);
        // update(connection);
        // delete(connection);

        displayTable(connection);

        connection.close();
        HikariConnection.shutdown();

        // create a query constructor for each situation 
    }
}

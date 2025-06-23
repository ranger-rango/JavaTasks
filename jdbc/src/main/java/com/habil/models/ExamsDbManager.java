package com.habil.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ExamsDbManager
{
    public static void insert(Connection connection) throws Exception
    {
        String sql = """
                INSERT INTO employees (class_code, surname, first_name, middle_name, class, gender, dob, created_At, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        List<Object> params = List.of(3, "Stein", "Hailey", "J", 4, "F", Date.valueOf("1994-05-03"), LocalDateTime.now(), LocalDateTime.now());

        DbUpdateQuery.updateOperation(connection, sql, params);
        
    }
    public static void update(Connection connection) throws Exception
    {
        String sql = """
                UPDATE teachers
                SET surname = ?
                WHERE teacher_id = ?;
                """;
        List<Object> params = List.of("Jones", 2);
        DbUpdateQuery.updateOperation(connection, sql, params);
    }

    public static void delete(Connection connection) throws Exception
    {
        String sql = """
                DELETE
                FROM pupils
                WHERE registration_num = ?;
                """;

        List<Object> params = List.of(2);
        DbUpdateQuery.updateOperation(connection, sql, params);
    }

    public static void callStoredFunctions(Connection connection) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Function Name: ");
        String functionName = scanner.nextLine();

        List<Object> params = List.of("Engineering");
        String sql = "SELECT * FROM " + functionName + "(?);";
        ResultSet results = CallPsqlFunctions.callPsqlFunctions(connection, sql, params);
        ResultSetMetaData metaData = results.getMetaData();
        int colCount = metaData.getColumnCount();

        scanner.close();

        while (results.next())
        {
            for (int i = 1; i <= colCount; i++)
            {
                String colName = metaData.getColumnLabel(i);
                Object value = results.getObject(i);
                System.out.println(colName + " = " + value);
            }

            System.out.println("_________________________________");
        }
    }

    public static void displayTable(Connection connection) throws Exception
    {
        String sql = """
                SELECT employee_email, surname, first_name
                FROM employees;
                """;

        List<Object> params = List.of();

        ResultSet resultSet = DbSelectQuery.readOperation(connection, sql, params);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int colCount = metaData.getColumnCount();

        while (resultSet.next())
        {
            for (int i = 1; i <= colCount; i++)
            {
                String colName = metaData.getColumnLabel(i);
                Object value = resultSet.getObject(i);

                System.out.println(colName + " = " + value);
            }
            System.out.println("_________________________________");
        }

    }

}

package com.habil.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class LibraryDbManager
{
    public static void insert(Connection connection) throws Exception
    {
        String sql = """
                INSERT INTO employees (author_id, sub_category_id, book_type_id, isbn, book_name, book_description, created_At, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """;
        List<Object> params = List.of(1, 1, 1, "9780747532312", "Harry Potter and the Deathly Hallows", "A book about Harry Potter", LocalDateTime.now(), LocalDateTime.now());

        DbUpdateQuery.updateOperation(connection, sql, params);
        
    }
    public static void update(Connection connection) throws Exception
    {
        String sql = """
                UPDATE librarians
                SET surname = ?
                WHERE librarian_id = ?;
                """;
        List<Object> params = List.of("DuLac", 2);
        DbUpdateQuery.updateOperation(connection, sql, params);
    }

    public static void delete(Connection connection) throws Exception
    {
        String sql = """
                DELETE
                FROM libraries
                WHERE library_id = ?;
                """;

        List<Object> params = List.of(3);
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

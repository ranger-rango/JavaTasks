package com.habil.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.habil.models.AnyDbManager;
import com.habil.models.HikariConnection;
import com.habil.models.ResultSetProcessor;
import com.habil.models.WriteDbProperties;

public class JdbcAppV2
{
    public static void main(String[] args) throws Exception
    {
        Connection connection;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Database Options,\n1: employees db\n2: library db\n3: exams db ");
        String dbOption = scanner.nextLine();
        int dbOpt = Integer.parseInt(dbOption);

        System.out.print("Database Password: ");
        String password = scanner.nextLine().strip();

        System.out.print("SQL Query (Parametarised)\n>");
        String sql = scanner.nextLine();

        System.out.print("SQL parameters (CSV): ");
        String params = scanner.nextLine();
        List<Object> sqlParams;
        String[] splitParams = params.split(",\\s*");
        if (splitParams.length == 1 && splitParams[0].isBlank()) 
        {
            sqlParams = List.of();
        }
        else
        {
            sqlParams = new ArrayList<>(Arrays.asList(splitParams));
        }
        
        scanner.close();

        switch (dbOpt)
        {
            case 1:
                WriteDbProperties.writeDbProperties("employee_salaries", password);
                connection = HikariConnection.getConnection();
                ResultSet employeeResults = AnyDbManager.dbOperation(connection, sql, sqlParams);
                ResultSetProcessor.processResults(employeeResults);
                connection.close();
                HikariConnection.shutdown();
                break;
            case 2:
                WriteDbProperties.writeDbProperties("library_management", password);
                connection = HikariConnection.getConnection();
                ResultSet libResults = AnyDbManager.dbOperation(connection, sql, sqlParams);
                ResultSetProcessor.processResults(libResults);
                connection.close();
                HikariConnection.shutdown();
                break;
            case 3:
                WriteDbProperties.writeDbProperties("online_exams", password);
                connection = HikariConnection.getConnection();
                ResultSet examResults = AnyDbManager.dbOperation(connection, sql, sqlParams);
                ResultSetProcessor.processResults(examResults);
                connection.close();
                HikariConnection.shutdown();
                break;
        
            default:
                break;
        }

    }
}

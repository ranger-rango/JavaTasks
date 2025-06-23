package com.habil.app;

import java.sql.Connection;
import java.util.Scanner;

import com.habil.models.EmployeesDbManager;
import com.habil.models.ExamsDbManager;
import com.habil.models.HikariConnection;
import com.habil.models.LibraryDbManager;

public class JdbcApp
{
    public static void main(String[] args) throws Exception
    {
        Connection connection = HikariConnection.getConnection();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Database Options,\n1: employees db\n2: library db\n3: exams db ");
        String dbOption = scanner.nextLine();
        int dbOpt = Integer.parseInt(dbOption);
        scanner.close();

        switch (dbOpt)
        {
            case 1:
                EmployeesDbManager.displayTable(connection);
                break;
            case 2:
                LibraryDbManager.displayTable(connection);
                break;
            case 3:
                ExamsDbManager.displayTable(connection);
                break;
        
            default:
                break;
        }

        connection.close();
        HikariConnection.shutdown();
    }
}

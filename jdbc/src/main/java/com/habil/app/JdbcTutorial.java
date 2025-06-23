package com.habil.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.habil.models.HikariConnection;

public class JdbcTutorial
{
    public static void main(String[] args) throws Exception
    {
        String sql = "SELECT * FROM employees";
        Connection connection = HikariConnection.getConnection();
        try
        {
            connection.setAutoCommit(false);

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();
            connection.commit();

            while (results.next())
            {
                String empEmail = results.getString("employee_email");
                String surname = results.getString("surname");
                String firstName = results.getString("first_name");

                System.out.println(empEmail + ", " + surname + ", " + firstName);
            }
            
        }
        catch (SQLException e)
        {
            connection.rollback();
            e.printStackTrace();
        }
        finally
        {
            connection.close();
            HikariConnection.shutdown();
        }
    }
}



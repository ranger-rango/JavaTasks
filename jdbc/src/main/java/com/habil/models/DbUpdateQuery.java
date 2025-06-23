package com.habil.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DbUpdateQuery
{
    public static void updateOperation(Connection connection, String sql, List<Object> args) throws Exception
    {
        // Connection connection = HikariConnection.getConnection();
        try
        {
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            for (int i = 0; i < args.size(); i++)
            {
                pstmt.setObject(i + 1, args.get(i));
            }
            int rowsAffected = pstmt.executeUpdate();
            connection.commit();
            System.out.println(rowsAffected + "row(s) affected");
        }
        catch (SQLException e)
        {
            connection.rollback();
            e.printStackTrace();
        }
        // finally
        // {
        //     connection.close();
        //     HikariConnection.shutdown();
        // }
    }
}

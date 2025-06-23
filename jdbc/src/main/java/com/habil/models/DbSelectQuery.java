package com.habil.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DbSelectQuery
{
    public static ResultSet readOperation(Connection connection, String sql, List<Object> args) throws Exception
    {
        // Connection connection  = HikariConnection.getConnection();
        ResultSet resultSet = null;
        try
        {
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            for (int i = 0; i < args.size(); i++)
            {
                pstmt.setObject(i + 1, args.get(i));
            }
            resultSet = pstmt.executeQuery();
            connection.commit();
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
        return resultSet;
    }
}

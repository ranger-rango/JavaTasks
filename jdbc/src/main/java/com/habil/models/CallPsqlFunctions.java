package com.habil.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CallPsqlFunctions
{
    public static ResultSet callPsqlFunctions(Connection connection, String sql, List<Object> params) throws Exception
    {
        ResultSet results = null;
        try
        {
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++)
            {
                pstmt.setObject(i + 1, params.get(i));
            }
            results = pstmt.executeQuery();
            connection.commit();
        }
        catch (SQLException e)
        {
            connection.rollback();
            e.printStackTrace();
        }
        return results;
    }
}

package com.habil.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetProcessor
{
    public static void processResults(ResultSet results) throws SQLException
    {
        ResultSetMetaData metaData = results.getMetaData();
        int cols = metaData.getColumnCount();

        while (results.next())
        {
            for (int i = 1; i <= cols; i++)
            {
                System.out.println(metaData.getColumnLabel(i) + ": " + results.getObject(i));
            }
            System.out.println("_____________________________________________________________________");     
        }

    }
}

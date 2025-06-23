package com.habil.app;

import java.sql.Connection;

import com.habil.models.HikariConnection;

public class CallPsqlFunctions
{
    public static void main(String[] args) throws Exception
    {
        Connection connection = HikariConnection.getConnection();

        connection.close();
        HikariConnection.shutdown();
    }
}

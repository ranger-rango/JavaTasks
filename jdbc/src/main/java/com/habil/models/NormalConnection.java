package com.habil.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class NormalConnection
{
    private static final String URL = "jdbc:postgresql://172.20.106.75:5432/employee_salaries";
    private static final String USER = "postgres";
    private static final String PASSWORD = "72bf3c074196479289e608d2517f1a32";

    public static Connection getConnection() throws Exception
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

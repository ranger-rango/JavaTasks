package com.habil.models;

import java.io.FileInputStream;
// import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariConnection
{
    private static HikariDataSource dataSource;

    // static 
    // {
    //     try (InputStream input = HikariConnection.class.getClassLoader().getResourceAsStream("db.properties"))
    //     {
    //         Properties props = new Properties();
    //         props.load(input);
    //         HikariConfig config = new HikariConfig(props);
    //         dataSource = new HikariDataSource(config);
    //     }
    //     catch (Exception e)
    //     {
    //         throw new RuntimeException("Failed to initiate HikariCP - ", e);
    //     }
    // }

    // public static Connection getConnection() throws Exception
    // {
    //     return dataSource.getConnection();
    // }

    public static Connection getConnection() throws Exception
    {
        if (dataSource != null && !dataSource.isClosed())
        {
            dataSource.close();
        }

        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/db.properties"))
        {
            props.load(input);
        }

        HikariConfig config = new HikariConfig(props);
        dataSource = new HikariDataSource(config);

        return dataSource.getConnection();
    }

    public static void shutdown()
    {
        if (dataSource != null && !dataSource.isClosed())
        {
            dataSource.close();
        }
    }
}

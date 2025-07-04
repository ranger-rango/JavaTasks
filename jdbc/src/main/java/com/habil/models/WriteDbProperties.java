package com.habil.models;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteDbProperties
{
    public static void writeDbProperties(String dbName, String password)
    {
        String dbProperties = """
                # PostgreSQL DataSource settings
                dataSourceClassName=org.postgresql.ds.PGSimpleDataSource
                dataSource.user=postgres
                dataSource.password="""+ password + """
                \ndataSource.databaseName=""" + dbName +
                        """
                \ndataSource.serverName=172.20.106.75
                dataSource.portNumber=5432

                # HikariCP optional tuning
                # maximumPoolSize=10
                # minimumIdle=2
                # connectionTimeout=30000
                # idleTimeout=600000
                # maxLifetime=1800000
                # poolName=PostgresHikariPool

                # windows psql conf file: C:\\Program Files\\PostgreSQL\\17\\data
                """;
        
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("/java/JavaTasks/jdbc/src/main/resources/db.properties")))
                {
                    writer.write(dbProperties);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
    }
}

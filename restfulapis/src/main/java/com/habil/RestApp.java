package com.habil;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.habil.models.HikariConnection;

@SpringBootApplication
public class RestApp
{

    @Bean
    public Connection myConnection() throws Exception
    {
        return HikariConnection.getConnection(); // NEVER USE IN A WEB APP, BAD FOR CONCURRENCY
    }
    public static void main(String[] args)
    {
        SpringApplication.run(RestApp.class, args);
    }
}
// mvn clean install:install-file -Dfile=/java/JavaTasks/jdbc/target/jdbc-1.0-SNAPSHOT-fat.jar -DgroupId=com.habil -DartifactId=jdbc-lib -Dversion=1.0 -Dpackaging=jar
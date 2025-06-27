package com.habil.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.habil.UndertowEmployee;
import com.habil.models.DbSelectQuery;
import com.habil.models.DbUpdateQuery;

public class PostgresDbUndertowController
{
    public static List<Map<String, Object>> getEmployees(Connection connection) throws Exception
    {
        String sql = """
                SELECT employee_email, surname, first_name
                FROM employees;
                """;
        List<Object> params = List.of();
        ResultSet resultSet = DbSelectQuery.readOperation(connection, sql, params);
        return resultSetToList(resultSet);

    }
    
    public static List<Map<String, Object>> getEmployeeByEmail(Connection connection, String email) throws Exception
    {
        String sql = """
                SELECT employee_email, surname, first_name
                FROM employees
                WHERE employee_email = ?
                """;
        List<Object> params = List.of(email);
        ResultSet resultSet = DbSelectQuery.readOperation(connection, sql, params);
        return resultSetToList(resultSet);

    }

    public static void createEmployee(Connection connection, UndertowEmployee req) throws Exception
    {
        String sql = """
                INSERT INTO employees (department_id, status_id, job_title_id, job_role_id, employee_email, password_hash, surname, first_name, middle_name, dob, created_At, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        LocalDate dob = LocalDate.parse(req.dob);
        List<Object> params = List.of(
            req.departmentId,
            req.statusId,
            req.jobTitleId,
            req.jobRoleId,
            req.employeeEmail,
            req.passwordHash,
            req.surname,
            req.firstName,
            req.middleName,
            dob,
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        DbUpdateQuery.updateOperation(connection, sql, params);
    }

    public static List<Map<String, Object>> resultSetToList(ResultSet resultSet) throws SQLException
    {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int colCount = metaData.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();
        
        while (resultSet.next())
        {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= colCount; i++)
            {
                row.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }
            list.add(row);
        }

        return list;
    }
}

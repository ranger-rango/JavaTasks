package com.habil.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habil.CreateEmployeeRequest;
import com.habil.models.DbSelectQuery;
import com.habil.models.DbUpdateQuery;


@RestController
@RequestMapping("/api")
public class PostgresDbController
{
    private final Connection connection;

    public PostgresDbController(Connection connection)
    {
        this.connection = connection;
    }

    @GetMapping("/employees")
    public List<Map<String, Object>> getEmployees() throws Exception
    {
        String sql = """
                SELECT employee_email, surname, first_name
                FROM employees;
                """;
        List<Object> params = List.of();
        ResultSet resultSet = DbSelectQuery.readOperation(connection, sql, params);
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

    @GetMapping("/employees/{email}")
    public List<Map<String, Object>> getEmployeeByEmail(@PathVariable String email) throws Exception
    {
        String sql = """
                SELECT employee_email, surname, first_name
                FROM employees
                WHERE employee_email = ?
                """;
        List<Object> params = List.of(email);
        ResultSet resultSet = DbSelectQuery.readOperation(connection, sql, params);
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

    @PostMapping("/employees")
    public void createEmployee(@RequestBody CreateEmployeeRequest req) throws Exception
    {
        String sql = """
                INSERT INTO employees (department_id, status_id, job_title_id, job_role_id, employee_email, password_hash, surname, first_name, middle_name, dob, created_At, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
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
            req.dob,
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        DbUpdateQuery.updateOperation(connection, sql, params);

    }

    @GetMapping("/library")
    public List<Map<String, Object>> getLibrary()
    {
        List<Map<String, Object>> list = new ArrayList<>();
        return list;
    }


    @GetMapping("/exams")
    public List<Map<String, Object>> getExams()
    {
        List<Map<String, Object>> list = new ArrayList<>();
        return list;
    }
}

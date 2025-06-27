package com.habil;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.habil.controllers.PostgresDbUndertowController;
import com.habil.models.HikariConnection;

import io.undertow.Undertow;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class UndertowServer
{
    private static final Gson gson = new Gson();

    private static void sendJson(HttpServerExchange exchange, Object object)
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(gson.toJson(object));
    }
    public static void main(String[] args) throws Exception
    {
        Connection connection = HikariConnection.getConnection();

        Undertow server = Undertow.builder()
        .addHttpListener(8080, "0.0.0.0")
        .setHandler(exchange -> 
        {
            String path = exchange.getRequestPath();
            String method = exchange.getRequestMethod().toString();

            if (path.equals("/api/employees") && method.equals("GET"))
            {
                List<Map<String, Object>> list = PostgresDbUndertowController.getEmployees(connection);
                sendJson(exchange, list);
                return;
            }

            if (path.startsWith("/api/employees/") && method.equals("GET"))
            {
                String email = path.replace("/api/employees/", "");
                List<Map<String, Object>> list = PostgresDbUndertowController.getEmployeeByEmail(connection, email);
                sendJson(exchange, list);
                return;
            }

            if (path.equals("/api/employees") && method.equals("POST"))
            {
                exchange.getRequestReceiver().receiveFullBytes((ex, data) -> 
                {
                    try
                    {
                        String body = new String(data);
                        UndertowEmployee req = gson.fromJson(body, UndertowEmployee.class);
                        PostgresDbUndertowController.createEmployee(connection, req);
                        ex.setStatusCode(201).endExchange();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        ex.setStatusCode(500).endExchange();
                    }
                });

                return;
            }

            exchange.setStatusCode(404).getResponseSender().send("Not Found");
        })
        .build();

        server.start();

        // connection.close();
        // HikariConnection.shutdown();
    }
}

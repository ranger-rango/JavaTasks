package main.java.com.habil.model;

import java.util.Map;
import java.util.TreeMap;

public class Table
{
    private int tableId;
    private String tableName;
    private Map <Integer, Column> columns;

    public Table(int tableId, String tableName)
    {
        this.tableId = tableId;
        this.tableName = tableName;
        this.columns = new TreeMap<>();
    }

    public Table(int tableId, String tableName, Map<Integer, Column> columns)
    {
        this.tableId = tableId;
        this.tableName = tableName;
        this.columns = new TreeMap<>();
    }

    public int getTableId()
    {
        return tableId;
    }

    public String getTableName()
    {
        return tableName;
    }

    public Map<Integer, Column> getColumns()
    {
        return columns;
    }

    public void setTableId(int tableId)
    {
        this.tableId = tableId;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public void setColumns(Map<Integer, Column> columns)
    {
        this.columns = columns;
    }

}

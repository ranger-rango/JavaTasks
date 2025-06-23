package main.java.com.habil.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Dbms
{
    Map <String, Table> tables;

    public Dbms(Map<String, Table> tables)
    {
        this.tables = new TreeMap<>();
    }

    public void addTableNColumns(int tblID, String tblName, List<Column> colsList)
    {
        Table table = new Table(tblID, tblName);

        for (int i = 0; i < colsList.size(); i++)
        {
            table.getColumns().put(i + 1, colsList.get(i));
        }
        tables.put(tblName, table);
    }

    public boolean addColumn(String tableName, Column newColumn)
    {
        Table table = tables.get(tableName);

        if (table != null)
        {
            int lastKey = ((TreeMap<Integer, Column>) table.getColumns()).lastKey();
            int nextId = lastKey + 1;
            newColumn.setColumnId(nextId);
            table.getColumns().put(nextId, newColumn);
            return true;
        }
        return false;
    }
    public boolean editColumn(String tableName, Column editedColumn)
    {
        int colId = editedColumn.getColumnId();
        Table table = tables.get(tableName);
        if (table != null)
        {
            table.getColumns().replace(colId, editedColumn);
            return true;
        }
        return false;
    }
    public boolean removeColumn(String tableName, Column delColumn)
    {
        Objects.requireNonNull(delColumn, "Col to be deleted cannot be null");

        Table table = tables.get(tableName);
        if (table != null)
        {
            table.getColumns().remove(delColumn.getColumnId());
            return true;
        }
        return false;
    }

    public void getTblsWithSimCols()
    {
        Map <String, Set<String>> colNameToTables = tables.values().stream()
        .flatMap(table -> 
        table.getColumns().values().stream()
        .map(column -> Map.entry(column.getColumnName(), table.getTableName()))
        )
        .collect(Collectors.groupingBy(
            Map.Entry::getKey,
            TreeMap::new,
            Collectors.mapping(
                Map.Entry::getValue,
                Collectors.toCollection(TreeSet::new)
                )
        ));

        System.out.println("Tables with similar-named columns");
        colNameToTables.entrySet().stream()
        .filter(entry -> entry.getValue().size() > 1)
        .forEach(entry -> 
        System.out.println("Tables: " + entry.getValue() +" -> Column: " + entry.getKey())
        );
        
    }

    public void displayTablesNColumns()
    {
        tables.forEach((tableName, table) -> 
        {
            System.out.println("Table: " + tableName);
            System.out.println("Columns: ");
            table.getColumns().values().stream()
            .forEach(column -> 
            System.out.println("colId: " + column.getColumnId() + ", tblName: " + column.getColumnName() + ", dataType: " + column.getDataType())
            );
            System.out.println();
        });
    }

}

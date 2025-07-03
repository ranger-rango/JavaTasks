package main.java.com.habil.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.com.habil.model.Column;
import main.java.com.habil.model.Dbms;
import main.java.com.habil.model.Table;

public class Maps2 {
    public static void main(String[] args)
    {
        Map <String, Table> tables = null;
        Dbms dbms = new Dbms(tables);

        List<Column> columns = new ArrayList<>();
        columns.add(new Column(1, "id", "BIGINT"));
        columns.add(new Column(2, "first_name", "VARCHAR"));
        columns.add(new Column(3, "surname", "VARCHAR"));
        columns.add(new Column(4, "email", "VARCHAR"));
        columns.add(new Column(5, "dob", "DATE"));

        List<Column> columns1 = new ArrayList<>();
        columns1.add(new Column(1, "id", "BIGINT"));
        columns1.add(new Column(2, "product_name", "VARCHAR"));
        columns1.add(new Column(3, "product_uuid", "VARCHAR"));
        columns1.add(new Column(5, "manufacture_date", "DATE"));

        dbms.addTableNColumns(1, "users", columns);
        dbms.addTableNColumns(2, "products", columns1);

        dbms.displayTablesNColumns();
        dbms.addColumn("products", new Column(4, "product_producer", "VARCHAR"));
        dbms.editColumn("users", new Column(4, "email_address", "VARCHAR"));
        dbms.removeColumn("users", new Column(5, null, null));
        dbms.displayTablesNColumns();
        System.out.println();

        dbms.getTblsWithSimCols();



    }
}

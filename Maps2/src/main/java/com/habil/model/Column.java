package main.java.com.habil.model;

public class Column
{
    private int columnId;
    private String columnName;
    private String dataType;

    public Column(int columnId, String columnName, String dataType)
    {
        this.columnId = columnId;
        this.columnName = columnName;
        this.dataType = dataType;
    }

    public int getColumnId()
    {
        return columnId;
    }
    public String getColumnName()
    {
        return columnName;
    }
    public String getDataType()
    {
        return dataType;
    }


    public void setColumnId(int columnId)
    {
        this.columnId = columnId;
    }
    public void setcolumnName(String columnName)
    {
        this.columnName = columnName;
    }
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

}

package main.java.com.habil.models;

public class IntegerHandler implements DataHandler<Integer, Float>
{
    public Float processData(Integer data)
    {
        return 1f / data;
    }
    
}

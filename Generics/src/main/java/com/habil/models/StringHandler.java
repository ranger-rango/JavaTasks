package main.java.com.habil.models;

public class StringHandler implements DataHandler<String, String>
{
    public String processData(String data)
    {
        return new StringBuilder(data).reverse().toString().toUpperCase();
    }
}

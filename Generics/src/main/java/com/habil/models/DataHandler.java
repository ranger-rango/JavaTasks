package main.java.com.habil.models;

public interface DataHandler<T, O>
{
    O processData(T data);
}

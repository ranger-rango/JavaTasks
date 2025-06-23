package main.java.com.habil.models;

public class Item<T>
{
    private T object;

    public Item()
    {}

    public Item(T object)
    {
        this.object = object;
    }

    public T getObject()
    {
        return object;
    }

    public void setObject(T object)
    {
        this.object = object;
    }

    @Override
    public String toString()
    {
        return "Item [object=" + object + "]";
    }
    
}

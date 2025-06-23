package main.java.com.habil.models;

public class Order
{
    private static int count = 0;
    private final int ID;

    public Order()
    {
        this.ID = ++count;
    }

    public int getID() {
        return ID;
    }
}

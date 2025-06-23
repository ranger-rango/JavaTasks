package main.java.com.habil.model;

public class Product
{
    /*private */public String productName;
    /*private */public double price;

    public Product(String productName, double price)
    {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName()
    {
        return productName;
    }
    public double getPrice()
    {
        return price;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "Product {name='" + productName + "', price=" + price + "}";
    }

    public void updatePrice(double price)
    {
        price = price * 2;
        System.out.println("Inside updatePrice(): " + price);
    }

}

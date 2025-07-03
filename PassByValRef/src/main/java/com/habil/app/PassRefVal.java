package main.java.com.habil.app;

import main.java.com.habil.model.Product;

public class PassRefVal
{

    public static void updateProduct(Product product)
    {
        product.productName = "New name";
        product.price = product.price * 2;

        System.out.println("Inside updateProduct(), (" + product.productName + ", " + product.price + ")");
    }

    public static void reassignProduct(Product product)
    {
        product = new Product("Egg", 10);
        System.out.println("Inside reassignProduct(), (" + product.productName + ", " + product.price + ")");
    }
    public static void main(String[] args)
    {
        double price = 65.0;
        String prodName = "milk";

        Product product = new Product(prodName, price);
        System.out.println(product);
        System.out.println();

        product.updatePrice(price);
        System.out.println("Outside updatePrice():" + price);

        System.out.println();

        updateProduct(product);
        System.out.println("Outside updateProduct(), (" + product.productName + ", " + product.price + ")");

        System.out.println();

        reassignProduct(product);
        System.out.println("Outside reassignProduct(), (" + product.productName + ", " + product.price + ")");

    }
}

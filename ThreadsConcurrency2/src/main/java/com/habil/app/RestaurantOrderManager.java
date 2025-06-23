package main.java.com.habil.app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import main.java.com.habil.models.Order;

public class RestaurantOrderManager
{
    public static void main(String[] args)
    {
        BlockingQueue<Order> ordersQueue = new LinkedBlockingQueue<>(10);

        Runnable customer = () -> 
        {
            while (true)
            {
                try
                {
                    Order order = new Order();
                    ordersQueue.put(order);
                    System.out.println(Thread.currentThread().getName() + " placed order # " + order.getID());
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable chef = () -> 
        {
            while (true)
            {
                try
                {
                    Order order = ordersQueue.take();
                    System.out.println("Chef is preparing Order # " + order.getID());
                    Thread.sleep(2000);
                    System.out.println("Chef completed Order # " + order.getID());
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        };

        for (int i = 1; i <= 3; i++)
        {
            new Thread(customer, "Customer-" + i).start();
        }

        new Thread(chef, "Chef").start();

    }   
}

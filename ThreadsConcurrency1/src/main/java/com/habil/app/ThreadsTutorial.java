package main.java.com.habil.app;

// import java.util.concurrent.Callable;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;
// import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

class MyThread extends Thread
{
    public void run()
    {
        System.out.println("Thread is running: " + Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable
{
    public void run()
    {
        System.out.println("Running in : " + Thread.currentThread().getName());
    }
}

// thread synchronisation - only one thread can execute the synchronised mtd at a time 
class Counter
{
    int count = 0;
    synchronized void increment()
    {
        count++;
    }

    public int getCount()
    {
        return count;
    }
}

// volatile keyword - prevents caching of variables, ensuring visibility across threads. this is 
// done through storage of the variable in main mem instead of the thread's mem

class FlagExample
{
    private volatile boolean available = false;

    synchronized void produce() throws InterruptedException
    {
        while (available) wait();
        System.out.println("Produced"  + available);
        available = true;
        notify();
    }

    synchronized void consume() throws InterruptedException
    {
        while (!available) wait();
        System.out.println("Consumed" + available);
        available = false;
        notify();
    }

}

// explicit locking 
class LockExample
{
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void increment()// throws InterruptedException
    {
        lock.lock();
        try
        {
            counter++;
        }
        finally
        {
            lock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }

}

public class ThreadsTutorial
{
    public static void main(String[] args) throws InterruptedException, Exception
    {
        // MyThread t1 = new MyThread();
        // t1.start();
        // Thread t2 = new Thread(new MyRunnable());
        // t2.start();


        // Thread t = new Thread(() ->
        // {
        //     try
        //     {
        //         Thread.sleep(2000);
        //         System.out.println("Thread done sleeping.");
        //     }
        //     catch (InterruptedException e)
        //     {
        //         e.printStackTrace();
        //     }
        // });
        // t.start();
        // t.join();
        // System.out.println("Main thread proceeds.");

        // Counter counter = new Counter();

        // Thread c1 = new Thread(() -> 
        // {
        //     for (int i = 0; i < 20; i++)
        //     {
        //         counter.increment();
        //     }
        // });

        // Thread c2 = new Thread(() -> 
        // {
        //     for (int i = 0; i < 20; i++)
        //     {
        //         counter.increment();
        //     }
        // });

        // c1.start();
        // c2.start();

        // c1.join();
        // c2.join();

        // System.out.println("Final Count: " + counter.getCount());

        FlagExample fe = new FlagExample();

        Thread producer = new Thread(() -> 
        {
            try
            {
                for (int i = 0; i < 5; i++)
                {
                    fe.produce();
                    Thread.sleep(500);
                }
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> 
        {
            try
            {
                for (int i = 0; i < 5; i++)
                {
                    fe.consume();
                    Thread.sleep(500);
                }
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();


        // thread pools - for automatically managing threads 
        // ExecutorService executor = Executors.newFixedThreadPool(3);
        // Runnable task = () -> System.out.println("Task by: " + Thread.currentThread().getName());

        // for (int i = 0; i < 5; i++)
        // {
        //     executor.submit(task);
        // }
        // executor.shutdown();


        // callable and future - used when you want to return a result 
        // ExecutorService executor = Executors.newSingleThreadExecutor();

        // Callable<Integer> task = () -> 
        // {
        //     Thread.sleep(1000);
        //     return 42;
        // };

        // Future<Integer> future = executor.submit(task);
        // System.out.println("Doing other work...");
        // System.out.println("Result: " + future.get());
        // executor.shutdown();


        LockExample example = new LockExample();

        Runnable task = () -> 
        {
            for (int i = 0; i < 1000; i++)
            {
                example.increment();
            }

        };

        Thread le1 = new Thread(task, "T1");
        Thread le2 = new Thread(task, "T2");
        Thread le3 = new Thread(task, "T3");

        le1.start();
        le2.start();
        le3.start();

        try
        {
            le1.join();
            le2.join();
            le3.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Final Counter: " + example.getCounter());

    }
}

// implements runnable is useful when the class already extends another class 

// thread lifecycle
// new - thread created but not started
// runnable - started and waiting to be scheduled
// running - currently executing
// blocked/waiting - waiting for resource of signal
// terminated - finished execution or stopped


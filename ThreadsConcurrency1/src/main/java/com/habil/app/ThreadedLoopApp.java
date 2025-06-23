package main.java.com.habil.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import main.java.com.habil.model.ThreadedRangeSum;

public class ThreadedLoopApp
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        final int RANGE = 1_000_000;
        final int CHUNK_SIZE = 10_000;

        // thread pooling 
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // store the sums of each range 
        List<Future<Long>> futures = new ArrayList<>();

        // loop from 1 - 1000000, through the ranges 
        for (int start = 1; start <= RANGE; start += CHUNK_SIZE)
        {
            int end = Math.min(start + CHUNK_SIZE - 1, RANGE);

            // run the range sums 
            ThreadedRangeSum task = new ThreadedRangeSum(start, end);
            Future<Long> future = executor.submit(task);

            // add the range sum to the futures list 
            futures.add(future);
        }

        // aggregate the range sums 
        long grandTotal = 0;
        for (Future<Long> future : futures)
        {
            System.out.println("Range Sum: " + future.get());
            grandTotal += future.get();
        }

        executor.shutdown();

        System.out.println("Grand Total: " + grandTotal);
    }
}

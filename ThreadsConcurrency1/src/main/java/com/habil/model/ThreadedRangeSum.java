package main.java.com.habil.model;

import java.util.concurrent.Callable;

public class ThreadedRangeSum implements Callable<Long> //allows the thread to return a value
{
    private final int start;
    private final int end;
    public ThreadedRangeSum(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // implements the range sums 
    @Override
    public Long call()
    {
        long sum = 0;
        for (int i = start; i <= end; i++)
        {
            sum += i;
        }
        return sum;
    }
    
}

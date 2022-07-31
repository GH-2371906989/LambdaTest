package com.gu.callable;

import java.util.concurrent.*;

public class TestCallable implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {

        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable();
        TestCallable t2 = new TestCallable();
        TestCallable t3 = new TestCallable();
//        创建固定线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Future<Boolean> submit1 = pool.submit(t1);
        Future<Boolean> submit2 = pool.submit(t1);
        Future<Boolean> submit3 = pool.submit(t1);
        Boolean s1 = submit1.get();
        Boolean s2 = submit2.get();
        Boolean s3 = submit3.get();
        //停止线程池
        pool.shutdown();
    }
}

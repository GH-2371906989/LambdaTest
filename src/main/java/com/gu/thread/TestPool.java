package com.gu.thread;

public class TestPool {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        new Thread(myThread).start();
    }
}


class MyThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}


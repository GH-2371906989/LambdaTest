package com.gu.thread;


import java.util.concurrent.locks.ReentrantLock;

public class RunMachine implements Runnable{
    private int a=1000;
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                if (a<=0){
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--->拿到了第:"+a--+"张票");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        RunMachine machine = new RunMachine();
        Thread t1=new Thread(machine,"张三");
        Thread t3=new Thread(machine,"里斯");
        Thread t2=new Thread(machine,"黄牛");
        t1.start();
        t3.start();
        t2.start();
    }
}

package com.gu.thread;

import java.util.concurrent.TimeUnit;

public class SynchronizedThread {
    public static void main(String[] args) {
        //创建三个线程,线程操纵资源类
        Ticket ticket = new Ticket();
        for(int i=0;i<3;i++){
            new Thread(()->{
                try {
                    ticket.sell();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"售票员"+String.valueOf(i)).start();
        }
    }
}


class Ticket{
    private int num = 10;
    public synchronized void sell() throws InterruptedException {
        while(num>0){
            System.out.println(Thread.currentThread().getName()+"卖票，票号为："+(num--));
            //为了创造一些异常，让线程到此处sleep阻塞一下1
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
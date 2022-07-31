package com.gu.thread;

//生产者消费者模式-->利用缓冲区解决
public class TestPC {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
//        new Product(synContainer).start();
        new Consumer(synContainer).start();
    }
}

//生产者
class Product extends Thread{
    SynContainer container;
    public Product(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            this.container.add();
            System.out.println("生产第"+i+"只鸡");
        }
    }
}
//消费者
class Consumer extends Thread{
    SynContainer container;
    public Consumer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            this.container.delect();
            System.out.println("消费第"+i+"只鸡");
        }
    }
}

class SynContainer{
    int sizes = 10;
    int count = 0;
    public synchronized void add(){
        if (count>=sizes) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //没有满添加
        count++;
        System.out.println("仓库加一,现有:"+count);
        //可以消费了
        this.notifyAll();
    }
    public synchronized void delect(){
        if (count<=0) {
            try {
                System.out.println("仓库无鸡,等待上架通知");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //没有满添加
        count--;
        System.out.println("仓库减一,现有:"+count);
        //可以消费了
        this.notifyAll();
    }
}
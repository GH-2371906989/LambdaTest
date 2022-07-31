package com.gu.thread;

public class ThMachine extends Thread{
    @Override
    public void run() {
        for(int a=0;a<50;a++)
            System.out.println(a);
    }

    public static void main(String[] args) {
        ThMachine thMachine = new ThMachine();
        thMachine.start();
    }
}

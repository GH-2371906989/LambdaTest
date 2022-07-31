package com.gu.thread;

public class Ract implements Runnable{
    private static String winner;
    @Override
    public void run() {
        for (int i=1;i<=100;i++){
            boolean b = gameover(i);
            if (b){
                break;
            }

            if ("兔子".equals(Thread.currentThread().getName())&&i%10==0){

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"-->跑了"+i+"步了");
        }
    }
//    判断是否完成比赛
    private boolean gameover(int i){
        if (winner!=null){
            return true;
        }else {
            if (i>=100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is"+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Ract ract = new Ract();
        new Thread(ract,"兔子").start();
        new Thread(ract,"乌龟").start();
    }

}

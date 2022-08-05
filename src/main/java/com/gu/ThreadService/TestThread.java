package com.gu.ThreadService;

public class TestThread  extends TaskThread{

    @Override
    public boolean dealMain() {
        System.out.println("测试");
        return false;
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.dealMain();
    }
}

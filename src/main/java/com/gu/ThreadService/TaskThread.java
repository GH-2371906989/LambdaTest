package com.gu.ThreadService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TaskThread implements Runnable {


    @Override
    public void run() {
        System.out.println("run");
    }
    public abstract boolean dealMain();
}

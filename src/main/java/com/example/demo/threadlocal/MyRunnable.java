package com.example.demo.threadlocal;

public class MyRunnable implements Runnable{
    private ThreadLocal<Integer> local = new ThreadLocal<>();
    @Override
    public void run() {
        local.set((int)(Math.random() * 100D));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(local.get());
    }
}

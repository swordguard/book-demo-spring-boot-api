package com.example.demo.threadlocal;

public class ThreadLocalExample2 {
    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Thread t1 = new Thread(() -> {
            System.out.println("===== Thread 1 =====");
            local.set("local");
            inheritableThreadLocal.set("inheriated local");
            System.out.println(local.get());
            System.out.println(inheritableThreadLocal.get());

            Thread t11 = new Thread(() -> {
                System.out.println("===== Child Thread 1 =====");
                System.out.println(local.get());
                System.out.println(inheritableThreadLocal.get());
            });
            t11.start();
        });

                t1.start();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===== Thread2 =====");
            System.out.println(local.get());
            System.out.println(inheritableThreadLocal.get());
            local.set("2");
            inheritableThreadLocal.set("22");
            System.out.println(local.get());
            System.out.println(inheritableThreadLocal.get());
        });
        t2.start();
    }
}

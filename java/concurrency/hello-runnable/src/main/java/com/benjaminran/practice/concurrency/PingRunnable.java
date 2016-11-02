package com.benjaminran.practice.concurrency;

public class PingRunnable implements Runnable {

    private Thread before;
    
    public PingRunnable(Thread before) {
        this.before = before;
    }

    public void run() {
        try {
            before.join();
            while(!Thread.interrupted()) {
                Thread.sleep(1000);
                System.out.println("Ping");
            }
        }
        catch (InterruptedException e) {
            System.out.format("Thread %s interrupted: %s%n", Thread.currentThread().getName(), e);
        }
        finally {
            System.out.format("Bye from PingRunnable%n");
        }
    }
}

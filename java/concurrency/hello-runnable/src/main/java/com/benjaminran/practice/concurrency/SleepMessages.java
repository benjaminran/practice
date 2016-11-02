package com.benjaminran.practice.concurrency;

public class SleepMessages implements Runnable {

    public void run() {
        String info[] = {"foo", "bar", "baz", "qux", "corge", "norf" };
        for (String s : info) {
            try {
                Thread.sleep(800);
            }
            catch (InterruptedException e) {
                throw new RuntimeException("interrupted while printing info");
            }
            System.out.println(s);
        }
    }
}

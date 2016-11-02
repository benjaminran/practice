package com.benjaminran.practice.concurrency;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread messageThread = new Thread(new SleepMessages());
        messageThread.start();
        Thread pingThread = new Thread(new PingRunnable(messageThread));
        pingThread.start();
    }
}

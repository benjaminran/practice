package com.benjaminran.practice.concurrency;

import java.util.Random;

public class Consumer implements Runnable {
    private GuardedBlocks drop;

    public Consumer(GuardedBlocks drop) {
        this.drop = drop;
    }
    
    public void run() {
        Random random = new Random();
        for(String message = drop.take();
            !message.equals("DONE");
            message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
    }
}

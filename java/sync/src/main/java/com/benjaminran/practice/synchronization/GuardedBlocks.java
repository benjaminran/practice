package com.benjaminran.practice.concurrency;

public class GuardedBlocks {

    private String message;
    private boolean empty = true;

    public synchronized String take() {
        while(empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        while(!empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
    
    public static void main(String[] args) {
        GuardedBlocks drop = new GuardedBlocks();
        Producer p = new Producer(drop);
        Consumer c = new Consumer(drop);
        (new Thread(p)).start();
        (new Thread(c)).start();
    }
}

package com.benjaminran.practice.concurrency.highlevel;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;


public class Clock {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    
    private final Runnable tick = new Runnable() {
            public void run() {
                System.out.format(" %s\r", dateFormat.format(Calendar.getInstance().getTime()));
            }
        };

    public void start() {
        scheduler.scheduleAtFixedRate(tick, 0, 1, TimeUnit.SECONDS);
    }
    
    public static void main(String[] args) {
        (new Clock()).start();
        System.out.println("Started clock");
    }
}

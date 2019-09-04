package com.batiaev.java3.lesson4.lesson4_Batiaev.homeWorkLesso4_batiaev;

import java.util.function.Consumer;

public class TextPrinter implements Runnable{
    private final Controller monitor;
    private final int count;
    private final String text;
    private final Consumer<String> action;

    TextPrinter(String text, int count, Controller monitor,
                Consumer<String> action) {
        this.text = text;
        this.monitor = monitor;
        this.count = count;
        this.action = action;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            for (int i = 0; i < count; i++) {
                try {
                    while (!monitor.getCurrent().equals(text)) {
                        monitor.wait();
                    }
                    action.accept(text);
                    Thread.sleep(100);
                    monitor.move();
                    monitor.notifyAll();
                } catch (Exception e) {
                    System.out.println("Step "+ i
                            + " failed with exception: " + e.getMessage());
                }
            }
        }
    }
}

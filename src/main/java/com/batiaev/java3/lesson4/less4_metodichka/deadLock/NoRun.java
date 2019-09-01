package com.batiaev.java3.lesson4.less4_metodichka.deadLock;

public class NoRun {
    private volatile char currentLetter = 'A';
    final int COUNT = 5;


    public static void main(String[] args) {
        NoRun c = new NoRun();
        new Thread(()->{
            //c.A();
        }).start();

    }


    class A extends Thread{
        @Override
        public void run() {
            synchronized (this) {
                try {
                    for (int i = 0; i < COUNT; i++) {
                        while (currentLetter != 'A') {
                            this.wait();
                        }
                        System.out.print("A");
                        currentLetter = 'B';
                       this.notify();
                    }
                } catch (InterruptedException e) { e.printStackTrace();
                }
            }
        }
    }
}

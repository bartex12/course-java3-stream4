package com.batiaev.java3.lesson4.less4_metodichka.Interaction_Wait;

// 1 создаётся класс WaitNotifyClass
// 2  в этом классе создаётся статический метод main
// 3 создаются нестатические методы класса Syncronazed_In_Method - это printA(), printB()
// к которым идёт обращение из статического метода main через создание экземпляра класса - w

public class WaitNotifyClass {

    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        Thread t1 = new Thread(() -> {
        w.printA();
    });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        t1.start();
        t2.start();
    }

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 3; i++) {
                    while (currentLetter != 'A') {
                        mon.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    mon.notify();
                }
            } catch (InterruptedException e) { e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 3; i++) {
                    while (currentLetter != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'A';
                    mon.notify();
                }
            } catch (InterruptedException e) { e.printStackTrace();
            }
        }
    }
}

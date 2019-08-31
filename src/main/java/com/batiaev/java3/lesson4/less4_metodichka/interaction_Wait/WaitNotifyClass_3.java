package com.batiaev.java3.lesson4.less4_metodichka.interaction_Wait;

// 1 создаётся класс WaitNotifyClass_3
// 2  в этом классе создаётся статический метод main
// 3 создаются нестатические методы класса Syncronazed_In_Method - это printA(), printB(), printC()
// к которым идёт обращение из статического метода main через создание экземпляра класса - w

public class WaitNotifyClass_3 {

    //

    private final Object mon = new Object();
    private volatile char currentLetter = 'A';
    final int COUNT = 5;

    public static void main(String[] args) {
        WaitNotifyClass_3 w = new WaitNotifyClass_3();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        Thread t3 = new Thread(() -> {
            w.printC();
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while (currentLetter != 'A') {
                        mon.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) { e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while (currentLetter != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) { e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while (currentLetter != 'C') {
                        mon.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) { e.printStackTrace();
            }
        }
    }
}
//вывод будет
//ABCABCABCABCABC
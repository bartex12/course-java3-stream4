package com.batiaev.java3.lesson4.less4_metodichka.interaction_Wait;

public class WaitNotifyClass_3_Var2 {

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

     class A extends Thread{

         C c =new C();
//         public printA(printC pC ){
//             this.pC = pC;
//         }
         @Override
         public void run() {
             synchronized (c) {
                 try {
                     for (int i = 0; i < COUNT; i++) {
                         while (currentLetter != 'A') {
                             c.wait();
                         }
                         System.out.print("A");
                         currentLetter = 'B';
                         c.notify();
                     }
                 } catch (InterruptedException e) { e.printStackTrace();
                 }
             }
         }


    }

     class B extends Thread {
         A a =new A();
         @Override
         public void run() {
             synchronized (a) {
                 try {
                     for (int i = 0; i < COUNT; i++) {
                         while (currentLetter != 'B') {
                             a.wait();
                         }
                         System.out.print("B");
                         currentLetter = 'C';
                         a.notify();
                     }
                 } catch (InterruptedException e) { e.printStackTrace();
                 }
             }
         }
    }

     class C extends Thread{

        B b = new B();
//         public printC(printB pB ){
//             this.pB = pB;
//         }

         @Override
         public void run() {
             synchronized (b) {
                 try {
                     for (int i = 0; i < COUNT; i++) {
                         while (currentLetter != 'C') {
                             b.wait();
                         }
                         System.out.print("C");
                         currentLetter = 'A';
                         b.notifyAll();
                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
         }


        }
    }
}

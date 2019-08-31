package com.batiaev.java3.lesson4.less4_metodichka.methodsOfSyncronization.notifyAndNotifyAll;

public class UseNotify {

        public static void main(String[] args) throws InterruptedException
        {
            Geek1 geeks1 = new Geek1();
            Geek2 geeks2 = new Geek2(geeks1);
            Geek3 geeks3 = new Geek3(geeks1);
            Thread t1 = new Thread(geeks1, "Thread-1");
            Thread t2 = new Thread(geeks2, "Thread-2");
            Thread t3 = new Thread(geeks3, "Thread-3");
            t1.start();
            t2.start();
            Thread.sleep(100);
            t3.start();
        }
    // Java program to illustrate the
// behaviour of notify() method
    static class Geek1 extends Thread {
        public void run()
        {
            synchronized(this)
            {
                System.out.println
                        (Thread.currentThread().getName() + "...starts");
                try {
                    this.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println
                        (Thread.currentThread().getName() + "...notified");
            }
        }
    }
    static class Geek2 extends Thread {
        Geek1 geeks1;
        Geek2(Geek1 geeks1)
        {
            this.geeks1 = geeks1;
        }
        public void run()
        {
            synchronized(this.geeks1)
            {
                System.out.println
                        (Thread.currentThread().getName() + "...starts");

                try {
                    this.geeks1.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println
                        (Thread.currentThread().getName() + "...notified");
            }
        }
    }
    static class Geek3 extends Thread {
        Geek1 geeks1;
        Geek3(Geek1 geeks1)
        {
            this.geeks1 = geeks1;
        }
        public void run()
        {
            synchronized(this.geeks1)
            {
                System.out.println
                        (Thread.currentThread().getName() + "...starts");
                this.geeks1.notify();
                System.out.println
                        (Thread.currentThread().getName() + "...notified");
            }
        }
    }
}
//Thread-1...starts
//Thread-2...starts
//Thread-3...starts
//Thread-3...notified
//Thread-1...notified
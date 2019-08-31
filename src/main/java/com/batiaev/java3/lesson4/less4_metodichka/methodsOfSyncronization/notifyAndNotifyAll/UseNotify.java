package com.batiaev.java3.lesson4.less4_metodichka.methodsOfSyncronization.notifyAndNotifyAll;

public class Main {

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
    
}

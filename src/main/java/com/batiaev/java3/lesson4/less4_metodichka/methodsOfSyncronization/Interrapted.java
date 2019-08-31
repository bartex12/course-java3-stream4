package com.batiaev.java3.lesson4.less4_metodichka.methodsOfSyncronization;

import java.util.concurrent.TimeUnit;

//основной поток ждёт пока закончится thread - для этого использован join
//обработано исключение и выводится Interrupted, затем выводится End
// Метод join довольно прост, потому что является просто методом с java кодом,
// который выполняет wait, пока поток, на котором он вызван, живёт.
// Как только поток умирает (при завершении), ожидание прерывается. Вот и вся магия метода join.

public class Interrapted {
    public static void main(String []args) {

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End");

    }
}
        //Interrupted
        //End
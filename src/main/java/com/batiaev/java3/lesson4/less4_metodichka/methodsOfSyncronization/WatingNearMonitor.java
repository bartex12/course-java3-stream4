package com.batiaev.java3.lesson4.less4_metodichka.methodsOfSyncronization;

public class WatingNearMonitor {

    //Здесь главный поток сначала отправляет задачу task в новый поток,
    // а потом сразу же "захватывает" лок и выполняет с ним долгую операцию (8*0,2 секунд).
    // Всё это время task не может для своего выполнения зайти в блок synchronized, т.к. лок уже занят.
    //Если поток не может получить лок, он будет ждать этого у монитора.
    // Как только получит — продолжит выполнение. Когда поток выходит из-под монитора, он освобождает лок.
    //
    //если между  th1.start(); и  synchronized (lock) { поставить Thread.sleep(1);
    //то поток th1 успеет войти в монитор до основного потока и последовательность вывода будет другой

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Runnable task = () -> {
            synchronized (lock) {
                System.out.println("thread");
            }
        };

        Thread th1 = new Thread(task);
        th1.start();
        //Thread.sleep(1);
        synchronized (lock) {
            for (int i = 0; i < 8; i++) {
                Thread.currentThread().sleep(200);
                System.out.print("  " + i);
            }
            System.out.println(" ...");
        }
    }
}
//вывод
//  0  1  2  3  4  5  6  7 ...
//thread
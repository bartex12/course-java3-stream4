package com.batiaev.java3.lesson4.less4_metodichka.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Метод submit(Runnable) также принимает реализацию Runnable,
// но возвращает объект типа Future, который можно использовать
// для проверки завершенности выполнения задачи.

public class Submit_Runnable {
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future future = executorService.submit(new Runnable() {
        public void run() {
            System.out.println("Асинхронная задача " + Thread.currentThread().getName());
        }
    });
        // вернет null если задача завершилась корректно
        System.out.println("future1.get() = " + future.get());

        Future future1 = executorService.submit(new Runnable() {
            public void run() {
                System.out.println("Асинхронная задача " + Thread.currentThread().getName());
            }
        });
        // вернет null если задача завершилась корректно
        System.out.println("future1.get() = " + future1.get());

        executorService.shutdown();
    }

}

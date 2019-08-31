package com.batiaev.java3.lesson4.less4_metodichka.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Метод execute(Runnable) принимает объект java.lang.Runnable и выполняет его асинхронно.
//Получить из потока результат выполнения не получится, для этого нужно использовать интерфейс Callable,

public class Execute_ {
    public static void main(String[] args) {
        info();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Runnable() {
            public void run() {
                info();
            }
        });
        executorService.execute(new Runnable() {
            public void run() {
                info();
            }
        });
        executorService.execute(new Runnable() {
            public void run() {
                info();
            }
        });
        executorService.shutdown();
    }

    static void info(){
        System.out.println("Асинхронная задача " + Thread.currentThread().getName());
    }
}
//вывод для Executors.newFixedThreadPool(3)
//                    Асинхронная задача main
//                    Асинхронная задача pool-1-thread-1
//                    Асинхронная задача pool-1-thread-2
//                    Асинхронная задача pool-1-thread-3
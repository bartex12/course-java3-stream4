package com.batiaev.java3.lesson4.less4_metodichka.executorService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Экземпляр Callable также позволяет дать потоку задачу,
// но в отличие от Runnable, его метод call() может возвращать результат.
// Результат Callable может быть получен через объект Future, возвращенный методом submit.
//. Для завершения потоков внутри ExecutorService, необходимо вызвать метод shutdown().
// ExecutorService не будет закрыт немедленно, но перестанет принимать новые задачи,
// и как только все потоки завершат текущие задачи, ExecutorService отключится.

public class Submit_Callable {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future future = executorService.submit(new Callable(){
        public Object call() throws Exception {
            System.out.println("Асинхронный вызов "  + Thread.currentThread().getName());
            return "Результат из потока " + Thread.currentThread().getName();
        }
    });
        System.out.println(future.get());

        Future future1 = executorService.submit(new Callable(){
            public Object call() throws Exception {
                System.out.println("Асинхронный вызов " + Thread.currentThread().getName());
                return (int)35+ " " + Thread.currentThread().getName();
            }
        });
        System.out.println(future1.get());

        executorService.shutdown();
    }
}
//вывод для 2 потоков
//      Асинхронный вызов pool-1-thread-1
//      Результат из потока pool-1-thread-1
//      Асинхронный вызов pool-1-thread-2
//      35 pool-1-thread-2
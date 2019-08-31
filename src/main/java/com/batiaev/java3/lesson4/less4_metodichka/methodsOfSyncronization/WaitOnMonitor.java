package com.batiaev.java3.lesson4.less4_metodichka.methodsOfSyncronization;


//Поток main создаёт новый поток с задачей task, запускает его и ждёт 3 секунды.
// Это позволяет с большой долей вероятности новому потоку захватить лок прежде, чем поток main,
// и встать в очередь по монитору. После чего поток main сам входит в блок синхронизации по lock
// и выполняет уведомление потока по монитору. После того, как уведомление отправлено,
// поток main освобождает монитор lock, а новый поток (который ранее ждал)
// дождавшись освобождения монитора lock, продолжает выполнение.

public class WaitOnMonitor {
    public static void main(String []args) throws InterruptedException {
        Object lock = new Object();
        // task будет ждать, пока его не оповестят через lock
        Runnable task = () -> {
            synchronized(lock) {
                try {
                    System.out.println("ожидаем уведомления по монитору lock (notify или notifyAll)...");
                    //будем находиться в состоянии ожидания, пока любой другой поток не вызовет
                    // метод notify () или notifyAll () для того же объекта.
                    lock.wait();
                    System.out.println(Thread.currentThread().getName()+" принял  notify или notifyAll - входим в монитор");
                } catch(InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
            // После оповещения нас мы будем ждать, пока сможем взять лок
            System.out.println(Thread.currentThread().getName() + " завершил работу");
        };
        Thread taskThread = new Thread(task);
        taskThread.start();
        // Ждём и после этого забираем себе лок, оповещаем и отдаём лок
        Thread.currentThread().sleep(3000);
        System.out.println(Thread.currentThread().getName() +  " проснулся");
        synchronized(lock) {
            lock.notify();
            System.out.println(Thread.currentThread().getName()+" послал notify() на объекте lock");
        }

    }
}
//ждём от кого нибудь на объекте lock оповещения notify или notifyAll...
//main проснулся
//main послал notify() на объекте lock
//Thread-0 принял  notify или notifyAll - входим в монитор
//Thread-0 завершил работу
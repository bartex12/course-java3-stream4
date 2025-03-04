package com.batiaev.java3.Lesson5.metodichka_Lesson5.semafor;

import java.util.concurrent.Semaphore;

//Semaphore ограничивает количество потоков при работе с ресурсами. Для этого служит счетчик.
// Если его значение больше нуля, то потоку разрешается доступ, а значение уменьшается.
// Если счетчик равен нулю, то текущий поток блокируется до освобождения ресурса.
// Для получения доступа используется метод acquire(), для освобождения – release().

// В примере ниже с	помощью метода acquire() одновременно захватить семафор могут только два потока.
// Остальные становятся в очередь, пока один из «захватчиков» не освободит семафор методом release().

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore smp = new Semaphore(2);
        for (int i = 0; i < 5; i++) {

            final int w = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + w + " перед семафором");
                    smp.acquire();
                    System.out.println("Поток " + w + " получил доступ к ресурсу");
                            Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace();
                } finally {
                    System.out.println("Поток " + w + " освободил ресурс");
                    smp.release();
                }
            }).start();
        }
    }
}

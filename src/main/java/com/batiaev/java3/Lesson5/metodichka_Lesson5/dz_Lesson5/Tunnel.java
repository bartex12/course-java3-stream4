package com.batiaev.java3.Lesson5.metodichka_Lesson5.dz_Lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    Semaphore semaphore;

    public Tunnel(int numberCars) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        semaphore = new Semaphore(numberCars/2);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " +
                        description);
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) { e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " +
                        description);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

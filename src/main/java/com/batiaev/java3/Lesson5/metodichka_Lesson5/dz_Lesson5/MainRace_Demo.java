package com.batiaev.java3.Lesson5.metodichka_Lesson5.dz_Lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainRace_Demo {

    public static final int CARS_COUNT = 4;
    
    public static void main(String[] args) throws InterruptedException {

        //передаём  в Car, чтобы обеспечить порядок вывода Гонка началась!!! и Гонка закончилась!!!
        CountDownLatch[] count = {new CountDownLatch(CARS_COUNT),new CountDownLatch(CARS_COUNT)};
        // передаём в Car, чтобы не делать каст  (int) от long и не поймать исключение когда нибудь
        CyclicBarrier barier = new CyclicBarrier(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), barier, count );
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        count[0].await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        count[1].await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

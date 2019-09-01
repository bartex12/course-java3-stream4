package com.batiaev.java3.Lesson5.metodichka_Lesson5.dz_Lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    CyclicBarrier barrier;
    CountDownLatch[] count;
    long countSize;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier barrier, CountDownLatch[] count) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.count = count;
        this.countSize = count[1].getCount();
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            //System.out.println(" count[0].getCount()" + count[0].getCount());
            count[0].countDown();
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //пробегаем по этапам гонки - на каждом этапе вызывается свой метод go(),
        // отвечающий за прохождение этапа
        for (int i = 0; i < race.getStages().size();i++) {
            race.getStages().get(i).go(this);
            //если это последний этап
            if ( i ==(race.getStages().size()-1)){
               try {
                    // считаем  участников, прошедших последний этап
                    count[1].countDown();
                    //если это первый прошедший - он победил
                    if (count[1].getCount()==countSize-1){
                        System.out.println( this.name + " WIN");
                    }
                    //собираем на барьере - чтобы сообщение " WIN" появлялась в правильном месте
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    }

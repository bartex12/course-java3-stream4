package com.batiaev.java3.lesson4.less4_metodichka.MethodsOfSyncronization;

// 1 создаётся класс Syncronized_In_Static
// 2  в этом классе создаётся статический метод main
// 3 создаётся статический метод класса - это method1()
// обращение к методу идёт напрямую - без создания экземпляра класса

public class Syncronized_In_Static {

    //При указании ключевого слова synchronized в объявлении статического метода
    // в роли монитора выступает сам класс Syncronized_In_Static

    public static void main(String[] args) {
        System.out.println("Start");
        new Thread(() -> method()).start();
        new Thread(() -> method()).start();
    }

    public synchronized static void method() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//вывод
//                Start
//                01234567890123456789
package com.batiaev.java3.lesson4.less4_metodichka.methodsOfSyncronization;

// 1 создаётся класс ObjectsHowMonitor
// 2  в этом классе создаётся статический метод main
// 3 создаются нестатические методы класса Syncronazed_In_Method - это method1(), method2()
// к которым идёт обращение из статического метода main через создание экземпляра класса - e2

public class ObjectsHowMonitor {

//    Этот способ синхронизации основан на использовании
//    отдельных объектов типа java.lang.Object в качестве мониторовВ
//    этом случае в роли монитора выступает объект lock1, соответственно два потока смогут
//    параллельно выполнять первую часть метода method1(), однако в блок синхронизации
//    в единицу времени может зайти только один поток, так как происходит захват монитора lock1.

    private Object lock1 = new Object();

    public static void main(String[] args) {
        ObjectsHowMonitor e2 = new ObjectsHowMonitor();
        System.out.println("Старт main потока");
        new Thread(() -> e2.method1()).start();
        new Thread(() -> e2.method1()).start();
    }

    public void method1() {
        System.out.println("\nМетод запущен");
        System.out.println("Блок 1 начало");
        for (int i = 0; i < 3; i++) {
            System.out.print(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nБлок 1 конец");

        synchronized (lock1) {
            System.out.println("Начало синхронизированного блока");
            for (int i = 0; i < 10; i++) {
                System.out.print(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\nКонец синхронизированного блока");
        }
        System.out.println("\nМетод завершил свою работу");
    }
}
        //вывод будет такой
//несинхрониз блок залезает в синхрониз иногда
//
//                        Метод запущен
//                        Блок 1 начало
//                        0
//                        Метод запущен
//                        Блок 1 начало
//                        01122
//                        Блок 1 конец
//                        Начало синхронизированного блока
//                        0
//                        Блок 1 конец
//                        123456789
//                        Конец синхронизированного блока
//                        Начало синхронизированного блока
//                        0
//                        Метод завершил свою работу
//                        123456789
//                        Конец синхронизированного блока
//
//                        Метод завершил свою работу

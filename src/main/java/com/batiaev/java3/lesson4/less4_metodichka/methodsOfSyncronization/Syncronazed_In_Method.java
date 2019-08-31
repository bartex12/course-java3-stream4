package com.batiaev.java3.lesson4.less4_metodichka.methodsOfSyncronization;


// 1 создаётся класс Syncronazed_In_Method
// 2  в этом классе создаётся статический метод main
// 3 создаются нестатические методы класса Syncronazed_In_Method - это method1(), method2()
// к которым идёт обращение из статического метода main через создание экземпляра класса - key

public class Syncronazed_In_Method {

//    При указании ключевого слова synchronized в объявлении метода, в роли монитора выступает объект,
//    у	которого был вызван синхронизированный метод. -у нас это key
//    То есть в приведенном выше примере два потока не смогут параллельно выполнять method1() и method2().

    public static void main(String[] args) {
        Syncronazed_In_Method key = new Syncronazed_In_Method();

        //через лямбда
//        new Thread(() -> key. method1()).start();
//        new Thread(() -> key. method2()).start();

//        //через классику
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" поток t1 method1");
                // key выступает в роли монитора, поэтому во втором потоке доступа к методу method2()
                // не будет пока не освободится монитор
               key.method1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" поток t2 method2");
                // key выступает в роли монитора, поэтому во втором потоке доступа к методу method1()
                // не будет пока не освободится монитор
                key.method2();
            }
        });
        t1.start();
        t2.start();
    }

    public synchronized  void method1() {
        System.out.println("M1-начало");
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("M1-конец");
    }

    public synchronized void method2() {
        System.out.println("M2-начало");
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("M2-конец");
    }
}
//вывод будет такой
//                    поток t1 method1
//                    поток t2 method2
//                    M1-начало
//                0123456789
//                    M1-конец
//                    M2-начало
//                0123456789
//                    M2-конец

//если бы не было синхронизации, вывод мог быть таким
//                    поток t1 method1
//                    поток t2 method2
//                    M2-начало
//                0M1-начало
//                0112233445566778899
//                    M2-конец
//
//                    M1-конец

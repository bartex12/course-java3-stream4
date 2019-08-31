package com.batiaev.java3.lesson4.less4_metodichka;

// 1 создаётся класс MainClass_Thread
// 2  в этом классе создаётся статический метод main
// 3 создаётся внутренний статический класс MyThread (он может быть только статическим, чтобы использовать в main )
// 4 обращение к классу идёт напрямую - без создания экземпляра класса

public class MainClass_Thread {

    //В классе Thread имеется несколько методов, которые можно переопределить в порожденном классе.
    // Из них обязательному переопределению подлежит только метод run().
    // Этот же метод, безусловно, должен быть определен и при реализации интерфейса Runnable.
    // В большинстве случаев создавать подкласс, порожденный от класса Thread, следует в случае,
    // если требуется дополнить его новыми функциями. Так, если переопределять любые другие методы из класса Thread
    // не нужно, то можно ограничиться только реализацией интерфейса Runnable.
    // Кроме того, реализация интерфейса Runnable позволяет создаваемому потоку
    // наследовать класс, отличающийся от Thread.

    public static void main(String[] args) {
        new MyThread().start();
        MyThread t = new MyThread();
        t.start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                    System.out.print(i);
                } catch (InterruptedException e) { e.printStackTrace();
                }
            }
        }
    }

}
//вывод
//00112233445566778899
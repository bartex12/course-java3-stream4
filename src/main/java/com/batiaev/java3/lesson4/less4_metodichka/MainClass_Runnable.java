package com.batiaev.java3.lesson4.less4_metodichka;

// 1 создаётся класс MainClass_Runnable
// 2  в этом классе создаётся статический метод main
// 3 создаётся внутренний статический класс MyRunnableClass (он может быть только статическим, чтобы использовать в main )
// 4 обращение к классу идёт напрямую - без создания экземпляра класса

public class MainClass_Runnable {

    //пример запуска потока через реализацию интерфейса Runnable:
    //Класс MyRunnableClass реализует интерфейс Runnable, в теле метода run() прописан цикл,
    // который выводит в консоль числа от 0 до 9. Метод sleep() приостанавливает поток, из которого он был вызван,
    // на указанное число миллисекунд, в нем может быть сгенерировано исключение InterruptedException.
    // Следовательно, его нужно вызывать в блоке try. В методе же main() создается два объекта типа Thread,
    // конструктору которых в качестве аргумента передаются объекты класса MyRunnableClass,
    // после чего новые потоки запускаются с помощью метода start().
    //Выполнение программы продолжается до тех пор, пока все потоки не завершат работу.

    public static void main(String[] args) {
        new Thread(new MyRunnableClass()).start();
        new Thread(new MyRunnableClass()).start();
    }

    static class MyRunnableClass implements Runnable {
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
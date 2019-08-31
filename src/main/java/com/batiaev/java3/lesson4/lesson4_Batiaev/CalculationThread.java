package com.batiaev.java3.lesson4.lesson4_Batiaev;

public class CalculationThread extends Thread {

    /**
     * CalculationThread
     *
     * @author anton
     * @since 29/08/19
     */
        @Override
        public void run() {
            System.out.println("Hello from calculation thread "+ Thread.currentThread().getName());
        }

}

package com.batiaev.java3.lesson7.dz_lesson7;
import com.batiaev.java3.lesson7.dz_lesson7.Test_ClassBase.beforeTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Testing_Demo {

    public static void main(String[] args) {
        start(Test_Class1.class);
    }

    public static void  start(Class testClass){

        //получаем методы класса
        Method[] methods = testClass.getDeclaredMethods();
        //перебираем методы
        for (Method m: methods){
            //если аннотация метода ==beforeTest
            if (m.getAnnotation(beforeTest.class)!=null){
                //System.out.println("!=null ");
                try {
                    //запускаем данный метод
                    m.invoke(testClass.getConstructor().newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

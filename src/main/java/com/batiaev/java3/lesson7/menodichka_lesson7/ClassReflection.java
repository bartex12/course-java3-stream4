package com.batiaev.java3.lesson7.menodichka_lesson7;

import java.lang.reflect.Modifier;

public class ClassReflection {
    public static void main(String[] args) {


        // *********как получить класс*****************
        // 1)вызвать метод getClass(), который вернет объект типа Class.
        Class cla1 = "Java".getClass();
        System.out.println("1) Полное имя класса: "+cla1.getName());
        // 2) Запросить объект типа Class напрямую у класса.
        Class cla2 = Integer.class;
        System.out.println("2) Полное имя класса: "+cla2.getName());
        // 3) Вызвать статический метод Class.forName(), и передать ему
        // полное имя класса в качестве аргумента.
        try {
            Class cla3 = Class.forName("java.lang.String");
            System.out.println("3) Полное имя класса: "+cla3.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //1) Полное имя класса: java.lang.String
        //2) Полное имя класса: java.lang.Integer
        //3) Полное имя класса: java.lang.String

        //***************  имя класса   ***************
        Class s = String.class;
        System.out.println("Полное имя класса: " + s.getName());
        System.out.println("Простое имя класса: " + s.getSimpleName());
//        Полное имя класса: java.lang.String
//        Простое имя класса: String

        //********** модификаторы класса *****************
        int modifiers = s.getModifiers();
        if (Modifier.isAbstract(modifiers)){
            System.out.println(s.getSimpleName()+ " - abstract");
        }
        if (Modifier.isPublic(modifiers)){
            System.out.println(s.getSimpleName()+ " - public");
        }
        if (Modifier.isFinal(modifiers)){
            System.out.println(s.getSimpleName()+ " - final");
        }
        //String - public
        //String - final

        //*********** Суперклассы ***********************
        Class ss = s.getSuperclass();
        System.out.println("Суперкласс - " + s +  " = " + ss);
        Class sss = ss.getSuperclass();
        System.out.println("Суперкласс - " + ss + " = " + sss);
        //Суперкласс - class java.lang.String = class java.lang.Object
        //Суперкласс - class java.lang.Object = null

        //********* интерфейсы, реализованные классами**************
        Class[] cls = s.getInterfaces();
        for (Class cl: cls){
            System.out.println(cl);
        }
        //interface java.io.Serializable
        //interface java.lang.Comparable
        //interface java.lang.CharSequence


    }
}

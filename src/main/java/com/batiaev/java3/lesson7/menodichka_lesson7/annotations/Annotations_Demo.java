package com.batiaev.java3.lesson7.menodichka_lesson7.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//Получить аннотации полей, методов или классов можно с помощью методов getAnnotations()
// и getDeclaredAnnotations() у соответствующего класса – Field, Method, Class.
// Если известно имя нужной аннотации – применяем getAnnotation() и getDeclaredAnnotation().
// Эти методы возвращают объекты типа Annotation.

public class Annotations_Demo {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface MarkingAnnotation {
    }

    public static void main(String[] args) {
        Method[] method = MyClass.class.getDeclaredMethods();
        //Пример вывода в консоль списка методов с аннотациями @MarkingAnnotation.
        for (Method m: method){
            if (m.getAnnotation(MarkingAnnotation.class)!= null){
                System.out.println(m);
            }
        }
        //public void com.batiaev.java3.lesson7.menodichka_lesson7.annotations.MyClass.MarkedMethod()


        //просто повторение материала предыдущих разделов
        try {
            MyClass myClass = new MyClass();
            //через рефлексию вызов
            Method method1 = MyClass.class.getMethod("MarkedMethod");
            method1.invoke(myClass);
            //обычный вызов
            myClass.MarkedMethod();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //Java
        //Java
    }
}

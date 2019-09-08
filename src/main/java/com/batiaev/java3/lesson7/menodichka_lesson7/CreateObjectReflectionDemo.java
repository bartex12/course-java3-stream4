package com.batiaev.java3.lesson7.menodichka_lesson7;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

//Метод newInstance() позволяет создавать экземпляры класса через объект типа Class
// и возвращает объект типа Object. Если этот метод вызван у объекта типа Class,
// то для создания нового объекта используется конструктор по умолчанию.
// Если он отсутствует – будет брошено исключение. Вариант1
// Если вначале получаем объект типа Constructor с заданным набором параметров,
// то newInstance() использует этот набор. Вариант 2
public class CreateObjectReflectionDemo {
    public static void main(String[] args) {

        // Вариант 1 конструктор по умолчанию отсутствует - выбрасывается исключение InstantiationException
       Class someClass  = CatConstructor.class;
        try {
            CatConstructor catConstructor1 = (CatConstructor)someClass.newInstance();
            Field f = CatConstructor.class.getField("name");
            System.out.println( catConstructor1.getClass().getName() + " имя поля = " +f.getName() + " тип поля = " + f.getType());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //java.lang.InstantiationException: com.batiaev.java3.lesson7.menodichka_lesson7.CatConstructor
        //	at java.lang.Class.newInstance(Class.java:427)
        //	at com.batiaev.java3.lesson7.menodichka_lesson7.CreateObjectReflectionDemo.main(CreateObjectReflectionDemo.java:19)
        //Caused by: java.lang.NoSuchMethodException: com.batiaev.java3.lesson7.menodichka_lesson7.CatConstructor.<init>()
        //	at java.lang.Class.getConstructor0(Class.java:3082)
        //	at java.lang.Class.newInstance(Class.java:412)
        //	... 1 more

        //Вариант2
        // Если вначале получаем объект типа Constructor с заданным набором параметров,
        // то newInstance() использует этот набор.
        try {
            Constructor constructor = CatConstructor.class.getConstructor(String.class, String.class, int.class);
            CatConstructor catConstructor2 = (CatConstructor)constructor.newInstance("Барсик", "Рыжий", 5);
            Field ff = CatConstructor.class.getDeclaredField("name");
            //Field ff = CatConstructor.class.getField("name");
            System.out.println( " имя_класса = " + catConstructor2.getClass().getName() + " имя_поля = " +ff.getName() + " тип_поля = " + ff.getType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //имя_класса = com.batiaev.java3.lesson7.menodichka_lesson7.CatConstructor имя_поля = name тип_поля = class java.lang.String

    }
}

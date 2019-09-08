package com.batiaev.java3.lesson7.menodichka_lesson7.classReflection;

import java.lang.reflect.Field;

//Посредством рефлексии можно получать и изменять значения полей с модификатором доступа private.
//Для этого получаем объект типа Field и открываем к нему доступ через setAccessible(true).
// Затем получаем и изменяем его значение – по аналогии с предыдущим примером.
// Изменить final поле нельзя даже при помощи рефлексии.

public class PrivateFieldsClassReflection_Demo {
    public static void main(String[] args) {

        try {
            ClassWithPrivateField obj = new ClassWithPrivateField(10);
            obj.info();
            Field privateField = ClassWithPrivateField.class.getDeclaredField("field");
            privateField.setAccessible(true);
            System.out.println("get " + privateField.get(obj));
            privateField.set(obj, 1000);
            obj.info();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //field: 10
        //get 10
        //field: 1000
    }
}

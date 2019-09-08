package com.batiaev.java3.lesson7.menodichka_lesson7.classReflection;

import java.lang.reflect.Field;

public class FieldsClassReflection_Demo {
    public static void main(String[] args) {

        //************* тип поля и имя поля ***************
        Class catClass = Cat.class;
        Field[] publicFields = catClass.getFields();
        for (Field f: publicFields){
            System.out.println("Тип_поля Имя_поля: " + f.getType().getName() + " " + f.getName());
        }
        //Тип_поля Имя_поля: java.lang.String name
        //Тип_поля Имя_поля: java.lang.String color
        //Тип_поля Имя_поля: int age

        //********** получение ссылки на поле по имени ***************
        try {
            Field ff = catClass.getField("name"); // так
            //Field ff = catClass.getDeclaredField("name"); //или так
            System.out.println("Тип_поля: " + ff.getType().getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
            //Тип_поля: java.lang.String

        //************ установка и получение значения поля*************
        try {

            Cat cat = new Cat();
            Field fieldName = cat.getClass().getField("name");
            //устанавливаем для поля name значение Мурзик
            fieldName.set(cat, "Мурзик");
            System.out.println("Поле name = " + fieldName.get(cat));

            //выводим значение поля age - 0 по умолчанию
            Field field1Age = cat.getClass().getField("age");
            System.out.println("Поле age = " + field1Age.get(cat));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //Поле name = Мурзик
        //Поле age = 0
    }
}


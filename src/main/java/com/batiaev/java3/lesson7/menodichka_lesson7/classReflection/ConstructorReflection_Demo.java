package com.batiaev.java3.lesson7.menodichka_lesson7.classReflection;

//Методы getConstructors() и getDeclaredConstructors() возвращают массив объектов типа Constructor.
// Они содержат в себе информацию о конструкторах класса: имя, модификаторы, типы параметров,
// генерируемые исключения. Если известен набор параметров конструктора, можно получить ссылку на него
// с помощью getConstructor() или getDeclaredConstructor().

import java.lang.reflect.Constructor;

public class ConstructorReflection_Demo {
    public static void main(String[] args) {
        Constructor[] constructors = CatConstructor.class.getConstructors();
        for (Constructor c: constructors){
            System.out.println(c);
        }
        System.out.println("-----------");
        try {
            System.out.println(CatConstructor.class.getConstructor(new Class[]{String.class, int.class}));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
//public com.batiaev.java3.lesson7.menodichka_lesson7.classReflection.CatConstructor(java.lang.String,java.lang.String,int)
//public com.batiaev.java3.lesson7.menodichka_lesson7.classReflection.CatConstructor(java.lang.String,int)
//public com.batiaev.java3.lesson7.menodichka_lesson7.classReflection.CatConstructor(java.lang.String)
//-----------
//public com.batiaev.java3.lesson7.menodichka_lesson7.classReflection.CatConstructor(java.lang.String,int)
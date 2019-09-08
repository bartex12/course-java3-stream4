package com.batiaev.java3.lesson7.dz_lesson7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Test_ClassBase implements AfterTesting, BeforeTesting {

    public static Object beforeTest;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface beforeTest{
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface afterTest{
    }

    @afterTest
    @Override
    public void after() {
        System.out.println("Действия после тестирования");
    }

    @beforeTest
    @Override
    public void befor() {
        System.out.println("Подготовка к тестированию");
    }
}

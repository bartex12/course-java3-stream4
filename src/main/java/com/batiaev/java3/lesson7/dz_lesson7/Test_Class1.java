package com.batiaev.java3.lesson7.dz_lesson7;

import com.batiaev.java3.lesson7.dz_lesson7.Test_ClassBase.afterTest;
import com.batiaev.java3.lesson7.dz_lesson7.Test_ClassBase.beforeTest;
import org.junit.Test;

public class Test_Class1 implements BeforeTesting, AfterTesting {

    public Test_Class1(){
        System.out.println("Конструктор класса Test_Class1");
    }

    @Test
    public void test1_class1() {
        System.out.println("test1_class1");
    }
        @Test
        public void test2_class1(){
            System.out.println("test1_class1");
        }

    @afterTest
    @Override
    public void after() {
        System.out.println("Действия после тестирования в классе Test_Class1");
    }

    @beforeTest
    @Override
    public void befor() {
        System.out.println("Подготовка к тестированию в классе Test_Class1");
    }
}


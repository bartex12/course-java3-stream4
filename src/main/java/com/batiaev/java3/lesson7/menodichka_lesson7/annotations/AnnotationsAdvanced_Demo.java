package com.batiaev.java3.lesson7.menodichka_lesson7.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class AnnotationsAdvanced_Demo {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AdvancedAnnotation{
        float value() default 5.0f;
    }

    //получаем все методы с аннотациями AdvancedAnnotation и выводим value
    public static void main(String[] args) {
        Method[] method1 = MyClassAdvanced.class.getDeclaredMethods();
        for (Method m: method1){
            if (m.getAnnotation(AdvancedAnnotation.class)!=null){
                AdvancedAnnotation annotation = m.getAnnotation(AdvancedAnnotation.class);
                System.out.println("value = " + annotation.value());
            }
        }
//        value = 30.0
//        value = 20.0

        //по известному имени метода получаем аннотацию и выводим value
        try {
            Method method2 = MyClassAdvanced.class.getDeclaredMethod("advancedAnnotateMethod");
            AdvancedAnnotation annotation = method2.getAnnotation(AdvancedAnnotation.class);
            System.out.println(" value = " + annotation.value());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // value = 20.0
    }
}

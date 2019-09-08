package com.batiaev.java3.lesson7.menodichka_lesson7.annotations;


import com.batiaev.java3.lesson7.menodichka_lesson7.annotations.AnnotationsAdvanced_Demo.AdvancedAnnotation;

public class MyClassAdvanced {

    @AdvancedAnnotation(value = 20.0f)
    public void advancedAnnotateMethod(){
        System.out.println("advancedAnnotateMethod");
    }

    @AdvancedAnnotation(value = 30.0f)
    public void advancedAnnotateMethod2(){
        System.out.println("advancedAnnotateMethod2");
    }
}

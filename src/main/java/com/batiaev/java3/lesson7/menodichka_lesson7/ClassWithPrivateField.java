package com.batiaev.java3.lesson7.menodichka_lesson7;

//класс с приватным полем для использования в классе PrivateFieldsClassReflection_Demo
public class ClassWithPrivateField {
    private int field;
    public ClassWithPrivateField(int field) { this.field = field;
    }
    public void info() {
        System.out.println("field: " + field);
    }
}

package com.batiaev.java3.lesson7.menodichka_lesson7;

public class CatConstructor {
    private String name;
    private String color;
    private int age;

    public CatConstructor(String name, String color, int age) { this.name = name;
        this.color = color;
        this.age = age;
    }

    public CatConstructor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public CatConstructor(String name) {
        this.name = name;
    }

    public void jamp(){
        System.out.println("jamp");
    }

    public void meow(int db){
        System.out.println(name + ": meow - " + db + " dB");
    }

}

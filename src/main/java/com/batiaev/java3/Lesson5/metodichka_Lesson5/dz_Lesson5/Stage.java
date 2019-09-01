package com.batiaev.java3.Lesson5.metodichka_Lesson5.dz_Lesson5;

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}

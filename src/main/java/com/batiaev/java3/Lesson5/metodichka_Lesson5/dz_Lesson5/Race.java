package com.batiaev.java3.Lesson5.metodichka_Lesson5.dz_Lesson5;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {

    private ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }
        public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}

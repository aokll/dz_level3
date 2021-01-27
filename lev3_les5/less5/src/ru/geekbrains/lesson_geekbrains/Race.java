package ru.geekbrains.lesson_geekbrains;

import java.util.ArrayList;
import java.util.Arrays;

public class Race extends Main{
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}

package com.ww.algorithm.Array;

public class Student {
    private String name;
    private Double score;

    public Student(String name, Double score){
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString(){
        return String.format("【Student】name=%s, score=%f", name, score);
    }

}

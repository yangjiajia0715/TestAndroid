package com.example;

import java.io.Serializable;

/**
 * Created by yangjiajia on 2017/8/10.
 */

public class StudentInfo implements Serializable {
    private float score;
    private String name;

    public StudentInfo(float score, String name) {
        this.score = score;
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}

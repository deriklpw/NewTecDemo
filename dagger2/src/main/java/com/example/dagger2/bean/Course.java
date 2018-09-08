package com.example.dagger2.bean;

/**
 * Created by derik on 18-8-27
 * <p>
 * Email: weilai0314@163.com
 */
public class Course {
    private String courseName;
    private float score;

    public Course(){

    }

    public Course(String courseName){
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}

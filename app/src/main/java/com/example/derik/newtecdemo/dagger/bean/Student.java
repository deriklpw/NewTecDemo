package com.example.derik.newtecdemo.dagger.bean;

import java.util.List;

/**
 * Created by derik on 18-8-27
 * <p>
 * Email: weilai0314@163.com
 */
public class Student {
    private String name;
    private String gender;

    private List<Course> courses;


    public Student(){

    }

    public Student(String name) {
        this.name = name;
    }


    public Student(List<Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

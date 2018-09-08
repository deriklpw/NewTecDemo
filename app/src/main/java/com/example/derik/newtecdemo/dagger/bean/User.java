package com.example.derik.newtecdemo.dagger.bean;

import javax.inject.Inject;

/**
 * Created by derik on 18-9-6
 * <p>
 * Email: weilai0314@163.com
 */
public class User {
    private String name;
    private String gender;
    private int age;

    @Inject
    public User() {
        name = "user1";
        gender = "man";
        age = 18;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

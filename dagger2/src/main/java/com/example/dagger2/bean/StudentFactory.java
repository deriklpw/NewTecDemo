package com.example.dagger2.bean;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by derik on 18-9-6
 * <p>
 * Email: weilai0314@163.com
 */
public class StudentFactory {

   private final Student student;

    @Inject
    public StudentFactory(@Named("courses") Student student) {
        this.student = student;

    }

    public Student getStudent() {
        return student;
    }
}

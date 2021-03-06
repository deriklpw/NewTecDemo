package com.example.dagger2.module;

import com.example.dagger2.bean.Course;
import com.example.dagger2.bean.Student;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by derik on 18-8-27
 * <p>
 * Email: weilai0314@163.com
 */
@Module
public class StudentModule {

    @Provides
    Student provideStudent(){
        return new Student();
    }

    @Provides
    @Named("name")
    Student provideStudentWithName() {
        return new Student("student1");
    }

    @Provides
    @Named("courses")
    Student provideStudentWithCourses(@Named("name") Course chinese, @Named("name") Course math, @Named("name") Course english) {
        List<Course> list = new ArrayList<>();
        list.add(chinese);
        list.add(math);
        list.add(english);
        return new Student(list);
    }
}

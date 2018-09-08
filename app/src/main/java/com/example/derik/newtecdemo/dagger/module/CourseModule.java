package com.example.derik.newtecdemo.dagger.module;

import com.example.derik.newtecdemo.dagger.bean.Course;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by derik on 18-8-27
 * <p>
 * Email: weilai0314@163.com
 */

@Module
public class CourseModule {

    @Provides
    Course provideCourse() {
        return new Course();
    }

    @Provides
    @Named("name")
    Course provideCourseWithCourse() {
        return new Course("chinese");
    }
}

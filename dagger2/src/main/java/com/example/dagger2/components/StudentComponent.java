package com.example.dagger2.components;

import com.example.dagger2.MainActivity;
import com.example.dagger2.bean.StudentFactory;
import com.example.dagger2.module.CourseModule;
import com.example.dagger2.module.StudentModule;

import dagger.Component;

/**
 * Created by derik on 18-8-27
 * <p>
 * Email: weilai0314@163.com
 */
@Component(modules = {StudentModule.class, CourseModule.class})
public interface StudentComponent {
    void inject(MainActivity mainActivity);
    StudentFactory maker();
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        Builder courseModule(CourseModule courseModule);
//        StudentComponent build();
//    }

}

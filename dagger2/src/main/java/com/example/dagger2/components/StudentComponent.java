package com.example.dagger2.components;

import com.example.dagger2.MainActivity;
import com.example.dagger2.bean.StudentFactory;
import com.example.dagger2.module.CourseModule;
import com.example.dagger2.module.StudentModule;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by derik on 18-8-27
 * <p>
 * Email: weilai0314@163.com
 */
@Component(modules = {StudentModule.class, CourseModule.class})
public interface StudentComponent {
    StudentFactory maker();
    void inject(MainActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder courseModule(CourseModule courseModule);
        StudentComponent build();
    }

}

package com.example.derik.newtecdemo.dagger.components;

import com.example.derik.newtecdemo.activity.Dagger2Activity;
import com.example.derik.newtecdemo.dagger.bean.StudentFactory;
import com.example.derik.newtecdemo.dagger.module.CourseModule;
import com.example.derik.newtecdemo.dagger.module.StudentModule;

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
    void inject(Dagger2Activity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder courseModule(CourseModule courseModule);
        StudentComponent build();
    }

}

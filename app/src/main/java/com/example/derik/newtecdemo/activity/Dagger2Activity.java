package com.example.derik.newtecdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.derik.newtecdemo.R;
import com.example.derik.newtecdemo.adapter.ItemDividerHorizontal;
import com.example.derik.newtecdemo.adapter.RecycleViewAdapter;
import com.example.derik.newtecdemo.dagger.bean.Student;
import com.example.derik.newtecdemo.dagger.bean.StudentFactory;
import com.example.derik.newtecdemo.dagger.bean.User;
import com.example.derik.newtecdemo.dagger.components.DaggerStudentComponent;
import com.example.derik.newtecdemo.dagger.components.StudentComponent;
import com.example.derik.newtecdemo.dagger.module.CourseModule;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;
import javax.inject.Named;

public class Dagger2Activity extends BaseActivity {

    private String[] mTargetNames = new String[]{
            "inject",
    };

    private RecycleViewAdapter mAdapter;

    private StudentFactory mStudentFactory;
    private Student mStudent;

    @Inject
    @Named("name")
    Student mStudent1;

    @Inject
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_dagger2);
        setTitle("Dagger2使用示例");
        setBackArrow();
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        // 线性排列
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        // 设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDividerHorizontal().setDividerColor(Color.GRAY).setmDividerSize(2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RecycleViewAdapter(mTargetNames, null);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        StudentComponent studentComponent = DaggerStudentComponent.builder().courseModule(new CourseModule()).build();
                        studentComponent.inject(Dagger2Activity.this);
                        mStudentFactory = studentComponent.maker();
                        mStudent = mStudentFactory.getStudent();
                        mStudent.setName("derik");
                        mStudent.setGender("man");
                        mStudent.getCourses().get(1).setScore(12.0f);

                        Logger.d(mStudent.getName());
                        Logger.d(mStudent.getGender());
                        Logger.d(mStudent.getCourses().get(1).getCourseName());
                        Logger.d(mStudent.getCourses().get(1).getScore());
                        Logger.d(mStudent1.getName());
                        Logger.d(mUser.getName());
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

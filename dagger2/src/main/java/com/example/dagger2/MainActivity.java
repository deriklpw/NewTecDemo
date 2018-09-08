package com.example.dagger2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.common.activity.BaseActivity;
import com.example.common.adapter.ItemDividerHorizontal;
import com.example.common.adapter.RecycleViewAdapter;
import com.example.dagger2.bean.Student;
import com.example.dagger2.bean.StudentFactory;
import com.example.dagger2.bean.User;
import com.example.dagger2.components.DaggerStudentComponent;
import com.example.dagger2.components.StudentComponent;
import com.example.dagger2.module.CourseModule;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getName();
    private String[] targetNames = new String[]{
            "inject",
            "show"
    };

    private RecycleViewAdapter adapter;

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
        setContentLayout(R.layout.activity_main);

        // Toolbar 初始化设置
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
        recyclerView.addItemDecoration(new ItemDividerHorizontal().setDividerColor(Color.GRAY).setDividerSize(2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new RecycleViewAdapter(targetNames, null);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        StudentComponent studentComponent = DaggerStudentComponent.builder().courseModule(new CourseModule()).build();
                        studentComponent.inject(MainActivity.this);
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
                        showToolBar();
                        Logger.d(position);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

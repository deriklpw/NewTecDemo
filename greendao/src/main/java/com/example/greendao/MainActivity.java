package com.example.greendao;

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
import com.example.greendao.base.Course;
import com.example.greendao.base.Student;
import com.example.greendao.greendao.CourseDao;
import com.example.greendao.greendao.DaoSession;
import com.example.greendao.greendao.StudentDao;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.query.LazyList;

import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getName();
    private String[] mTargetNames = new String[]{
            "base",
            "base",
            "base",
            "base",
            "base",
            "base",
            "关联",
            "关联"
    };

    private String[] mTargetDescs = new String[]{
            "insert",
            "delete",
            "update",
            "query all",
            "query by key",
            "query by column name",
            "一对一",
            "一对多"
    };
    private StudentDao mStudentDao;
    private CourseDao mCourseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        setTitle("greenDao使用示例");
        setBackArrow();
        initViews();
        DaoSession daoSession = MyApplication.getInstance().getDaoSession();
        mStudentDao = daoSession.getStudentDao();
        mCourseDao = daoSession.getCourseDao();
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

        RecycleViewAdapter adapter = new RecycleViewAdapter(mTargetNames, mTargetDescs);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        Student student0 = new Student()
                                .setName("student0")
                                .setGender("man")
                                .setAge((short) 0)
                                .setData(new Date());
                        mStudentDao.insert(student0);
                        break;
                    case 1:
                        Student student1 = new Student()
                                .setName("student1")
                                .setGender("woman")
                                .setAge((short) 1)
                                .setData(new Date());
                        mStudentDao.insert(student1);

                        //通过Id Key进行删除
                        mStudentDao.deleteByKey(1L);

                        //通过对象进行删除，需给对象设置Id
//                        Student student1_1 = new Student()
//                                .setId(1L);
//                        mStudentDao.delete(student1_1);
                        break;
                    case 2:
                        Student student2 = new Student()
                                .setName("student2")
                                .setGender("man")
                                .setAge((short) 2)
                                .setData(new Date());
                        mStudentDao.insert(student2);

                        Student student2_2 = new Student()
                                .setId(1L)
                                .setGender("woman");
                        //mStudentDao.update(student2_2);
                        mStudentDao.insertOrReplace(student2_2);
                        break;
                    case 3:
                        Student student3 = new Student()
                                .setName("student3")
                                .setGender("man")
                                .setAge((short) 3)
                                .setData(new Date());
                        mStudentDao.insert(student3);

                        // List<Student> list = mStudentDao.loadAll();
                        // List<Student> list = mStudentDao.queryBuilder().build().list();
                        LazyList<Student> list = mStudentDao.queryBuilder().listLazy();
                        for (Student stu : list) {
                            Logger.d("id=" + stu.getId());
                        }
                        //需要关闭cursor，防止内存泄露
                        list.close();
                        break;
                    case 4:
                        Student student4 = new Student()
                                .setName("student4")
                                .setGender("man")
                                .setAge((short) 4)
                                .setData(new Date());
                        mStudentDao.insert(student4);

                        student4 = mStudentDao.queryBuilder().where(StudentDao.Properties.Id.eq(1)).build().unique();
                        if (student4 != null) {
                            Logger.d("equal, unique=" + student4.getId());
                        }
                        List<Student> list4 = mStudentDao.queryBuilder().where(StudentDao.Properties.Id.gt(1)).build().list();
                        if (list4 != null && list4.size() > 0) {
                            for (Student stu : list4) {
                                Logger.d("greater than 1, " + stu.getId() + "; age=" + stu.getAge());
                            }
                        }
                        break;
                    case 5:
                        Student student5 = new Student()
                                .setName("student5")
                                .setGender("man")
                                .setAge((short) 5)
                                .setData(new Date());
                        mStudentDao.insert(student5);

                        List<Student> list5 = mStudentDao.queryBuilder().where(StudentDao.Properties.Name.eq("student5")).build().list();
                        if (list5 != null && list5.size() > 0) {
                            for (Student stu : list5) {
                                Logger.d("id=" + stu.getId() + "; name=" + stu.getName());
                            }
                        }
                        break;
                    case 6:
                        try {
                            //因为固定了课程Id，所以需要卸载，重新安装，查看正确结果
                            Course course6 = new Course()
                                    .setName("english")
                                    .setTeacherName("teacher6")
                                    .setHours(100);
                            mCourseDao.insert(course6);


                            Student student6 = new Student()
                                    .setName("student6")
                                    .setGender("man")
                                    .setCid(1L);
                            mStudentDao.insert(student6);

                            List<Student> list6 = mStudentDao.queryBuilder().build().list();
                            if (list6 != null && list6.size() > 0) {
                                //所有学生的Course，只有此处添加的有课程Id
                                for (Student stu : list6) {
                                    if (stu.getId() == list6.size()) {
                                        //通过学生对象，可拿到course表，id为cid的课程信息。一个student对应一个course
                                        Logger.d("id=" + stu.getId() + "; course=" + stu.getCourse().getName());
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            //因为固定了课程Id，需要卸载，重新安装查看正确结果
                            Course course7_chinese = new Course()
                                    .setName("course7_chinese")
                                    .setTeacherName("chineseTeacher7");
                            Course course7_math = new Course()
                                    .setName("course7_math")
                                    .setTeacherName("mathTeacher7");
                            Course course7_english = new Course()
                                    .setName("course7_english")
                                    .setTeacherName("englishTeacher7");
                            mCourseDao.insertInTx(course7_chinese, course7_math, course7_english);

                            Student student7_1 = new Student()
                                    .setName("student7_1")
                                    .setGender("man")
                                    .setAge((short) 7)
                                    .setData(new Date())
                                    .setCid(1L);
                            Student student7_2 = new Student()
                                    .setName("student7_2")
                                    .setGender("man")
                                    .setAge((short) 7)
                                    .setData(new Date())
                                    .setCid(1L);

                            Student student7_3 = new Student()
                                    .setName("student7_3")
                                    .setGender("man")
                                    .setAge((short) 7)
                                    .setData(new Date())
                                    .setCid(2L);
                            mStudentDao.insertInTx(student7_1, student7_2, student7_3);

                            List<Course> list7 = mCourseDao.queryBuilder().build().list();
                            if (list7 != null && list7.size() > 0) {
                                Logger.d("size=" + list7.size());
                                for (Course course7 : list7) {
                                    Logger.d("course=" + course7.getName());
                                    //打印每个课程中的所有学生，即通过课程对象，可拿到student表中和自己id关联的所有学生信息。
                                    //一个Course对应多个student
                                    for (Student stu : course7.getStudents()) {
                                        Logger.d("id=" + stu.getId() + "; name=" + stu.getName());
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStudentDao.deleteAll();
        mCourseDao.deleteAll();
    }
}

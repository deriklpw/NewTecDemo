package com.example.greendao.base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

import org.greenrobot.greendao.DaoException;

import com.example.greendao.greendao.DaoSession;
import com.example.greendao.greendao.CourseDao;
import com.example.greendao.greendao.StudentDao;

/**
 * Created by derik on 18-9-12.
 */
@Entity(
        //表的名字
        nameInDb = "student"
)
public class Student {
    @Id(autoincrement = true)
    //自增，必须是Long类型，long会报异常
    private Long id;
    @Property(nameInDb = "studentName")
    private String name;
    private String gender;
    private short age;
    private String address;
    private Date data;

    //一对一关联，通过cid外键（表示课程的id），进行对象关联
    private Long cid;
    @ToOne(joinProperty = "cid")
    private Course course;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;

    @Generated(hash = 375498010)
    public Student(Long id, String name, String gender, short age, String address,
                   Date data, Long cid) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.data = data;
        this.cid = cid;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return this.gender;
    }

    public Student setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public short getAge() {
        return this.age;
    }

    public Student setAge(short age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public Student setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getData() {
        return this.data;
    }

    public Student setData(Date data) {
        this.data = data;
        return this;
    }

    public Long getCid() {
        return this.cid;
    }

    public Student setCid(Long cid) {
        this.cid = cid;
        return this;
    }

    @Generated(hash = 13676306)
    private transient Long course__resolvedKey;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1188271233)
    public Course getCourse() {
        Long __key = this.cid;
        if (course__resolvedKey == null || !course__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CourseDao targetDao = daoSession.getCourseDao();
            Course courseNew = targetDao.load(__key);
            synchronized (this) {
                course = courseNew;
                course__resolvedKey = __key;
            }
        }
        return course;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1665690238)
    public void setCourse(Course course) {
        synchronized (this) {
            this.course = course;
            cid = course == null ? null : course.getId();
            course__resolvedKey = cid;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }

}

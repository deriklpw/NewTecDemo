package com.example.greendao.base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.example.greendao.greendao.DaoSession;
import com.example.greendao.greendao.StudentDao;
import com.example.greendao.greendao.CourseDao;

/**
 * Created by derik on 18-9-13.
 */

@Entity(
        nameInDb = "course"
)
public class Course {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String teacherName;
    private int hours;
    private int score;

    @ToMany(referencedJoinProperty = "cid")
    private List<Student> students;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2063667503)
    private transient CourseDao myDao;

    @Generated(hash = 609719190)
    public Course(Long id, String name, String teacherName, int hours, int score) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
        this.hours = hours;
        this.score = score;
    }

    @Generated(hash = 1355838961)
    public Course() {
    }

    public Long getId() {
        return this.id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public Course setTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public int getHours() {
        return this.hours;
    }

    public Course setHours(int hours) {
        this.hours = hours;
        return this;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1555632920)
    public List<Student> getStudents() {
        if (students == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            List<Student> studentsNew = targetDao._queryCourse_Students(id);
            synchronized (this) {
                if (students == null) {
                    students = studentsNew;
                }
            }
        }
        return students;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 238993120)
    public synchronized void resetStudents() {
        students = null;
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 94420068)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCourseDao() : null;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
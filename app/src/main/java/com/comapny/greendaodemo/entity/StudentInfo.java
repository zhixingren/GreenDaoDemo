package com.comapny.greendaodemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Note: only Java classes are supported.
 * If you prefer another language like Kotlin, your entity classes must still be Java.
 * 必须是java类
 */
@Entity(
       // schema = "student_info1",//如果您有多个模式，可以告诉greenDAO,实体属于哪个模式(选择任意字符串作为名称)。
        nameInDb = "student_info"//表名称
)
public class StudentInfo {

    @Id(autoincrement = true)//设置自增长
    private Long id;

    @Index(unique = true)//设置唯一性
    private String perNo;//人员编号

    @Property(nameInDb = "studentName")//对应数据库，字段名称
    private String name;//人员姓名

    @Property(nameInDb = "updated_time")//更新时间
    private String updateTime;

    private int sex ;

    @Generated(hash = 1235426908)
    public StudentInfo(Long id, String perNo, String name, String updateTime, int sex) {
        this.id = id;
        this.perNo = perNo;
        this.name = name;
        this.updateTime = updateTime;
        this.sex = sex;
    }

    @Generated(hash = 2016856731)
    public StudentInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerNo() {
        return this.perNo;
    }

    public void setPerNo(String perNo) {
        this.perNo = perNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

}

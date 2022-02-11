package com.cao.score.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息表(Students)实体类
 *
 * @author makejava
 * @since 2022-01-28 10:35:52
 */
public class Students implements Serializable {
    private static final long serialVersionUID = -94713290740468455L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 学号
     */
    private String studentId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 出生日期
     */
    private Date birthdate;
    /**
     * 身份证号
     */
    private Integer identityNum;
    /**
     * 家庭住址
     */
    private String address;
    /**
     * 年级
     */
    private Integer gradeNum;
    /**
     * 班级
     */
    private Integer classNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(Integer identityNum) {
        this.identityNum = identityNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

}
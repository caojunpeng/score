package com.cao.score.entity;

import java.io.Serializable;

/**
 * 教师信息表(Teachers)实体类
 *
 * @author makejava
 * @since 2022-01-28 10:36:02
 */
public class Teachers implements Serializable {
    private static final long serialVersionUID = 528711604747937850L;
    /**
     * 主键id
     */
    private Long id;
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
     * 身份证号
     */
    private Integer identityNum;
    /**
     * 任职科目：见字典type_id=2
     */
    private Integer subject;
    /**
     * 联系方式
     */
    private String phone;
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

    public Integer getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(Integer identityNum) {
        this.identityNum = identityNum;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
package com.lt.domain;

/**
 * 领域对象
 * 与表单和数据表对应
 */
public class Customer {
    /**
     * 对应数据库表
     * cid		CHAR(32) PRIMARY KEY,
     * 	cname		VARCHAR(40) NOT NULL,
     * 	gender		VARCHAR(6) NOT NULL,
     * 	birthday	CHAR(10),
     * 	cellphone	VARCHAR(15) NOT NULL,
     * 	email		VARCHAR(40),
     * 	description	VARCHAR(500)
     */
    private String cid;//主键
    private String cname;//客户名称
    private String gender;//客户性别
    private String birthday;//客户生日
    private String cellphone;//客户手机
    private String email;//客户邮箱
    private String description;//客户描述

    public Customer() {
    }

    public Customer(String cid, String cname, String gender, String birthday, String cellphone, String email, String description) {
        this.cid = cid;
        this.cname = cname;
        this.gender = gender;
        this.birthday = birthday;
        this.cellphone = cellphone;
        this.email = email;
        this.description = description;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

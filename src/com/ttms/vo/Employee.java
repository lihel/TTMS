package com.ttms.vo;

import java.io.Serializable;

public class Employee implements Serializable{
    private Integer emp_id;
    private String emp_no;
    private String emp_name;
    private String emp_sex;
    private String emp_tel_num;
    private String emp_addr;
    private String emp_email;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_tel_num() {
        return emp_tel_num;
    }

    public void setEmp_tel_num(String emp_tel_num) {
        this.emp_tel_num = emp_tel_num;
    }

    public String getEmp_addr() {
        return emp_addr;
    }

    public void setEmp_addr(String emp_addr) {
        this.emp_addr = emp_addr;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }
    public String getEmp_sex() {
        return emp_sex;
    }

    public void setEmp_sex(String emp_sex) {
        this.emp_sex = emp_sex;
    }
}

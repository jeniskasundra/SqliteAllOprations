package com.jeniskasundra.sqlitealloprations.model;

/**
 * Created by Qtonz on 2/8/2018.
 */

public class Student {

    int id;
    String name,gender,address,mobile,email;

    public Student(int id, String name, String gender, String address, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

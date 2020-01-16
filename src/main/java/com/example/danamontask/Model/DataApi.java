package com.example.danamontask.Model;

public class DataApi {

    String id;
    String username;
    String salary;
    String age;

    public DataApi(String id, String username, String salary, String age) {
        this.id = id;
        this.username = username;
        this.salary = salary;
        this.age = age;
    }

    public DataApi(){



    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSalary() {
        return salary;
    }

    public String getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

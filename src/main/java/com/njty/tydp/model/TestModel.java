package com.njty.tydp.model;

import com.njty.tydp.commom.BaseModel;

public class TestModel extends BaseModel{

    public TestModel(String name) {
        this.name = name;
    }

    public TestModel(int id, String name, String memo) {
        this.id = id;
        this.name = name;
        this.memo = memo;
    }

    private int id;
    private String name;
    private String memo;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}

package com.tarenwang.data_storage;

/**
 * Created by zhangYan on 2017/9/18.
 */

public class BlackNumber {

    public BlackNumber() {

    }

    public BlackNumber(int id, String number) {
        this.id = id;
        this.number = number;
    }
    private int id;
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BlackNumber{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}

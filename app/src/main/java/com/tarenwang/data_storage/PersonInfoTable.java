package com.tarenwang.data_storage;

/**
 * Created by zhangYan on 2017/9/15.
 */

public class PersonInfoTable {

    public static final String TAB_NAME = "tab_person_one";

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    public static final String CREATE_TABLE = "create table " + TAB_NAME + "("
            + _ID + " integer primary Key autoincrement ,"
            + NAME + " text,"
            + AGE + " text" +
            ")";
}

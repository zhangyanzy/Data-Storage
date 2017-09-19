package com.tarenwang.data_storage;

/**
 * Created by zhangYan on 2017/9/18.
 */

public class BlackNumberTable {

    public static final String TAB_NAME = "tab_black_number";

    public static final String _ID = "_id";
    public static final String NUMBER = "number";

    public static final String CREATE_TABLE = "create table " + TAB_NAME + "("
            + _ID + " integer primary Key autoincrement ,"
            + NUMBER + " text "
            + ")";

}

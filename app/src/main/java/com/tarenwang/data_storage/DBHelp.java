package com.tarenwang.data_storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zhangYan on 2017/9/15.
 */

public class DBHelp extends SQLiteOpenHelper {

    public DBHelp(Context context, int version) {
        super(context, "zhangyan.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PersonInfoTable.CREATE_TABLE);
        //插入一写初始值
        String sql = "insert into tab_person_one (name,age) values ('zhangyan','24')";
        db.execSQL(sql);
        Log.i("sql", PersonInfoTable.TAB_NAME + "创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

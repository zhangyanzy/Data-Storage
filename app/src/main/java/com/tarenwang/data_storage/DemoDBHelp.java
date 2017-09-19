package com.tarenwang.data_storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zhangYan on 2017/9/18.
 */

public class DemoDBHelp extends SQLiteOpenHelper {

    public DemoDBHelp(Context context) {
        super(context, "Demo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BlackNumberTable.CREATE_TABLE);
        Log.i("sqlDemo", "创建数据库: " + BlackNumberTable.TAB_NAME + "成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

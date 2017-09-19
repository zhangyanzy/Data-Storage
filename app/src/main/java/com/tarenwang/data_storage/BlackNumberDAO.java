package com.tarenwang.data_storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangYan on 2017/9/18.
 * BlackNumber表的操作类
 */

public class BlackNumberDAO {

    private DemoDBHelp mDemoDBHelp;
    private SQLiteDatabase mDatabase;

    public BlackNumberDAO(Context mContext) {
        mDemoDBHelp = new DemoDBHelp(mContext);
    }

    public void add(BlackNumber blackNumber) {
        mDatabase = mDemoDBHelp.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", blackNumber.getNumber());
        long id = mDatabase.insert(BlackNumberTable.TAB_NAME, null, values);
        Log.i("sqlDemo", "添加数据所返回的id值为 = " + id);

        blackNumber.setId((int) id);
        mDatabase.close();
    }

    public void deleteById(int id) {
        mDatabase = mDemoDBHelp.getReadableDatabase();
        int deleteCount = mDatabase.delete(BlackNumberTable.TAB_NAME, "_id = ?", new String[]{"id"});
        Log.i("sqlDemo", "删除数据所返回的id值为 = " + deleteCount);
        mDatabase.close();
    }

    public void update(BlackNumber blackNumber) {
        mDatabase = mDemoDBHelp.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", blackNumber.getNumber());
        int updateCount = mDatabase.update(BlackNumberTable.TAB_NAME, values, "_id = " + blackNumber.getId(), null);
        Log.i("sqlDemo", "更新数据所返回的id值为 = " + updateCount);
        mDatabase.close();

    }

    public List<BlackNumber> getAll() {
        List<BlackNumber> list = new ArrayList<>();
        mDatabase = mDemoDBHelp.getReadableDatabase();
        Cursor cursor = mDatabase.query(BlackNumberTable.TAB_NAME, null, null, null, null, null, "_id desc");
        //从cursor中把数据出来封装到集和中
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String number = cursor.getString(1);
            list.add(new BlackNumber(id, number));
        }
        cursor.close();
        mDatabase.close();
        return list;
    }


}

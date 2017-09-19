package com.tarenwang.data_storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tarenwang.data_storage.databinding.ActivitySqliteBinding;

public class SQLiteActivity extends AppCompatActivity {

    private ActivitySqliteBinding binding;

    private DBHelp dbHelp;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sqlite);
        binding.setPresenter(new Presenter());
    }

    public class Presenter {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.create_db:
                    dbHelp = new DBHelp(getApplicationContext(), 1);
                    database = dbHelp.getReadableDatabase();
                    Toast.makeText(getApplicationContext(), "创建数据库", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.update_db:
                    dbHelp = new DBHelp(getApplicationContext(), 2);
                    database = dbHelp.getReadableDatabase();
                    Toast.makeText(getApplicationContext(), "更新数据库", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.insert:
                    dbHelp = new DBHelp(getApplicationContext(), 2);
                    database = dbHelp.getReadableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("name", "Tom");
                    values.put("age", "22");
                    values.put("name", "Android");
                    values.put("age", "13");
                    long id = database.insert(PersonInfoTable.TAB_NAME, null, values);
                    database.close();
                    Toast.makeText(getApplicationContext(), "id=" + id, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.update:
                    dbHelp = new DBHelp(getApplicationContext(), 2);
                    database = dbHelp.getReadableDatabase();
                    //更新数据库
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", "Jack");
                    contentValues.put("age", "13");
                    int updateCount = database.update(PersonInfoTable.TAB_NAME, contentValues, "_id = ?", new String[]{"1"});
                    database.close();
                    Log.i("updateCount", "updateCount: = " + updateCount);
                    break;
                case R.id.delete:
                    dbHelp = new DBHelp(getApplicationContext(), 2);
                    database = dbHelp.getReadableDatabase();
                    //删除数据
                    int deleteCount = database.delete(PersonInfoTable.TAB_NAME, "_id = ?", new String[]{"2"});
                    Log.i("deleteCount", "deleteCount:= " + deleteCount);
                    break;
                case R.id.query:
                    dbHelp = new DBHelp(getApplicationContext(), 2);
                    database = dbHelp.getReadableDatabase();
                    //查询
                    //Cursor cursor = database.query(PersonInfoTable.TAB_NAME, null, null, null, null, null, null);
                    Cursor cursor = database.query(PersonInfoTable.TAB_NAME, null, "_id = ?", new String[]{"1"}, null, null, null);
                    //得到匹配的总记录数
                    int count = cursor.getCount();
                    //取出Cursor中的数据
                    while (cursor.moveToNext()) {
                        int select_id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String age = cursor.getString(2);
                        Log.i("query", "select_id: =" + select_id + "name = " + name + "age = " + age);
                        Log.i("query", "count: = " + count);
                    }
                    cursor.close();
                    database.close();
                    break;
                case R.id.transaction_text:
                    Toast.makeText(getApplicationContext(), "......", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}

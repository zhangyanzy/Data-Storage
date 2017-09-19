package com.tarenwang.data_storage.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.tarenwang.data_storage.BlackNumber;
import com.tarenwang.data_storage.BlackNumberDAO;
import com.tarenwang.data_storage.R;
import com.tarenwang.data_storage.databinding.ActivitySqliteDemoBinding;

import java.util.List;

public class SQLiteDemoActivity extends AppCompatActivity {

    private ActivitySqliteDemoBinding binding;
    private BlackNumberAdapter adapter;
    private List<BlackNumber> mList;
    private BlackNumberDAO mDao;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sqlite_demo);
        adapter = new BlackNumberAdapter();
        mDao = new BlackNumberDAO(this);
        mList = mDao.getAll();
        binding.list.setAdapter(adapter);
        binding.setPresenter(new Presenter());
        binding.list.setOnCreateContextMenuListener(this);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "更新");
        menu.add(0, 2, 0, "删除");
        //得到listView长按的position
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = info.position;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //得到对应的blackNumber对象
        BlackNumber blackNumber = mList.get(position);
        switch (item.getItemId()) {
            case 1:
                showUpdateDialog(blackNumber);
                break;
            case 2:
                mDao.deleteById(blackNumber.getId());
                mList.remove(position);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(final BlackNumber blackNumber) {
        final EditText editText = new EditText(SQLiteDemoActivity.this);
        editText.setHint(blackNumber.getNumber());
        new AlertDialog.Builder(SQLiteDemoActivity.this)
                .setTitle("更新")
                .setView(editText)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newNumber = editText.getText().toString();
                        blackNumber.setNumber(newNumber);
                        mDao.update(blackNumber);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }


    public class Presenter {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.add_main:
                    final EditText editText = new EditText(SQLiteDemoActivity.this);
                    editText.setHint("输入黑名单号");
                    new AlertDialog.Builder(SQLiteDemoActivity.this)
                            .setTitle("添加黑名单")
                            .setView(editText)
                            .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String number = editText.getText().toString();
                                    BlackNumber blackNumber = new BlackNumber(-1, number);
                                    mDao.add(blackNumber);
                                    mList.add(0, blackNumber);
                                    adapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
            }
        }
    }


    class BlackNumberAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(SQLiteDemoActivity.this, android.R.layout.simple_list_item_1, null);
            }
            BlackNumber blackNumber = mList.get(position);
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(blackNumber.getNumber());
            return convertView;
        }
    }
}

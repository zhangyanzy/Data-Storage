package com.tarenwang.data_storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tarenwang.data_storage.databinding.ActivitySpBinding;

public class SpActivity extends AppCompatActivity {

    private ActivitySpBinding binding;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sp);
        binding.setPresenter(new Presenter());

        init();
    }

    private void init() {
        //1.得到SP对象
        sp = getSharedPreferences("zhangyan", Context.MODE_PRIVATE);
    }

    public class Presenter {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.save_sp:
                    save();
                    break;
                case R.id.read_sp:
                    read();
                    break;
                default:
                    break;
            }
        }
    }

    private void save() {
        //2.得到editor对象
        SharedPreferences.Editor edit = sp.edit();
        //3.得到key/value
        String key = binding.etSpKey.getText().toString();
        String value = binding.etSpValue.getText().toString();
        //4.使用editor对象保存key/value
        edit.putString(key, value).commit();
        //5.提示
        Toast.makeText(getApplicationContext(), "保存完成", Toast.LENGTH_SHORT).show();
    }

    private void read() {
        //1:得到输入的Key
        String key = binding.etSpKey.getText().toString();
        //2：根据key读取对应的value
        String value = sp.getString(key, null);
        //3：显示
        if (value == null) {
            Toast.makeText(getApplicationContext(), "找不到对应的value", Toast.LENGTH_SHORT).show();
        } else {
            binding.etSpValue.setText(value);
        }
    }

}

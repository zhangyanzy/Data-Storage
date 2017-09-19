package com.tarenwang.data_storage;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tarenwang.data_storage.activity.SQLiteDemoActivity;
import com.tarenwang.data_storage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setPresenter(new Presenter());
    }

    /**
     * 点击事件
     */
    public class Presenter {
        public void onClick(View view) {
            intent = new Intent();
            switch (view.getId()) {
                case R.id.sp_btn:
                    intent.setClass(getApplicationContext(), SpActivity.class);
                    startActivity(intent);
                    break;
                case R.id.inside_btn:
                    intent.setClass(getApplicationContext(), IFActivity.class);
                    startActivity(intent);
                    break;
                case R.id.external_btn:
                    intent.setClass(getApplicationContext(), SDCardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.sqlite_btn:
                    intent.setClass(getApplicationContext(), SQLiteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.server_btn:
                    break;
                case R.id.sqlite_app:
                    intent.setClass(getApplicationContext(), SQLiteDemoActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
            finish();
        }
    }

}

package com.tarenwang.data_storage;

import android.content.Context;
import android.content.res.AssetManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tarenwang.data_storage.databinding.ActivityIfBinding;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 读取assets下的一张图片
 */

public class IFActivity extends AppCompatActivity {


    private ImageView mIv;
    private ActivityIfBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_if);
        binding.setPresenter(new Presenter());
        init();
    }

    private void init() {
        mIv = (ImageView) findViewById(R.id.image_if);

    }

    public class Presenter {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.save_if:
                    save();
                    break;
                case R.id.read_if:
                    read();
                    break;
                default:
                    break;
            }
        }
    }


    private void save() {
        //1、得到InputStream-->读取assets下的图片
        //得到AssetManager
        AssetManager manager = getAssets();
        //读取文件
        InputStream is = null;
        try {
            is = manager.open("log.png");
            //2、得到OutOutStream-->/data/data/packageName/file/xxx.png
            FileOutputStream fos = openFileOutput("log.png", Context.MODE_PRIVATE);
            //3、边读边写
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            //关闭流
            fos.close();
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //4、提示
        Toast.makeText(getApplicationContext(), "保存完成", Toast.LENGTH_SHORT).show();
    }

    private void read() {
        //1、获得图片路径
        String filePath = getFilesDir().getAbsolutePath();
        String imagePath = filePath + "/log.png";
        //2、读取加载图片文件得到bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        //3、将其设置到imageView中显示
        binding.imageIf.setImageBitmap(bitmap);
    }


}

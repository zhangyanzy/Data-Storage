package com.tarenwang.data_storage;

import android.databinding.DataBindingUtil;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tarenwang.data_storage.databinding.ActivitySdcardBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 手机外部文件存储
 */

public class SDCardActivity extends AppCompatActivity {

    private ActivitySdcardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sdcard);
        binding.setPresenter(new Present());
    }

    /**
     * 点击事件绑定
     */
    public class Present {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.save_sd:
                    save();
                    break;
                case R.id.read_sd:
                    read();
                    break;
                case R.id.save_diy:
                    saveDiy();
                    break;
                case R.id.read_diy:
                    readDiy();
                    break;
                default:
                    break;
            }
        }
    }

    private void save() {
        //1、判断SD卡状态
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //2、读取输入的文件名/内容
            String fileName = binding.etSdName.getText().toString();
            String Content = binding.etSdContent.getText().toString();
            //3、得到指向文件的输出流-->OutPutStream
            //3.1、得到SD卡下的files路径
            File filesPath = getExternalFilesDir(null).getAbsoluteFile();
            //3.2、组成完整路径
            String filePath = filesPath + "/" + fileName;
            //3.3、创建FileOutOutStream
            try {
                FileOutputStream fos = new FileOutputStream(filePath);
                //4、写数据
                fos.write(Content.getBytes("utf-8"));
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "保存完成", Toast.LENGTH_SHORT).show();
            //5、提示
        } else {
            Toast.makeText(getApplicationContext(), "Don't hava SDCARD", Toast.LENGTH_SHORT).show();
        }

    }

    private void read() {
        //1、判断SD卡状态
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //2、读取输入的文件名/内容
            String fileName = binding.etSdName.getText().toString();
            //3、得到指向文件的输入流-->InPutStream
            //3.1、得到SD卡下的files路径
            File filesPath = getExternalFilesDir(null).getAbsoluteFile();
            //3.2、组成完整路径
            String filePath = filesPath + "/" + fileName;
            //3.3、创建FileInOutStream
            try {
                FileInputStream fis = new FileInputStream(filePath);
                //4、读数据成String
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String content = baos.toString();
                //5、显示
                binding.etSdContent.setText(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Don't hava SDCARD", Toast.LENGTH_SHORT).show();
        }
    }

    //storage/sdcard/zhangyan/xxx.txt
    private void saveDiy() {
        //1、判断SD卡状态
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //2、读取输入的文件名/内容
            String fileName = binding.etSdName.getText().toString();
            String Content = binding.etSdContent.getText().toString();
            //3、得到指向文件的输出流-->OutPutStream
            //3.1、得/storage/sdcard
            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            //3.2、/storage/sdcard/zhangyan/(创建文件夹)
            File file = new File(sdPath + "/zhangyan");
            if (!file.exists()) {
                file.mkdirs();//创建文件夹
            }
            //3.3、/storage/sdcard/zhangyan/xxx.txt
            String filePath = sdPath + "/zhangyan/" + fileName;
            //4、创建输入流
            try {
                FileOutputStream fos = new FileOutputStream(filePath);
                //4、写数据
                fos.write(Content.getBytes("utf-8"));
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "保存完成", Toast.LENGTH_SHORT).show();
            //5、提示
        } else {
            Toast.makeText(getApplicationContext(), "Don't hava SDCARD", Toast.LENGTH_SHORT).show();
        }

    }

    private void readDiy() {
        //1、判断SD卡状态
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //2、读取输入的文件名/内容
            String fileName = binding.etSdName.getText().toString();
            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            //3.2、组成完整路径
            String filePath = sdPath + "/zhangyan" + fileName;
            //3.3、创建FileInOutStream
            try {
                FileInputStream fis = new FileInputStream(filePath);
                //4、读数据成String
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = fis.read(buffer))!= -1){
                    baos.write(buffer, 0, len);
                }
                String content = baos.toString();
                fis.close();
                //5、显示
                binding.etSdContent.setText(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

package com.tarenwang.data_storage.test;

import android.test.AndroidTestCase;

import com.tarenwang.data_storage.BlackNumber;
import com.tarenwang.data_storage.BlackNumberDAO;

import java.util.List;

/**
 * Created by zhangYan on 2017/9/19.
 */


//Android的单元测试类
public class BlackNumberDAOTest extends AndroidTestCase {

    public void testAdd(){
        BlackNumberDAO dao = new BlackNumberDAO(getContext());
        dao.add(new BlackNumber(-1,"456"));
    }
    public void testGetAll(){
        BlackNumberDAO dao = new BlackNumberDAO(getContext());
        List<BlackNumber> list = dao.getAll();
    }

}

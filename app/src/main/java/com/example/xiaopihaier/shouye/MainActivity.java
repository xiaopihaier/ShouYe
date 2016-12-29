package com.example.xiaopihaier.shouye;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    View view_1, view_2, view_3, view_4;
    ArrayList<View> arrayList;
    int posin;

    android.os.Handler myhandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                viewPager.setCurrentItem(posin % 4);
                posin++;
                myhandler.sendEmptyMessageDelayed(1, 2500);
            }
        }
    };

    //创建适配器
    PagerAdapter pagerAdapter = new PagerAdapter() {
        //获取viewpager的页数
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(arrayList.get(position));
        }

        //创建
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(arrayList.get(position));
            return arrayList.get(position);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentView();
        //将布局文件加载成view视图
        view_1 = getLayoutInflater().inflate(R.layout.bttom_table1, null);
        view_2 = getLayoutInflater().inflate(R.layout.bttom_lable2, null);
        view_3 = getLayoutInflater().inflate(R.layout.bttom_lable3, null);
        view_4 = getLayoutInflater().inflate(R.layout.bttom_lable4, null);


        arrayList = new ArrayList();
        arrayList.add(view_1);
        arrayList.add(view_2);
        arrayList.add(view_3);
        arrayList.add(view_4);

        viewPager.setAdapter(pagerAdapter);

        myhandler.sendEmptyMessageAtTime(1, 2500);
    }

    private void IntentView() {
        viewPager = (ViewPager) findViewById(R.id.vp_tabs);
    }
}

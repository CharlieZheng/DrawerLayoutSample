package com.demo.jiongdongfilter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jiongdongfilter.Filterfragment.FitrerFragment;

public class FilterActivity extends FragmentActivity implements View.OnClickListener{
    private DrawerLayout mDrawerLayout;
    private RelativeLayout drawer_content;
    private TextView slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        initView();
        initData();
    }

    private void initView() {
        slide = (TextView) findViewById(R.id.slide);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_content = (RelativeLayout) findViewById(R.id.drawer_content);
        slide.setOnClickListener(this);
    }

    private void initData() {
        //显示默认fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_content, new FitrerFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.slide:
                mDrawerLayout.openDrawer(drawer_content);//打开抽屉内容
                break;

        }
    }

}

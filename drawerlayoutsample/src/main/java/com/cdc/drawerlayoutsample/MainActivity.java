package com.cdc.drawerlayoutsample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity implements OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<String> menuLists;
    private ArrayAdapter<String> adapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = (String) getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        menuLists = new ArrayList<String>();
        for (int i = 0; i < 5; i++)
            menuLists.add("菜单0" + i);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menuLists);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle("请选择");
                invalidateOptionsMenu(); // Call onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //开启ActionBar上APP ICON的功能
        getActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            getActionBar().setHomeButtonEnabled(true);
        }

    }

    /** 先onCreateOptionsMenu后onPrepareOptionsMenu，实参是同一个对象
     *  暂时不知道这个方法是用来干嘛的 TODO*/
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!isDrawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /** 这个方法用来显示设置项*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //将ActionBar上的图标与Drawer结合起来
//        if (mDrawerToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        switch (item.getItemId()) {
//            case R.id.action_websearch:
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri uri = Uri.parse("http://www.baidu.com");
//                intent.setData(uri);
//                startActivity(intent);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    /** 这个方法用于显示自定义图标的*/
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        //需要将ActionDrawerToggle与DrawerLayout的状态同步
//        //将ActionBarDrawerToggle中的drawer图标，设置为ActionBar中的Home-Button的Icon
//        mDrawerToggle.syncState();
//    }
/** 暂时不知道这个方法是用来干嘛的 TODO*/
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }








    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long arg3) {
        // 动态插入一个Fragment到FrameLayout当中
        Fragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text", menuLists.get(position));
        contentFragment.setArguments(args);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, contentFragment)
                .commit();

        mDrawerLayout.closeDrawer(mDrawerList);
    }

}
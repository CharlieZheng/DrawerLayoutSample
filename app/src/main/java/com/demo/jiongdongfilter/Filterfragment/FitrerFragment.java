package com.demo.jiongdongfilter.Filterfragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jiongdongfilter.R;
import com.demo.jiongdongfilter.adapter.FiterAdapter;
import com.demo.jiongdongfilter.model.FiterBean;

import java.util.ArrayList;

/**
 * @FileName FitrerFragment
 * @Description 一级筛选界面
 * @Author
 * @Date 2015-08-13 13:36
 * @Version V 1.0
 */
public class FitrerFragment extends BaseFragment implements View.OnClickListener {

    private ListView listView;
    private TextView tvCancel;
    private RelativeLayout drawer_content;
    private DrawerLayout mDrawerLayout;

    private String[] mString = {"品牌", "价格", "尺寸", "特色", "系统", "硬盘", "大家说"};
    private ArrayList<FiterBean> fiterList;
    private FiterAdapter fiterAdapter;

    public FitrerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fiter_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        tvCancel = (TextView) view.findViewById(R.id.tvCancel);

        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer_content = (RelativeLayout) getActivity().findViewById(R.id.drawer_content);

        tvCancel.setOnClickListener(this);
        initData();

    }

    private void initData() {
        fiterList = new ArrayList<>();
        for (int i = 0; i < mString.length; i++) {
            fiterList.add(fiterBean(mString[i]));
        }
        fiterAdapter = new FiterAdapter(getActivity(), fiterList);
        listView.setAdapter(fiterAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showNext(new TowFitrerFragment(),R.id.drawer_content);
            }
        });
    }

    private FiterBean fiterBean(String name) {
        FiterBean bean = new FiterBean();
        bean.setFiterName(name);
        return bean;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCancel:
                mDrawerLayout.closeDrawer(drawer_content);
                break;
        }
    }
}

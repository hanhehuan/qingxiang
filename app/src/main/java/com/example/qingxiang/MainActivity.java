package com.example.qingxiang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.qingxiang.Adapter.SectionsPagerAdapter;
import com.example.qingxiang.Fragment.FragmentChat;
import com.example.qingxiang.Fragment.FragmentHome;
import com.example.qingxiang.Fragment.FragmentMine;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initView();

    }

    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        bottomNavigationBar = findViewById(R.id.bottom);

        initViewPager();
        initBottomNavigationBar();
    }

    private void initBottomNavigationBar() {
        //配置底部导航
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.setBarBackgroundColor(R.color.white)
                .setActiveColor(R.color.colorBase1)
                .setInActiveColor(R.color.black);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.homepage_fill,"首页").setInactiveIconResource(R.drawable.homepage))
        .addItem(new BottomNavigationItem(R.drawable.mobiephone_fill,"论坛").setInactiveIconResource(R.drawable.mobiephone))
        .addItem(new BottomNavigationItem(R.drawable.mine_fill,"我的").setInactiveIconResource(R.drawable.mine))
        .setFirstSelectedPosition(0)
        .initialise();


    }

    private void initViewPager() {

        viewPager.setOffscreenPageLimit(3);
        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentChat());
        fragments.add(new FragmentMine());

        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);

    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

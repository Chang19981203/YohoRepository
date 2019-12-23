package com.example.yohoshop.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.yohoshop.R;
import com.example.yohoshop.mvp.ui.adapter.MyMineViewPagerAdapter;
import com.example.yohoshop.mvp.ui.view.MyMineViewPger;
import com.example.yohoshop.mvp.utils.SpUtils;

import java.util.ArrayList;

public class MineFragment extends Fragment {
    private RadioGroup mine_cut_rg;
    private RadioButton mine_com_rb;
    private RadioButton mine_shopping_rb;
    private MyMineViewPger mine_viewPager;
    private ImageView mine_exit;

    private MyMineViewPagerAdapter myMineViewPagerAdapter;
    private ArrayList<Fragment> list = new ArrayList<>();
    private CommunityFragment communityFragment = new CommunityFragment();
    private ShoppingFragment shoppingFragment = new ShoppingFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_mine, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mine_exit = view.findViewById(R.id.mine_exit);
        mine_cut_rg = view.findViewById(R.id.mine_cut_rg);
        mine_com_rb = view.findViewById(R.id.mine_com_rb);
        mine_shopping_rb = view.findViewById(R.id.mine_shopping_rb);
        mine_viewPager = view.findViewById(R.id.mine_viewPager);
        mine_cut_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.mine_com_rb:{
                        mine_viewPager.setCurrentItem(0);
                        break;
                    }
                    case R.id.mine_shopping_rb:{
                        mine_viewPager.setCurrentItem(1);
                        break;
                    }
                }
            }
        });
        mine_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpUtils.getInstance(getContext()).save("isLogin",false);
            }
        });
        list.clear();
        list.add(communityFragment);
        list.add(shoppingFragment);
        myMineViewPagerAdapter = new MyMineViewPagerAdapter(getChildFragmentManager(),list);
        mine_viewPager.setAdapter(myMineViewPagerAdapter);
        mine_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    mine_com_rb.setChecked(true);
                }else if (position == 1){
                    mine_shopping_rb.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}

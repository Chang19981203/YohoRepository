package com.example.yohoshop.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.EnvUtils;
import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.alipay.PayDemoActivity;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.ShoppingCarEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyShoppingCarBottomAdapter;
import com.example.yohoshop.mvp.ui.adapter.MyShoppingCarTopAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCarActivity extends BaseActivity<LrePresenter> implements LreContact.LreView,MyShoppingCarTopAdapter.GoodsValuesCallBack {
    @BindView(R.id.shopping_back)
    ImageView shopping_back;
    @BindView(R.id.shopping_top_linear)
    LinearLayout shopping_top_linear;
    @BindView(R.id.shopping_center_recycle)
    RecyclerView shopping_center_recycle;
    @BindView(R.id.shopping_center_linear)
    LinearLayout shopping_center_linear;
    @BindView(R.id.shopping_center_all_money)
    TextView shopping_center_all_money;
    @BindView(R.id.shopping_bottom_all_num)
    TextView shopping_all_num;
    @BindView(R.id.shopping_bottom_all_money)
    TextView shopping_bottom_all_money;
    @BindView(R.id.shopping_for_recycle)
    RecyclerView shopping_for_recycle;

    @BindView(R.id.shopping_bottom_linear)
    LinearLayout shopping_bottom_linear;
    @BindView(R.id.shopping_pay_btn)
    Button shopping_pay_btn;
    @BindView(R.id.shopping_bottom_check)
    CheckBox shopping_bottom_check;

    MyShoppingCarBottomAdapter myShoppingCarBottomAdapter;
    ArrayList<ShoppingCarEntity.RecommendGoodsBean> bottomList = new ArrayList<>();

    MyShoppingCarTopAdapter myShoppingCarTopAdapter;
    ArrayList<ShoppingCarEntity.ValuesBean> topList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        ButterKnife.bind(this);
        initMyView();

    }

    private void initMyView() {
        shopping_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        shopping_top_linear.setVisibility(View.GONE);
        shopping_center_linear.setVisibility(View.VISIBLE);
        shopping_center_all_money.setVisibility(View.VISIBLE);
        shopping_bottom_linear.setVisibility(View.VISIBLE);
        shopping_center_recycle.setVisibility(View.VISIBLE);

        myShoppingCarBottomAdapter = new MyShoppingCarBottomAdapter(this,bottomList);
        shopping_for_recycle.setLayoutManager(new GridLayoutManager(this,2));
        shopping_for_recycle.setAdapter(myShoppingCarBottomAdapter);

        myShoppingCarTopAdapter = new MyShoppingCarTopAdapter(this,topList);
        shopping_center_recycle.setAdapter(myShoppingCarTopAdapter);
        shopping_center_recycle.setLayoutManager(new LinearLayoutManager(this));

        shopping_pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCarActivity.this, PayDemoActivity.class));
            }
        });
        myShoppingCarTopAdapter.setGoodsValuesCallBack(this);
        shopping_bottom_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopping_bottom_check.isChecked()){
                    myShoppingCarTopAdapter.setAllCheck();
                }else{
                    myShoppingCarTopAdapter.setUnAllCheck();
                }
            }
        });

    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if (type == ApiDoman.CARLIST&&baseEntity instanceof ShoppingCarEntity){
            List<ShoppingCarEntity.RecommendGoodsBean> recommend_goods = ((ShoppingCarEntity) baseEntity).getRecommend_goods();
            bottomList.addAll(recommend_goods);
            myShoppingCarBottomAdapter.notifyDataSetChanged();

            List<ShoppingCarEntity.ValuesBean> values = ((ShoppingCarEntity) baseEntity).getValues();
            topList.addAll(values);
            myShoppingCarTopAdapter.notifyDataSetChanged();


        }
    }

    @Override
    public void error(Throwable throwable) {

    }

    @Override
    public void refreshSuccess(BaseEntity baseEntity, int type) {

    }

    @Override
    public void loadMoreSuccess(BaseEntity baseEntity, int type) {

    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this)).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_shopping_car;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject object = new JSONObject();
        try {
            object.put("userid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.CARLIST);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void getGoodsValues(boolean isFlag, float allMoney, int allNum) {
        shopping_bottom_check.setChecked(isFlag);
        shopping_center_all_money.setText("总计￥"+allMoney+"=商品金额￥"+allMoney);
        shopping_bottom_all_money.setText(allMoney+"");
        shopping_all_num.setText(allNum+"");


    }
}

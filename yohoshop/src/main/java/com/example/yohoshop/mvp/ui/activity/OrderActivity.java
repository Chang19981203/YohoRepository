package com.example.yohoshop.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.ShoppingCarEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyOrderRecycleAdapter;
import com.example.yohoshop.mvp.ui.adapter.MyShoppingCarBottomAdapter;
import com.example.yohoshop.mvp.ui.adapter.MyShoppingCarTopAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.order_recycle)
    RecyclerView order_recycle;

    MyOrderRecycleAdapter myOrderRecycleAdapter;
    ArrayList<ShoppingCarEntity.ValuesBean> topList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initMyView();

    }

    private void initMyView() {
        myOrderRecycleAdapter = new MyOrderRecycleAdapter(this,topList);
        order_recycle.setAdapter(myOrderRecycleAdapter);
        order_recycle.setLayoutManager(new LinearLayoutManager(this));
        order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if (type == ApiDoman.CARLIST&&baseEntity instanceof ShoppingCarEntity){
            List<ShoppingCarEntity.ValuesBean> values = ((ShoppingCarEntity) baseEntity).getValues();
            topList.addAll(values);
            myOrderRecycleAdapter.notifyDataSetChanged();

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
        return R.layout.activity_order;
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
}

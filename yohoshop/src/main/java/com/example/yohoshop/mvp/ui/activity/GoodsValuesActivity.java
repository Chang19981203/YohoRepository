package com.example.yohoshop.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.api.Api;
import com.example.yohoshop.mvp.model.entity.AddCarEntity;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.GoodsListEntity;
import com.example.yohoshop.mvp.model.entity.GoodsValuesEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.google.gson.JsonObject;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsValuesActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    @BindView(R.id.goods_back)
    ImageView goods_back;
    @BindView(R.id.goods_pic)
    ImageView goods_pic;
    @BindView(R.id.goods_name)
    TextView goods_name;
    @BindView(R.id.goods_price)
    TextView goods_price;
    @BindView(R.id.goods_former_price)
    TextView goods_former_price;
    @BindView(R.id.goods_add_car)
    Button goods_add_car;

    private String[] split = null;
    private GoodsListEntity.ValuesBean valuesBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_values);
        ButterKnife.bind(this);
        initMyView();

    }

    private void initMyView() {
        goods_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        goods_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();
                try {
                    object.put("userid","1");
                    object.put("goodsid",valuesBean.getGoods_id());
                    object.put("shopname",valuesBean.getGoods_name());
                    object.put("shopcolor",valuesBean.getGoods_original_price());
                    object.put("shopsize",valuesBean.getGoods_name());
                    object.put("shopnum",valuesBean.getGoods_img_id());
                    object.put("shopprice",valuesBean.getGoods_original_price());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mPresenter.lreAll(object.toString(),ApiDoman.ADDCAR);

            }
        });
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if (type == ApiDoman.GOODSVALUES&&baseEntity!=null){
            Log.e(TAG, "success: "+baseEntity );
            Glide.with(this).load(Api.APP_DOMAIN+valuesBean.getGoods_img_path()).into(goods_pic);
            goods_name.setText(((GoodsValuesEntity) baseEntity).getValues().get(0).getGoods_name());
            goods_price.setText(((GoodsValuesEntity) baseEntity).getValues().get(0).getGoods_discount_price());
            goods_former_price.setText(((GoodsValuesEntity) baseEntity).getValues().get(0).getGoods_original_price());
        }else if(type == ApiDoman.ADDCAR&&baseEntity!=null){
            Toast.makeText(GoodsValuesActivity.this, ((AddCarEntity)baseEntity).getMsg(), Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_goods_values;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        valuesBean = (GoodsListEntity.ValuesBean) intent.getSerializableExtra("values");
        JSONObject object = new JSONObject();
        try {
            object.put("goodsid",valuesBean.getGoods_id());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.GOODSVALUES);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}

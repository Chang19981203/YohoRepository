package com.example.yohoshop.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.entity.AddressListEntity;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyAddressListRecycleAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends BaseActivity<LrePresenter>implements LreContact.LreView {
    //退出
    private TextView address_back;
    //跳转添加也买你
    private TextView address_add;
    //当前地址列表
    private RecyclerView address_recycle;

    private MyAddressListRecycleAdapter myAddressListRecycleAdapter;
    private ArrayList<AddressListEntity.ValuesBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initMyView();
    }

    private void initMyView() {
        address_back = findViewById(R.id.address_back);
        address_add = findViewById(R.id.address_add);
        address_recycle = findViewById(R.id.address_recycle);
        address_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        address_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
            }
        });

        myAddressListRecycleAdapter = new MyAddressListRecycleAdapter(this,list);
        address_recycle.setAdapter(myAddressListRecycleAdapter);
        address_recycle.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        Log.e(TAG, "success: "+baseEntity );
        if (baseEntity instanceof AddressListEntity&&baseEntity!=null){
            list.clear();
            List<AddressListEntity.ValuesBean> values = ((AddressListEntity) baseEntity).getValues();
            list.addAll(values);
            myAddressListRecycleAdapter.notifyDataSetChanged();
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
        return R.layout.activity_address;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        JSONObject object = new JSONObject();
        try {
            object.put("user_id","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.ADDRESSLIST);
    }
}

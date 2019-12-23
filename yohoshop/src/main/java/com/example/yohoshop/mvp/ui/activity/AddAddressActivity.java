package com.example.yohoshop.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

public class AddAddressActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {
    private TextView add_back;
    private EditText add_name_ed;
    private EditText add_phone_ed;
    private EditText add_address_ed;
    private EditText add_xiang_ed;
    private RadioGroup add_tag_rg;
    private Button add_address_btn;
    private String tag = "家";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initMyView();

    }

    private void initMyView() {
        add_back = findViewById(R.id.add_back);
        add_name_ed = findViewById(R.id.add_name_ed);
        add_phone_ed = findViewById(R.id.add_phone_ed);
        add_address_ed = findViewById(R.id.add_address_ed);
        add_xiang_ed  = findViewById(R.id.add_xiang_ed);
        add_tag_rg = findViewById(R.id.add_tag_rg);
        add_address_btn = findViewById(R.id.add_address_btn);

        add_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        add_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!add_name_ed.getText().toString().isEmpty()&&!add_phone_ed.getText().toString().isEmpty()&&!add_address_ed.getText().toString().isEmpty()&&!add_xiang_ed.getText().toString().isEmpty()){
                    JSONObject object = new JSONObject();
                    try {
                        object.put("user_id","1");
                        object.put("user_name",add_name_ed.getText().toString());
                        object.put("phone",add_phone_ed.getText().toString());
                        object.put("address_area",add_address_ed.getText().toString());
                        object.put("address_detail",add_xiang_ed.getText().toString());
                        object.put("address_tag",tag);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mPresenter.lreAll(object.toString(), ApiDoman.ADDADDRESS);

                }

            }
        });
        //{"user_id":"1","user_name":"wzh","phone":"12345678","address_area":"北京市","address_detail":"八维","address_tag":"家"}

        add_tag_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.add_home:{
                        tag = "家";
                    }
                    case R.id.add_company:{
                        tag = "公司";
                    }
                    case R.id.add_school:{
                        tag = "学校";
                    }
                    case R.id.add_else:{
                        tag = "其他";
                    }
                }
            }
        });
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        Log.e(TAG, "success: "+baseEntity );
        Toast.makeText(this, baseEntity.getMsg(), Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_add_address;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}

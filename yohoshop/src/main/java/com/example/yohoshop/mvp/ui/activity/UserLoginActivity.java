package com.example.yohoshop.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.LoginEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.example.yohoshop.mvp.utils.SpUtils;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserLoginActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {
    private ImageView login_aj;

    @BindView(R.id.login_name)
    EditText login_name;
    @BindView(R.id.login_password)
    EditText login_password;
    @BindView(R.id.login_btn)
    Button login_btn;
    @BindView(R.id.login_register)
    ImageView login_register;

    public String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJa4C5IKvNRcLWXiLFcF4F+i1S2QAusCMszlQeJV84UetEkczjUdJ4dWbnpRkeAmXCTzRHyO67XKS6GSCuKVO/sf7cyll0i6e+d0MSWB2CTxojYingZSV6ZQO8K1Z3fJyFYSHiRhDwJ4idC80zTyKagsWV29uNa38iQYr4FwbNqZAgMBAAECgYAxV1k6W1eMMg0OsKeRabQVuwoNG3tJEnQtDdSu0zKg3vdohAyh6MR7EvmiA7g86HH8CsPd/y/9WJe/8j6sBO0Ye9gt7eyQ2NiwWvlTuwNmngcSTapVvVI6NEyJFMfQt9PB1EHLNAXlz8jtJUyA7C48jReQD9p/SzAP0VxG7lwyMQJBAOjE7hAZ/6fyP3DB1fG7jr9gONZcz3TUaqx6BUn4GKZnckW08ht9Xqcqft5Hthu8BbLM9ptQ0U8QZekrJwD6ya0CQQClwstZMPu8jLhsgugVwodcG1mPEOiw9Yjnmt9+WTI07Ll2uFv//hRXBnahBBnZbucUYEbUY3kqUX9b3e9TmEodAkEAybPMbxt4VDoxCy6Mi/pxChkBZ4/pHV3sSiU6bAyWn6vIc+sGWRfca5MBePA/N+1IKtY9Y/02QwL8rH5+P/URyQJAL/hdjORGFdzLimuf6pwvPBKWKncEQCHuisghIZmClBpl2duklELddAnkztg2+tvDd/wcw14+NGb9aoKhvhl2aQJAbvcgoPU+xs0CjeexH+TS2S/jKkTRpvP2CpPK/k71m13xWdE8RtMkYY1measRmlIwOfWze7ll/PGT4dxWf31FNg==";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(this);
        initMyView();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: 点击" );
                if (!login_name.getText().toString().isEmpty()&&!login_password.getText().toString().isEmpty()){
                    login(login_name.getText().toString(),login_password.getText().toString());
                }else{
                    Toast.makeText(UserLoginActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginActivity.this,RegisterActivity.class));
            }
        });

    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        Log.e("chang ", "success: "+baseEntity );
        if (baseEntity!=null&&baseEntity instanceof LoginEntity){
            Log.e("chang ", "success: "+baseEntity.getMsg() );
            if (baseEntity.getMsg().equals("请求成功")){
                SpUtils.getInstance(this).save("isLogin",true);
                finish();
            }

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

        return R.layout.activity_user_login;
    }

    private void initMyView() {
        login_aj = findViewById(R.id.login_aj);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(login_aj, "rotation", 0f, 90f);
        login_aj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectAnimator.start();
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    private void login(String name,String pwd){

        String nameStr = name;
        String pwdStr = pwd;
        Log.e("name",name);
        Log.e("pwd",pwd);
        //rsq加密pwdStr
        //rsa加密后密码Base64->防乱码
        byte[] buf = EncryptUtils.encryptRSA(pwdStr.getBytes(),
                android.util.Base64.decode(privateKey.getBytes(), android.util.Base64.DEFAULT)
                ,false,"RSA/ECB/PKCS1Padding");
        byte[]buff = android.util.Base64.encode(buf, android.util.Base64.DEFAULT);
        String rasPwd = new String(buff);
        Log.e("ZXY","rasPwd:"+rasPwd);
        //生成请求参数
        JSONObject job = new JSONObject();
        try {
            job.put("username",nameStr);
            job.put("password",rasPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(job.toString(), ApiDoman.LOGIN);
    }

}

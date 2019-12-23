package com.example.yohoshop.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yohoshop.R;

import com.example.yohoshop.di.component.DaggerMenuComponent;
import com.example.yohoshop.di.module.MenuModule;
import com.example.yohoshop.mvp.contact.MenuContact;
import com.example.yohoshop.mvp.model.entity.MenuEntity;
import com.example.yohoshop.mvp.presenter.MenuPersenter;
import com.example.yohoshop.mvp.ui.fragment.ClassFragment;
import com.example.yohoshop.mvp.ui.fragment.ComFragment;
import com.example.yohoshop.mvp.ui.fragment.HomeFragment;
import com.example.yohoshop.mvp.ui.fragment.MineFragment;
import com.example.yohoshop.mvp.ui.fragment.UFOFragment;
import com.example.yohoshop.mvp.utils.SpUtils;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends BaseActivity<MenuPersenter> implements MenuContact.MenuView {
    @BindView(R.id.main_frame)
    FrameLayout main_frame;
    @BindView(R.id.main_rg)
    RadioGroup main_rg;
    @BindView(R.id.yoho_car)
    ImageView yoho_car;
    private HomeFragment homeFragment = new HomeFragment();
    private ClassFragment classFragment = new ClassFragment();
    private UFOFragment ufoFragment = new UFOFragment();
    private ComFragment comFragment = new ComFragment();
    private MineFragment mineFragment = new MineFragment();
    private FragmentManager fragmentManager;

    @Override
    public void getMenuOk(MenuEntity menuEntity) {
        if (menuEntity!=null){
            Log.e("menuEntity", "getMenuOk: "+menuEntity.getValues().toString() );

        }else{

        }
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMenuComponent.builder().appComponent(appComponent).menuModule(new MenuModule(this)).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        init();
        mPresenter.requestMenu();
    }

    private void init() {
        yoho_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShoppingCarActivity.class));
            }
        });
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_frame,homeFragment).commit();

        main_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.main_home: {
                        yoho_car.setVisibility(View.VISIBLE);
                        fragmentManager.beginTransaction().replace(R.id.main_frame,homeFragment).commit();
                    } break;
                    case R.id.main_class: {
                        yoho_car.setVisibility(View.VISIBLE);
                        fragmentManager.beginTransaction().replace(R.id.main_frame,classFragment).commit();
                    } break;
                    case R.id.main_ufo: {
                        yoho_car.setVisibility(View.VISIBLE);
                        fragmentManager.beginTransaction().replace(R.id.main_frame,ufoFragment).commit();
                    } break;
                    case R.id.main_com: {
                        yoho_car.setVisibility(View.GONE);
                        fragmentManager.beginTransaction().replace(R.id.main_frame,comFragment).commit();
                    } break;
                    case R.id.main_mine: {
                        yoho_car.setVisibility(View.GONE);
                        if (SpUtils.getInstance(MainActivity.this).getBoolean("isLogin",false)){
                            fragmentManager.beginTransaction().replace(R.id.main_frame,mineFragment).commit();
                        }else{
                            startActivity(new Intent(MainActivity.this,UserLoginActivity.class));
                        }

                    } break;
                }
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@NonNull Intent intent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

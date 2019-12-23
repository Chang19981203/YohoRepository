package com.example.yohoshop.mvp.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.yohoshop.R;

public class Login2Activity extends AppCompatActivity {
    private ImageView login_cut;
    private Button login_jump;

    private ObjectAnimator alphaAnimator;

    private ObjectAnimator scaleXAnimator;
    private ObjectAnimator scaleYAnimator;
    private ObjectAnimator ascaleXAnimator;
    private ObjectAnimator ascaleYAnimator;

    private AnimatorSet animatorSet;
    private AnimatorSet aanimatorSet;
    //是否是点击跳转
    private boolean isJump = false;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();

        aanimatorSet = new AnimatorSet();
        ascaleXAnimator = ObjectAnimator.ofFloat(login_cut, "ScaleY", 1.1f);
        ascaleXAnimator.setDuration(0);
        ascaleYAnimator = ObjectAnimator.ofFloat(login_cut, "ScaleX", 1.1f);
        ascaleYAnimator.setDuration(0);
        aanimatorSet.play(ascaleXAnimator).with(ascaleYAnimator);
        alphaAnimator = ObjectAnimator.ofFloat(login_cut, "alpha", 0.0f, 1.0f);
        alphaAnimator.setDuration(3000);
        animatorSet = new AnimatorSet();
        scaleXAnimator = ObjectAnimator.ofFloat(login_cut, "ScaleY", 1.1f, 1.0f);
        scaleXAnimator.setDuration(3000);
        scaleYAnimator = ObjectAnimator.ofFloat(login_cut, "ScaleX", 1.1f, 1.0f);
        scaleYAnimator.setDuration(3000);
        animatorSet.play(scaleXAnimator).with(scaleYAnimator);

        alphaAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                login_jump.setVisibility(View.GONE);
                animatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isJump){
                    startActivity(new Intent(Login2Activity.this, MainActivity.class));
                    overridePendingTransition(R.anim.startin, R.anim.startout);
                    finish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        login_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isJump = true;
                startActivity(new Intent(Login2Activity.this, MainActivity.class));
                finish();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        alphaAnimator.clone();
        aanimatorSet.clone();

    }

    @Override
    protected void onResume() {
        super.onResume();
        aanimatorSet.start();
        alphaAnimator.start();
    }

    private void initView() {
        login_cut = findViewById(R.id.login_cut);
        login_jump = findViewById(R.id.login_jump);
    }

}

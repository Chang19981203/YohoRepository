package com.example.yohoshop.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yohoshop.R;
import com.example.yohoshop.mvp.ui.activity.UserLoginActivity;

public class AttentionFragment extends Fragment {
    private Button att_login;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_attention, null);
        att_login = view.findViewById(R.id.att_login);
        att_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UserLoginActivity.class));
            }
        });


        return view;
    }
}

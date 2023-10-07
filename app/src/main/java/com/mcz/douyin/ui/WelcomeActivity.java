package com.mcz.douyin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.mcz.douyin.activitys.MainActivity;
import com.mcz.douyin.databinding.ActivityWelcomeBinding;
import com.mcz.douyin.ui.login.LoginActivity;
import com.mcz.douyin.util.Constant;


public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding binding;
    private static final int MSG_UPDATE_TIME = 1;
    private int countTime = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_UPDATE_TIME:
                    updateTime();
                    sendEmptyMessageDelayed(MSG_UPDATE_TIME, 1000);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mHandler.sendEmptyMessage(MSG_UPDATE_TIME);
    }

    private void updateTime() {
        if (countTime == 0) {
            mHandler.removeCallbacksAndMessages(null);
            String token = SPUtils.getInstance().getString(Constant.TOKEN, "");
            if (TextUtils.isEmpty(token)) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }

            mHandler.removeCallbacksAndMessages(null);
            finish();
            return;
        }
        countTime--;
        binding.tvJumpMain.setText(countTime + "s后跳转，点击跳过");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
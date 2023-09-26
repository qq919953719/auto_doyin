package com.mcz.douyin.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.mcz.douyin.activitys.MainActivity;
import com.mcz.douyin.bean.VcodeBean;
import com.mcz.douyin.databinding.ActivityLoginBinding;
import com.mcz.douyin.util.Constant;
import com.mcz.douyin.util.CountDownTimerUtils;
import com.mcz.douyin.util.OkManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;


public class LoginActivity extends AppCompatActivity {

    private OkManager manager;
    private OkHttpClient clients;
    private ActivityLoginBinding binding;
    private String getVcoeUrl = "https://juzhen.xibeizhenxing.com/api/sms/send";
    private String loginAutoSystemUrl = "https://juzhen.xibeizhenxing.com/api/user/mobilelogin";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        manager = OkManager.getInstance();
        binding.btnGetvcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.username.getText().toString().trim().length() != 11) {
                    Toast.makeText(LoginActivity.this, "请您输入正确的手机好吗", Toast.LENGTH_SHORT).show();
                    return;
                }
                getVcode(binding.username.getText().toString().trim());
            }
        });
        binding.btnLogin222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.username.getText().toString().trim().length() != 11) {
                    Toast.makeText(LoginActivity.this, "请您输入正确的手机好吗", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (binding.password.getText().toString().trim().length() == 0) {
                    Toast.makeText(LoginActivity.this, "请您输入正确的验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginAutoSystem(binding.username.getText().toString().trim(), binding.password.getText().toString().trim());

            }
        });
    }

    private void loginAutoSystem(String phone, String pwd) {
        binding.loading.setVisibility(View.VISIBLE);
        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", phone);
        map.put("captcha", pwd);
        manager.sendComplexForm(loginAutoSystemUrl, map, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("Tag", jsonObject.toString());
                binding.loading.setVisibility(View.GONE);
                VcodeBean bean = JSON.parseObject(jsonObject.toString(), VcodeBean.class);
                if (bean.getCode() == 1) {
                    SPUtils.getInstance().put(Constant.TOKEN, bean.getData().getUserinfo().getToken());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void getVcode(String phone) {
        binding.loading.setVisibility(View.VISIBLE);
        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", phone);
        map.put("event", "mobilelogin");
        manager.sendComplexForm(getVcoeUrl, map, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("Tag", jsonObject.toString());
                VcodeBean bean = JSON.parseObject(jsonObject.toString(), VcodeBean.class);
                if (bean.getCode() == 1) {
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(binding.btnGetvcode, 60000, 1000); //倒计时1分钟
                    mCountDownTimerUtils.start();
                    binding.btnLogin222.setEnabled(true);
                } else {
                    Toast.makeText(LoginActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
                binding.loading.setVisibility(View.GONE);

            }
        });

    }


}
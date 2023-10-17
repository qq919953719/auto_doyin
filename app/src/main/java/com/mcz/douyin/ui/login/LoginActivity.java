package com.mcz.douyin.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.mcz.douyin.activitys.MainActivity;
import com.mcz.douyin.bean.VcodeBean;
import com.mcz.douyin.databinding.ActivityLoginBinding;
import com.mcz.douyin.util.Constant;
import com.mcz.douyin.util.OkManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;


public class LoginActivity extends AppCompatActivity {

    private OkManager manager;
    private OkHttpClient clients;
    private ActivityLoginBinding binding;
    private String loginAutoSystemUrl = "http://www.xubeibei.icu:81/api/biz/client/login";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.username.setText(SPUtils.getInstance().getString(Constant.ACCOUNT, ""));
        binding.password.setText(SPUtils.getInstance().getString(Constant.PWD, ""));
        manager = OkManager.getInstance();
        binding.btnLogin222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.username.getText().toString().trim().length() == 0) {
                    Toast.makeText(LoginActivity.this, "请您输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (binding.password.getText().toString().trim().length() == 0) {
                    Toast.makeText(LoginActivity.this, "请您输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginAutoSystem(binding.username.getText().toString().trim(), binding.password.getText().toString().trim());

            }
        });
    }


    private void loginAutoSystem(String phone, String pwd) {
        binding.loading.setVisibility(View.VISIBLE);
        Map<String, String> map = new HashMap<String, String>();
        map.put("account", phone);
        map.put("pwd", pwd);
        map.put("deviceName", DeviceUtils.getModel());
        map.put("deviceID", DeviceUtils.getUniqueDeviceId());
        manager.sendComplexForm(loginAutoSystemUrl, map, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("Tag", jsonObject.toString());
                binding.loading.setVisibility(View.GONE);
                VcodeBean bean = JSON.parseObject(jsonObject.toString(), VcodeBean.class);
                if (bean.getCode() == 200) {
                    SPUtils.getInstance().put(Constant.TOKEN, bean.getData().getToken());
                    SPUtils.getInstance().put(Constant.ACCOUNT, phone);
                    SPUtils.getInstance().put(Constant.PWD, pwd);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
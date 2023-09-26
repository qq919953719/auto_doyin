package com.mcz.douyin.ui;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.mcz.douyin.R;
import com.mcz.douyin.bean.AutoDataBean;
import com.mcz.douyin.databinding.ActivityFunctionBinding;
import com.mcz.douyin.script.TaskDemo;
import com.mcz.douyin.util.Constant;
import com.mcz.douyin.util.OkManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class FunctionActivity extends AppCompatActivity {
    private int delayTime = 600000;  //10分钟执行一次
    private static final String TAG = "MainActivity";
    private String loginAutoSystemUrl = "https://juzhen.xibeizhenxing.com/api/user/DyName";
    ActivityFunctionBinding binding;
    Button runBtn;

    private OkManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFunctionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        manager = OkManager.getInstance();
        getAutoData();
        runBtn = findViewById(R.id.runBtn);
        binding.btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAutoData();
            }
        });
        mHandler.postDelayed(mRunnable, delayTime);
    }

    /**
     * 点击按钮对应的方法
     *
     * @param v
     */
    public void runMyUiautomator(View v) {
        mHandler.post(mRunnable);
        Log.i(TAG, "runMyUiautomator: ");
        Toast.makeText(this, "start run", Toast.LENGTH_SHORT).show();
    }


    private void getAutoData() {
        String token = SPUtils.getInstance().getString(Constant.TOKEN, "");
        if (TextUtils.isEmpty(token)) {
            Toast.makeText(FunctionActivity.this, "token失效", Toast.LENGTH_SHORT).show();
            return;
        }
        binding.loading.setVisibility(View.VISIBLE);
        if (manager == null) {
            manager = OkManager.getInstance();
        }
        Map<String, String> map = new HashMap<String, String>();

        manager.sendComplexForm(loginAutoSystemUrl, token, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("Tag", "11111111111");
                Log.i("Tag", jsonObject.toString());

                AutoDataBean bean = JSON.parseObject(jsonObject.toString(), AutoDataBean.class);
                if (bean.getCode() == 1) {
                    binding.tvAutoData.setText(jsonObject.toString());

                    for (int i = 0; i < bean.getData().size(); i++) {

                    }
                    start_run_dy();
                    Log.i(TAG, "runMyUiautomator: ");

                    Toast.makeText(FunctionActivity.this, "start run", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(FunctionActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
                binding.loading.setVisibility(View.GONE);
            }
        });

    }

    // 定义一个Handler类
    private Handler mHandler = new Handler();
    //
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            // 要做的事情
            getAutoData();
            mHandler.postDelayed(this, delayTime);
        }
    };
// 启动计时器


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 停止计时器
        mHandler.removeCallbacks(mRunnable);
    }

    private void start_run_dy() {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {
//
//                    DyTaskService dyTaskService = new DyTaskService();
//                    dyTaskService.main();

                    TaskDemo demo = new TaskDemo();
                    demo.start_run();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

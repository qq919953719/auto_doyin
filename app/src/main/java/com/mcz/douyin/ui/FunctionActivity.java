package com.mcz.douyin.ui;

import static com.mcz.douyin.config.GlobalVariableHolder.waitThreeSecond;
import static com.mcz.douyin.node.AccUtils.home;
import static com.mcz.douyin.node.AccUtils.printLogMsg;
import static com.mcz.douyin.node.AccUtils.timeSleep;

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
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.mcz.douyin.R;
import com.mcz.douyin.bean.AutoDataBean;
import com.mcz.douyin.bean.AutoFollowDataBean;
import com.mcz.douyin.databinding.ActivityFunctionBinding;
import com.mcz.douyin.script.TaskDemo;
import com.mcz.douyin.script.dyService.TaskItemDemo;
import com.mcz.douyin.util.Constant;
import com.mcz.douyin.util.OkManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FunctionActivity extends AppCompatActivity {
    private int delayTime = 60000;  //1分钟执行一次
    private static final String TAG = "MainActivity";
    private String loginAutoSystemUrl = "http://www.xubeibei.icu:81/api/biz/client/deviceInfoClient";
    ActivityFunctionBinding binding;
    Button runBtn;

    private OkManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFunctionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        manager = OkManager.getInstance();
//        getAutoData();
//        getAutoFollowData();
        runBtn = findViewById(R.id.runBtn);
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                TaskDemo.scriptStart = false;
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


    @RequiresApi(api = Build.VERSION_CODES.N)
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
        map.put("deviceID", DeviceUtils.getUniqueDeviceId());
        map.put("token", SPUtils.getInstance().getString(Constant.TOKEN, ""));
        manager.sendComplexForm(loginAutoSystemUrl, map, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("Tag", jsonObject.toString());
                AutoDataBean bean = JSON.parseObject(jsonObject.toString(), AutoDataBean.class);
                if (bean.getCode() == 200) {
                    binding.tvAutoData.setText(jsonObject.toString());
                    printLogMsg("获取接口数据：" + jsonObject.toString());
                    if (bean.getData() != null) {
                        if (bean.getData().getDevice().getMsgOn().equals("0")) {
                            //开始执行脚本
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    if (!TaskDemo.scriptStart) {
                                        //脚本没有执行，开始执行脚本
                                        TaskDemo.scriptStart = true;
                                        TaskDemo.videoNum = 0;
                                        TaskDemo demo = new TaskDemo();
                                        demo.startAutoParenting(bean);
                                        home();
                                    }

                                }
                            }).start();
                        } else {
                            //关闭脚本
                            TaskDemo.scriptStart = false;
                        }

                    }

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
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {
            // 要做的事情
            mHandler.postDelayed(this, delayTime);
            getAutoData();
        }
    };
// 启动计时器


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 停止计时器
        mHandler.removeCallbacks(mRunnable);
    }


}

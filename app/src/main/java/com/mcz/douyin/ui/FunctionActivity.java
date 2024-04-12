package com.mcz.douyin.ui;

import static com.mcz.douyin.config.GlobalVariableHolder.waitThreeSecond;
import static com.mcz.douyin.node.AccUtils.backToDesktop;
import static com.mcz.douyin.node.AccUtils.home;
import static com.mcz.douyin.node.AccUtils.printLogMsg;
import static com.mcz.douyin.node.AccUtils.timeSleep;
import static com.mcz.douyin.script.TaskDemo.scriptGrowthRunning;
import static com.mcz.douyin.script.TaskDemo.scriptGrowthStart;
import static com.mcz.douyin.script.TaskDemo.scriptMessageRunning;
import static com.mcz.douyin.script.TaskDemo.scriptMessageStart;


import android.content.Intent;
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
import com.mcz.douyin.bean.OnOffBean;
import com.mcz.douyin.databinding.ActivityFunctionBinding;
import com.mcz.douyin.script.TaskDemo;
import com.mcz.douyin.ui.login.LoginActivity;
import com.mcz.douyin.util.Constant;
import com.mcz.douyin.util.OkManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class FunctionActivity extends AppCompatActivity {
    private int delayTime = 60000;  //1分钟执行一次
    private static final String TAG = "MainActivity";
    private String loginAutoSystemUrl = "http://121.40.17.26:18080/api/biz/client/deviceInfoClient";
    private String onOffSystemUrl = "http://121.40.17.26:18080/api/biz/client/deviceInfoClientSwitch";
    ActivityFunctionBinding binding;
    Button runBtn;

    private OkManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFunctionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        manager = OkManager.getInstance();
        isVisivity = true;
//        getAutoData();
//        getAutoFollowData();
        runBtn = findViewById(R.id.runBtn);
        binding.tvDeviceInfo.setText("当前账号:" + SPUtils.getInstance().getString(Constant.ACCOUNT, "") + "\n当前设备id:" + DeviceUtils.getUniqueDeviceId());
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                scriptMessageStart = false;
                scriptGrowthStart = false;
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
    private void getOnOff() {
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
        manager.sendComplexForm(onOffSystemUrl, map, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("Tag", jsonObject.toString());
                OnOffBean bean = JSON.parseObject(jsonObject.toString(), OnOffBean.class);
                if (bean.getCode() == 200) {
                    binding.tvAutoData.setText(jsonObject.toString());
                    printLogMsg("获取接口数据：" + jsonObject.toString());
                    if (bean.getData() != null) {
                        if (bean.getData().getMsgOn().equals("0")) {
                            if (!scriptMessageRunning) {
                                scriptMessageStart = true;
                                scriptGrowthStart = false;
                                if (!scriptGrowthRunning) {
                                    getAutoData();
                                }
                            }
                            return;
                        } else if (bean.getData().getGrowUpOn().equals("0")) {
                            scriptMessageStart = false;
                            scriptGrowthStart = true;
                            if (!scriptMessageRunning && !scriptGrowthRunning) {
                                getAutoData();
                            }
                        } else {
                            scriptMessageStart = false;
                            scriptGrowthStart = false;
                            printLogMsg("脚本全部关闭");

                        }
                        //开始执行脚本
                    }

                    Log.i(TAG, "runMyUiautomator: ");
                    Toast.makeText(FunctionActivity.this, "start run", Toast.LENGTH_SHORT).show();
                } else {
                    TaskDemo.scriptGrowthStart = false;
                    scriptMessageStart = false;
                    backToDesktop();
                    backToDesktop();
                    Intent intent = new Intent(FunctionActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(FunctionActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
                binding.loading.setVisibility(View.GONE);
            }
        });

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
                        //开始执行脚本
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //脚本没有执行，开始执行脚本
                                if (scriptMessageStart) {
                                    printLogMsg("开始执行私信脚本");
                                    if (isVisivity) {
                                        home();
                                    }
                                    if (!scriptGrowthRunning && !scriptMessageRunning) {
                                        TaskDemo.videoNum = 0;
                                        TaskDemo demo = new TaskDemo();
                                        demo.startAutoParenting(bean);
                                    }

                                } else if (scriptGrowthStart) {
                                    printLogMsg("开始执行养号脚本");
                                    if (isVisivity) {
                                        home();
                                    }
                                    if (!scriptGrowthRunning && !scriptMessageRunning) {
                                        TaskDemo.videoNum = 0;
                                        TaskDemo demo = new TaskDemo();
                                        demo.startAutoGrowthOn(bean);
                                    }

                                } else {
                                    scriptMessageStart = false;
                                    scriptGrowthStart = false;
                                    backToDesktop();
                                }


                            }
                        }).start();
                    }

                    Log.i(TAG, "runMyUiautomator: ");
                    Toast.makeText(FunctionActivity.this, "start run", Toast.LENGTH_SHORT).show();
                } else {
                    scriptMessageStart = false;
                    scriptGrowthStart = false;
                    backToDesktop();
                    backToDesktop();
                    Intent intent = new Intent(FunctionActivity.this, LoginActivity.class);
                    startActivity(intent);
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
            getOnOff();
            //脚本执行完毕，开始获取数据


        }
    };
// 启动计时器


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 停止计时器
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isVisivity = false;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isVisivity = true;
    }

    private boolean isVisivity = true;
}

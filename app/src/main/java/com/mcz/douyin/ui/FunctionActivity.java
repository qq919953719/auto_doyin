package com.mcz.douyin.ui;

import static com.mcz.douyin.config.GlobalVariableHolder.waitThreeSecond;
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
    private int delayTime = 600000000;  //10分钟执行一次
    private static final String TAG = "MainActivity";
    private String loginAutoSystemUrl = "https://juzhen.xibeizhenxing.com/api/user/DyName";
    private String loginAutoFollowUrl = "https://juzhen.xibeizhenxing.com/api/user/getFollowName";
    ActivityFunctionBinding binding;
    Button runBtn;

    private OkManager manager;

    private List<AutoDataBean.DataDTO> autoDataBeanList = new ArrayList<>();
    ;

    @Override
    protected void onRestart() {
        super.onRestart();
//        getAutoData();
//        getAutoFollowData();
    }

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
        autoDataBeanList.clear();
        AutoDataBean.DataDTO bean = new AutoDataBean.DataDTO();
        bean.setComment("关注你了，记得回关一下奥");
        bean.setId(1);
        bean.setDyName("我是");
        bean.setProbability(100);
        autoDataBeanList.add(bean);

        AutoDataBean.DataDTO bean1 = new AutoDataBean.DataDTO();
        bean1.setComment("你在干嘛呢？什么时候开直播啊？");
        bean1.setDyName("石头人");
        bean1.setProbability(100);
        bean1.setId(2);
        autoDataBeanList.add(bean1);
        printLogMsg("获取数据成功");
//
//
//        for (int i = 3; i < 100; i++) {
//            AutoDataBean.DataDTO beanDemo = new AutoDataBean.DataDTO();
//            beanDemo.setId(i);
//            autoDataBeanList.add(beanDemo);
//
//        }
        AutoDataBean myBean = new AutoDataBean();
        myBean.setData(autoDataBeanList);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                for (AutoDataBean.DataDTO doyinBean : autoDataBeanList) {
//                    TaskItemDemo itemDemo = new TaskItemDemo();
//                    itemDemo.startAutoParenting(doyinBean);
//                }

                TaskDemo.scriptStart = true;
                TaskDemo.videoNum = 0;
                TaskDemo demo = new TaskDemo();
                demo.startAutoParenting(myBean);
            }
        }).start();


//        String token = SPUtils.getInstance().getString(Constant.TOKEN, "");
//        if (TextUtils.isEmpty(token)) {
//            Toast.makeText(FunctionActivity.this, "token失效", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        binding.loading.setVisibility(View.VISIBLE);
//        if (manager == null) {
//            manager = OkManager.getInstance();
//        }
//        Map<String, String> map = new HashMap<String, String>();
//
//        manager.sendComplexForm(loginAutoSystemUrl, token, new OkManager.Fun4() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                Log.i("Tag", "11111111111");
//                Log.i("Tag", jsonObject.toString());
//
//                AutoDataBean bean = JSON.parseObject(jsonObject.toString(), AutoDataBean.class);
//                if (bean.getCode() == 1) {
//                    binding.tvAutoData.setText(jsonObject.toString());
//
//                    if(bean.getData() == null || bean.getData().size() == 0){
//                        Toast.makeText(FunctionActivity.this, "数据为空，数据马上就来啦～", Toast.LENGTH_SHORT).show();
//                        getAutoFollowData();
//                        return;
//                    }
//                    start_run_dy(bean);
//                    Log.i(TAG, "runMyUiautomator: ");
//                    Toast.makeText(FunctionActivity.this, "start run", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(FunctionActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
//                }
//                binding.loading.setVisibility(View.GONE);
//            }
//        });

    }

    private void getAutoFollowData() {
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

        manager.sendComplexForm(loginAutoFollowUrl, token, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("Tag", "11111111111");
                Log.i("Tag", jsonObject.toString());

                AutoFollowDataBean bean = JSON.parseObject(jsonObject.toString(), AutoFollowDataBean.class);
                if (bean.getCode() == 1) {
                    binding.tvAutoData.setText(jsonObject.toString());

                    if (bean.getData() == null) {
                        Toast.makeText(FunctionActivity.this, "数据为空，数据马上就来啦～", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    start_run_dy_follow(bean);
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
            getAutoData();
//            mHandler.postDelayed(this, delayTime);
        }
    };
// 启动计时器


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 停止计时器
        mHandler.removeCallbacks(mRunnable);
    }

    private void start_run_dy(AutoDataBean autoDataBean) {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {
//
//                    DyTaskService dyTaskService = new DyTaskService();
//                    dyTaskService.main();

                    TaskDemo demo = new TaskDemo();
                    demo.start_run(autoDataBean);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void start_run_dy_follow(AutoFollowDataBean autoFollowDataBean) {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {
//
//                    DyTaskService dyTaskService = new DyTaskService();
//                    dyTaskService.main();

                    TaskDemo demo = new TaskDemo();
                    demo.start_run_follow(autoFollowDataBean);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

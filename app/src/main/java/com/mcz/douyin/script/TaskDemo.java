package com.mcz.douyin.script;

import static com.mcz.douyin.config.GlobalVariableHolder.*;
import static com.mcz.douyin.node.AccUtils.*;
import static com.mcz.douyin.util.Constant.baseUrl;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ClipboardUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.mcz.douyin.bean.AutoDataBean;
import com.mcz.douyin.bean.AutoFollowDataBean;
import com.mcz.douyin.bean.OnOffBean;
import com.mcz.douyin.node.AccUtils;
import com.mcz.douyin.node.UiCollection;
import com.mcz.douyin.node.UiSelector;
import com.mcz.douyin.script.dyService.TaskItemDemo;
import com.mcz.douyin.ui.FunctionActivity;
import com.mcz.douyin.util.Constant;
import com.mcz.douyin.util.DataUtils;
import com.mcz.douyin.util.OkManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TaskDemo extends UiSelector {
    public static boolean scriptGrowthStart = false;
    public static boolean scriptMessageStart = false;
    public static boolean scriptGrowthRunning = false;
    public static boolean scriptMessageRunning = false;
    private AutoDataBean autoDataBean;
    private AutoFollowDataBean autoFollowDataBean;

    public static int videoNum = 0;


    /****************************************测试脚本***************************************************************************/

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startAutoGrowthOn(AutoDataBean bean) {
        scriptGrowthRunning = true;
        autoDataBean = bean;
        /*************************/
        timeSleep(waitThreeSecond);
        jumpApp("com.ss.android.ugc.aweme");
        printLogMsg("抖音打开成功");
        while (true) {
            if (!scriptGrowthStart) {
                scriptGrowthRunning = false;
                scriptGrowthStart = false;
                printLogMsg("已关闭脚本养号功能");
                break;
            }
            scriptGrowthRunning = true;
            try {
                timeSleep(waitSixSecond);
                TaskItemDemo itemDemo = new TaskItemDemo();
                itemDemo.startAutoParenting(autoDataBean.getData());
            } catch (Exception e) {
                scriptGrowthRunning = false;
                printLogMsg("养号异常，等待重启启动");
                backToDesktop();
//                timeSleep(waitSixSecond);
//                TaskItemDemo itemDemo = new TaskItemDemo();
//                itemDemo.startAutoParenting(autoDataBean.getData());
            }

        }
        scriptGrowthRunning = false;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startAutoParenting(AutoDataBean bean) {
        this.autoDataBean = bean;

        if (scriptMessageStart) {
            //私信
            try {
                scriptMessageRunning = true;
                sendMessage(bean);
            } catch (Exception e) {
                backToDesktop();
                printLogMsg("私信异常，等待重启启动");
                scriptMessageRunning = false;
            }
            printLogMsg("私信脚本执行完毕");
            scriptMessageRunning = false;
            return;
        } else {
            scriptMessageRunning = false;
            scriptMessageStart = false;
            printLogMsg("已关闭");
            return;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sendMessage(AutoDataBean bean) {
        timeSleep(waitThreeSecond);
        //私信
        jumpApp("com.ss.android.ugc.aweme");
        printLogMsg("抖音打开成功");
        timeSleep(waitSixSecond);
        printLogMsg("开始执行私信操作");
        for (AutoDataBean.DataDTO.DeviceDTO doyinBean : bean.getData().getTargetUserList()) {
            scriptMessageRunning = true;
            if (!scriptMessageStart) {
                printLogMsg("已关闭脚本");
                scriptMessageRunning = false;
                break;
            }
            if (className("Button").text("拒绝").exists()) {
                //关闭通讯录
                className("Button").text("拒绝").findOne().click();
                printLogMsg("关闭通讯录");
            }
            timeSleep(waitTwoSecond);
            if (className("ImageView").desc("关闭").exists()) {
                //关闭通讯录
                className("ImageView").desc("关闭").findOne().click();
                printLogMsg("关闭通讯录");
            }
            timeSleep(waitTwoSecond);
            int loopTime = 0;
            while (true) {
                if (className("ImageView").desc("关闭").exists()) {
                    className("ImageView").desc("关闭").findOne().click();
                }
                timeSleep(500);
                loopTime++;
                if (loopTime > 10) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }
            timeSleep(waitThreeSecond);
            loopTime = 0;

            while (true) {
                if (className("TextView").desc("搜索").exists()) {
                    className("TextView").desc("搜索").findOne().click();
                    printLogMsg("点击搜索按TextView钮成功");
                    break;
                }
                if (className("Button").desc("搜索").exists()) {
                    className("Button").desc("搜索").findOne().click();
                    printLogMsg("点击搜索按钮Button成功");
                    break;
                }
                timeSleep(500);
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }
            timeSleep(waitSixSecond);
            className("android.widget.EditText").findOne().setText(doyinBean.getAccount());
            printLogMsg("输入抖音账号抖音号：" + doyinBean.getAccount() + "成功");
            timeSleep(waitThreeSecond);
            click(987, 115);
            printLogMsg("点击搜索按钮成功");
            timeSleep(waitSixSecond);
//            click(291, 241);
//            printLogMsg("点击搜索结果第一个");
//            timeSleep(waitTwoSecond);
            loopTime = 0;
            while (true) {
                if (className("Button").text("用户").exists()) {
                    className("Button").text("用户").findOne().click();
                    printLogMsg("点击用户按钮成功");
                    break;
                }
                timeSleep(500);
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }
            printLogMsg("点击用户分类成功");
            timeSleep(waitSixSecond);


//            className("RecyclerView").find().filter(new UiCollection.FilterCondition<AccessibilityNodeInfo>() {
//                @Override
//                public boolean shouldKeep(AccessibilityNodeInfo item) {
//                    AccessibilityNodeInfo accessibilityNodeInfo = item.getChild(0);
//                    AccUtils.clickNodeByPoint(accessibilityNodeInfo);
//                    printLogMsg("点击搜索到的第一个用户头像成功");
//                    return false;
//                }
//            });
            click(520, 370);
            printLogMsg("点击搜索到的第一个用户头像成功");
            timeSleep(waitSixSecond);

            loopTime = 0;
            while (true) {
                if (className("ImageView").desc("关闭").exists()) {
                    className("ImageView").desc("关闭").findOne().click();
                }
                if (className("TextView").text("关注").exists()) {
//                className("TextView").text("关注").findOne().click();
                    id("q1y").className("TextView").findOne().click();
                    printLogMsg("点击关注按钮成功");
                    break;
                }
                timeSleep(500);
                loopTime++;
                if (loopTime > 10) {
                    back();
                    timeSleep(waitTwoSecond);
                    if (className("TextView").text("关注").exists()) {
                        id("q1y").className("TextView").findOne().click();
                        printLogMsg("点击关注按钮成功");
                    }
                    break;
                }
            }


            timeSleep(waitSixSecond);

            if (className("Button").text("知道了").exists()) {
                id("button1").className("Button").text("知道了").findOne().click();
                timeSleep(waitTwoSecond);
            }
            boolean canMessage = true;

            loopTime = 0;
            while (true) {
                if (className("TextView").desc("私信").exists()) {
                    className("TextView").desc("私信").findOne().click();
                    printLogMsg("点击私信按钮成功");
                    break;
                }
                loopTime++;
                timeSleep(500);
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    printLogMsg("没有检测到私信按钮，即将返回到APP主页");
                    for (int i = 0; i < 5; i++) {
                        printLogMsg("执行第" + i + "次返回");
                        back();
                        timeSleep(waitTwoSecond);
                    }
                    canMessage = false;
                    break;
                }
            }
            if (!canMessage) {
                break;
            }

            timeSleep(waitSixSecond);

            loopTime = 0;
            while (true) {

                if (className("ImageView").descContains("拍摄").exists()) {
                    break;
                }
                if (className("ImageView").descContains("会话").exists()) {
                    className("ImageView").descContains("会话").findOne().click();
                    printLogMsg("点击会话按钮成功");
                    break;
                }
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }


            timeSleep(waitTwoSecond);
            click(395, 1810);
            printLogMsg("点击评论按钮成功");
            timeSleep(waitTwoSecond);
            int sixinNum = new Random().nextInt(bean.getData().getMsgList().size() - 1);
            className("android.widget.EditText").findOne().setText(bean.getData().getMsgList().get(sixinNum).getMsg());
            printLogMsg("输入私信内容成功");
            timeSleep(waitTwoSecond);
            loopTime = 0;
            while (true) {
                if (className("ImageView").desc("发送").exists()) {
                    className("ImageView").desc("发送").findOne().click();
                    printLogMsg("点击发送按钮成功");
                    break;
                }
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }
            timeSleep(waitTwoSecond);


            for (int i = 0; i < 6; i++) {
                if (className("LinearLayout").desc("已送达").exists()) {
                    printLogMsg("私信发送成功");
                    break;
                }
                timeSleep(waitTwoSecond);
            }


            if (className("TextView").text("刚刚").exists()) {
                printLogMsg("私信发送成功");
            }
            timeSleep(waitTwoSecond);
            //从未完成队列里移除此条数据
            updStatusByAccount(doyinBean.getAccount());
            DataUtils.getInstance().getDataDTOList().remove(bean);
            printLogMsg("执行完当前私信脚本，即将返回到APP主页");
            for (int i = 0; i < 5; i++) {
                printLogMsg("执行第" + i + "次返回");
                back();
                timeSleep(waitTwoSecond);
            }
            while (true) {
                if (className("TextView").desc("首页，按钮").exists()) {
                    printLogMsg("成功返回到首页，终止返回！");
                    break;
                }
                back();
                timeSleep(waitOneSecond);
                jumpApp("com.ss.android.ugc.aweme");
            }


        }
        printLogMsg("脚本执行完毕");
    }


    //


    /**
     * 清除后台此条数据
     */
    private String onUpdStatusByAccountUrl = baseUrl + "client/updStatusByAccount";
    private OkManager manager;

    public void jumpApp(String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent it = packageManager.getLaunchIntentForPackage(packageName);
        ActivityUtils.getTopActivity().startActivity(it);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updStatusByAccount(String account) {
        if (manager == null) {
            manager = OkManager.getInstance();
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("deviceID", DeviceUtils.getUniqueDeviceId());
        map.put("token", SPUtils.getInstance().getString(Constant.TOKEN, ""));
        map.put("account", account);
        manager.sendComplexForm(onUpdStatusByAccountUrl, map, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("Tag", jsonObject.toString());
                OnOffBean bean = JSON.parseObject(jsonObject.toString(), OnOffBean.class);
                if (bean.getCode() == 200) {
                    printLogMsg("账号" + account + ":此条账号消费成功");
                } else {
                    printLogMsg("账号" + account + ":消费失败");
                }

            }
        });

    }

}

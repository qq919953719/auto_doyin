package com.mcz.douyin.activitys;

import static com.mcz.douyin.config.GlobalVariableHolder.*;
import static com.mcz.douyin.node.AccUtils.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.mcz.douyin.R;
import com.mcz.douyin.config.GlobalVariableHolder;
import com.mcz.douyin.config.WindowPermissionCheck;
import com.mcz.douyin.script.TaskDemo;
import com.mcz.douyin.service.MyService;
import com.mcz.douyin.ui.FunctionActivity;
import com.mcz.douyin.utils.TxTManager;
import com.cmcy.rxpermissions2.RxPermissions;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    File patch_signed_7zip = null;
    private String readFromTxt;
    private boolean isAgreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        printLogMsg("onCreate() is run");

        // 存储权限
        storagePermissions();

        // 获取手机信息
        // getPhoneInfo();

        // 开启前台服务 未适配低版本安卓
        openForwardService();

        // 从 sdcard 读取版本号和抖音id号
        readIDFromSdcard();

        setContentView(R.layout.activity_main);

        // 在其他应用上层显示


        buildAdapter();

        initDisplay();
    }


    // 任务开始入口


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(MainActivity.this)) {
                Toast.makeText(this.getApplicationContext(), "悬浮窗授权失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getApplicationContext(), "悬浮窗授权成功", Toast.LENGTH_SHORT).show();
                // 打开悬浮窗
                startService(new Intent(GlobalVariableHolder.context, FloatingButton.class));
                // 打开悬浮窗
                startService(new Intent(GlobalVariableHolder.context, FloatingWindow.class));
            }
        }
    }

    BasePopupView popupView;

    private void mianze(String result, View view) {
        popupView = new XPopup.Builder(this).isDestroyOnDismiss(true)
//                        .isTouchThrough(true)
//                        .dismissOnBackPressed(false)
//                        .isViewMode(true)
//                        .hasBlurBg(true)
//                         .autoDismiss(false)
//                        .popupAnimation(PopupAnimation.NoAnimation)
                .asConfirm("免责条款", "本项目仅供学习、分享与交流，我们不保证内容的正确性，" + "禁止使用本项目进行商业化与违反法律法规的操作。下载试用后请 24 小时内删除，" + "因下载本站资源造成的损失，全部由使用者本人承担！根据 署名-非商业性使用-相同方式共享 (by-nc-sa) 许可协议规定，" + "只要他人在以原作品为基础创作的新作品上适用同一类型的许可协议，" + "并且在新作品发布的显著位置，注明原作者的姓名、来源及其采用的知识共享协议，与该作品在本网站的原发地址建立链接，" + "他人就可基于非商业目的对原作品重新编排、修改、节选或者本人的作品为基础进行创作和发布。" + "基于原作品创作的所有新作品都要适用同一类型的许可协议，因此适用该项协议， " + "对任何以他人原作为基础创作的作品自然同样都不得商业性用途。\n点击确定即代表您已同意本协议与声明！", "取消", "确定", new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        isAgreen = true;
                        switchResult(result, view);

                    }
                }, new OnCancelListener() {
                    @Override
                    public void onCancel() {
                        isAgreen = false;
                        ToastUtils.showShort("您已拒绝本协议与声明，无法继续使用");
                    }
                }, false);
        popupView.show();
    }


    private void buildAdapter() {
        //2、绑定控件
        ListView listView = (ListView) findViewById(R.id.list_view);
        //3、准备数据
        String[] data = {
                //"版本号 => 2.1.6",
                "开始运行", "开启无障碍",
                //"ANDROID_ID: " + Variable.ANDROID_ID,
                //"PHONE_NAME: " + Variable.PHONE_NAME,
                //"load fix patch",
                "跳转到设置无障碍页面",};
        //4、创建适配器 连接数据源和控件的桥梁
        //参数 1：当前的上下文环境
        //参数 2：当前列表项所加载的布局文件
        //(android.R.layout.simple_list_item_1)这里的布局文件是Android内置的，里面只有一个textview控件用来显示简单的文本内容
        //参数 3：数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        //5、将适配器加载到控件中
        listView.setAdapter(adapter);
        //6、为列表中选中的项添加单击响应事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String result = ((TextView) view).getText().toString();
                if (isAgreen) {
                    switchResult(result, view);
                } else {
                    mianze(result, view);

                }

            }
        });
    }

    private void switchResult(String result, View view) {
        boolean permission = WindowPermissionCheck.checkPermission(this);
        if (permission) {
            printLogMsg("onCreate: permission true => " + permission);
            // 打开悬浮窗
            startService(new Intent(GlobalVariableHolder.context, FloatingButton.class));
            // 打开悬浮窗
            startService(new Intent(GlobalVariableHolder.context, FloatingWindow.class));
        }
        // 跳转无障碍页面
        if (result.equals("跳转到设置无障碍页面")) {
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            return;
        }

        if (result.equals("开启无障碍")) {
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            return;
        }


        //判断无障碍是否开启
        if (!isAccessibilityServiceOn()) {
            ToastUtils.showShort("请开启无障碍模式");
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        } else {
            // 初始化
            if (result.equals("init webSocket")) {
                // 移动悬浮窗
                btnTextView.setText("全屏");
                Intent intent = new Intent();
                intent.setAction("com.msg");
                intent.putExtra("msg", "show_max");
                context.sendBroadcast(intent);
            }
            if (result.equals("开始运行")) {
                startActivity(new Intent(MainActivity.this, FunctionActivity.class));
                return;
            }


        }
    }

    public void initDisplay() {
        DisplayMetrics dm = new DisplayMetrics();//屏幕度量
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        defaultDisplay.getRealMetrics(dm);
        mWidth = dm.widthPixels;//宽度
        mHeight = dm.heightPixels;//高度
        DisplayMetrics __dm = new DisplayMetrics();//屏幕度量
        getWindowManager().getDefaultDisplay().getMetrics(__dm);
        __mHeight = __dm.heightPixels;//去掉导航栏和状态栏的高度
    }

    private void readIDFromSdcard() {
        // 判断有没有 FATJS_DIR 目录  没有则创建
        File dirFile = new File(Environment.getExternalStorageDirectory() + "/FATJS_DIR");
        if (!dirFile.exists()) {
            if (dirFile.mkdir()) {
                printLogMsg("onCreate: FATJS_DIR 目录创建成功");
            }
        }

        // 读取version.txt的版本号
        File versionFile = new File(Environment.getExternalStorageDirectory() + "/FATJS_DIR/version.txt");
        readFromTxt = readOrCreateVersion(versionFile.getAbsolutePath());
        readFromTxt = readFromTxt.trim();
    }

    private void openForwardService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void getPhoneInfo() {
        // 获取 ANDROID_ID
        GlobalVariableHolder.ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        printLogMsg("attachBaseContext: ANDROID_ID--->" + GlobalVariableHolder.ANDROID_ID);

        // 获取手机名称
        String phoneName = getPhoneName();
        GlobalVariableHolder.PHONE_NAME = phoneName;
        printLogMsg("onCreate: phoneName => " + phoneName);
    }

    // 获取 sdcard 读写权限
    private void storagePermissions() {
        // 存储权限
        RxPermissions.request(this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(granted -> {
            if (granted) {
                //权限允许
                readIDFromSdcard();
            } else {
                //权限拒绝
                Toast.makeText(this.getApplicationContext(), "存储授权失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String readOrCreateVersion(String absolutePath) {
        String fromXML = TxTManager.readFromTxt(absolutePath);
        if (fromXML == null || fromXML.equals("")) {
            TxTManager.writeToTxt(absolutePath, "2.1.6");
            return "2.1.6";
        }
        return fromXML;
    }

    // 获取本机名称
    private String getPhoneName() {
        return Settings.Secure.getString(getContentResolver(), "bluetooth_name"); // 手机名称
    }

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FATJS_DIR/patch_signed_7zip.apk";


    // 广播
    DataReceiver dataReceiver = null;
    private static final String ACTIONR = "com.jumpUid";

    @Override
    protected void onStart() {//重写onStart方法
        super.onStart();

        if (dataReceiver == null) {
            dataReceiver = new DataReceiver();
            IntentFilter filter = new IntentFilter();//创建IntentFilter对象
            filter.addAction(ACTIONR);
            registerReceiver(dataReceiver, filter);//注册Broadcast Receiver
        }

    }

    // 广播内部类
    public class DataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            GlobalVariableHolder.broadcast_map.put("jumpUid", false);
            printLogMsg("onReceive广播: " + intent.getAction());
            printLogMsg("onReceive: param -> " + intent.getStringExtra("tem"));

            // UID跳转
            Intent intentToUri = new Intent();
            intentToUri.setData(Uri.parse("snssdk1128://user/profile/" + intent.getStringExtra("tem")));
            startActivity(intentToUri);
        }
    }

    // 判断本程序的无障碍服务是否已经开启
    public Boolean isAccessibilityServiceOn() {
        try {
            String packageName = this.getPackageName();
            String service = packageName + "/" + packageName + ".MyAccessibilityService";
            int enabled = Settings.Secure.getInt(GlobalVariableHolder.context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
            TextUtils.SimpleStringSplitter splitter = new TextUtils.SimpleStringSplitter(':');
            if (enabled == 1) {
                String settingValue = Settings.Secure.getString(GlobalVariableHolder.context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
                if (settingValue != null) {
                    splitter.setString(settingValue);
                    while (splitter.hasNext()) {
                        String accessibilityService = splitter.next();
                        if (accessibilityService.equals(service)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
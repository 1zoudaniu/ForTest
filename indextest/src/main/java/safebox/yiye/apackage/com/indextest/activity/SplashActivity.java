package safebox.yiye.apackage.com.indextest.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import safebox.yiye.apackage.com.indextest.R;
import safebox.yiye.apackage.com.indextest.bean.HandlerContent;
import safebox.yiye.apackage.com.indextest.bean.UpdateInfo;
import safebox.yiye.apackage.com.indextest.constant.SpConstent;
import safebox.yiye.apackage.com.indextest.gloable.MyApplication;
import safebox.yiye.apackage.com.indextest.utils.APIClient;
import safebox.yiye.apackage.com.indextest.utils.ConnectUtil;
import safebox.yiye.apackage.com.indextest.utils.GetInfoUtils;
import safebox.yiye.apackage.com.indextest.utils.MyAnimationUtils;
import safebox.yiye.apackage.com.indextest.utils.NetUtils;
import safebox.yiye.apackage.com.indextest.utils.OnlyOneUtils;
import safebox.yiye.apackage.com.indextest.utils.SpUtils;
import safebox.yiye.apackage.com.indextest.utils.ToastDebugUtils;
import safebox.yiye.apackage.com.indextest.view.MyDialog;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private long startTimeMillis;
    //splash页面
    private RelativeLayout mAcRlSplash;
    //版本号
    private TextView mAcTvSplashVersion;
    //当前版本号
    private String currentVersion;
    /**
     * 是否取消下载
     */
    public static boolean cancelDownLoad = false;
    private UpdateInfo info;

    // 提示对话框界面和按钮
    private MyDialog alertDialog;
    private TextView mTextViewMsg;
    private Button mButtonOK;
    private Button mButtonNO;
    /**
     * 显示下载的进度条
     */
    private ProgressBar pd;
    /**
     * 下载的对话框
     */
    private Dialog downLoadDialog;


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case HandlerContent.REQUEST_UPDATE_APK_SUCCESS:
                    if (currentVersion.equals(info.getVersion())) {
                        toLogin();// 进入登录界面
                    } else {
                        // 显示提示下载的dialog
                        showUpdateDialog();
                    }
                    break;

                case HandlerContent.REQUEST_UPDATE_APK_ERROR:
                    ToastDebugUtils.showShortMsg(SplashActivity.this, "请求更新信息失败");
                    toLogin();// 进入登录界面
                    break;

                case HandlerContent.REQUEST_DOWNLOAD_APK_SUCCESS:
                    if (cancelDownLoad) {// 取消下载
                        toLogin();
                    } else {// 成功下载最新apk
                        finish();
                        APIClient.installApk(SplashActivity.this);
                    }
                    break;
                case HandlerContent.REQUEST_DOWNLOAD_APK_ERROR:
                    ToastDebugUtils.showShortMsg(SplashActivity.this, "下载apk失败");
                    toLogin();// 进入登录界面
                    break;
                case HandlerContent.TO_LOGIN:
                    finish();
                    startActivity(new Intent(SplashActivity.this,
                            LoginActivity.class));// 进入登录界面
                    break;
            }
            return false;
        }
    });

    /**
     * 显示提示下载的dialog
     */
    protected void showUpdateDialog() {
        ((MyApplication)getApplication()).setNewVersion(false);
        showAlertDialog();
        mTextViewMsg.setText("修复了一些bug，完善了一些功能");
        mButtonNO.setVisibility(View.VISIBLE);
        mButtonNO.setText("下次再说");
        mButtonOK.setText("立即下载");
    }


    /**
     * 弹出提示对话框
     */
    private void showAlertDialog() {
        View view = View.inflate(this, R.layout.view_alertdialog, null);
        mTextViewMsg = (TextView) view.findViewById(R.id.tv_alert_msg);
        mButtonOK = (Button) view.findViewById(R.id.btn_alert_ok);
        mButtonNO = (Button) view.findViewById(R.id.btn_alert_no);
        alertDialog = new MyDialog(this, 0, 0, view, R.style.dialog);
        alertDialog.setCancelable(false);
        alertDialog.show();
        mButtonOK.setOnClickListener(this);
        mButtonNO.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        /**
         * 记录一进来的时间
         */
        startTimeMillis = System.currentTimeMillis();
        mAcRlSplash = (RelativeLayout) findViewById(R.id.ac_rl_splash);
        mAcTvSplashVersion = (TextView) findViewById(R.id.ac_tv_splash_version);
// 显示透明度动画
        MyAnimationUtils.startAnimation(mAcRlSplash);
        currentVersion = GetInfoUtils.getVersion(this);
        mAcTvSplashVersion.setText("版本号：" + currentVersion);
        // 检查版本更新
        checkUpdate();

    }

    /**
     * 检测更新
     */
    private void checkUpdate() {
        //判断手机是否联网
        boolean connected = NetUtils.isConnected(this);
        if (connected) {
            //连接网络就更新
            startCheckUpadate();
        } else {
            //否则直接跳转到登录
            ToastDebugUtils.showShortMsg(this, "手机没有联网");
            toLogin();
        }
    }

    /**
     * 直接跳转到登录
     */
    private void toLogin() {
        ToastDebugUtils.showShortMsg(this, " 直接跳转到登录");
        long time = System.currentTimeMillis();
        if (time - startTimeMillis < 2000) {
            handler.sendEmptyMessageDelayed(HandlerContent.TO_LOGIN, 2000 - time + startTimeMillis); // 延迟进入登录界面
        } else {
            handler.sendEmptyMessage(HandlerContent.TO_LOGIN); // 立即进入
        }
    }
    /**
     * 联网检查当前版本是否为最新版本
     */
    private void startCheckUpadate() {
        new Thread() {
            public void run() {
                // 得到最新版本的信息对象
                try {
                    info = APIClient.getUpdateInfo(SplashActivity.this);
                    ((MyApplication) (getApplication())).setNewVersion(info
                            .getVersion());
                    ToastDebugUtils.d("info=" + info);
                    handler.sendEmptyMessage(HandlerContent.REQUEST_UPDATE_APK_SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(HandlerContent.REQUEST_UPDATE_APK_ERROR);
                }
            }
        }.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        //防止用户疯狂点击
        if(OnlyOneUtils.isFastClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.btn_alert_no:
                alertDialog.dismiss();
                toLogin();
                break;
            case R.id.btn_alert_ok:
                alertDialog.dismiss();
                startDownloadApk();
                break;
        }
    }

    /**
     * 开始下载apk(在分线程)
     */
    private void startDownloadApk() {
        // apk的下载地址
        final String apkUrl = SpUtils.getInstance(this).getString(
                SpConstent.BASE_URL, null)
                + ConnectUtil.URL_NEW_APK;
        // 创建保存apk的file对象
        APIClient.createApkFile(this);
        // 显示下载进度的ProgressDialog
        showDownloadDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    APIClient.downloadApk(apkUrl, pd);
                    handler.sendEmptyMessage(HandlerContent.REQUEST_DOWNLOAD_APK_SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(HandlerContent.REQUEST_DOWNLOAD_APK_ERROR);
                }
            }
        }).start();
    }
    /**
     * 显示下载进度的ProgressDialog
     */
    private void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("下载中....");
        builder.setCancelable(false);
        View view = View.inflate(this, R.layout.downloaddialog, null);
        pd = (ProgressBar) view.findViewById(R.id.pb_update);
        Button button = (Button) view.findViewById(R.id.btn_cancle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoadDialog.dismiss();
                // 取消下载
                cancelDownLoad = true;
            }
        });
        builder.setView(view);
        downLoadDialog = builder.create();
        downLoadDialog.show();
        // 开始下载(没有取消下载)
        cancelDownLoad = false;
    }
}

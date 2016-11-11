package safebox.yiye.apackage.com.indextest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lidroid.xutils.http.RequestParams;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import safebox.yiye.apackage.com.indextest.R;
import safebox.yiye.apackage.com.indextest.bean.LoginBackBean;
import safebox.yiye.apackage.com.indextest.constant.SpConstent;
import safebox.yiye.apackage.com.indextest.gloable.MyApplication;
import safebox.yiye.apackage.com.indextest.utils.ConnectUtil;
import safebox.yiye.apackage.com.indextest.utils.NetUtils;
import safebox.yiye.apackage.com.indextest.utils.OnlyOneUtils;
import safebox.yiye.apackage.com.indextest.utils.ParseUtils;
import safebox.yiye.apackage.com.indextest.utils.SpUtils;
import safebox.yiye.apackage.com.indextest.utils.ToastDebugUtils;
import safebox.yiye.apackage.com.indextest.view.MyProgressDialog;

public class LoginActivity extends AppCompatActivity {
    private RelativeLayout mRlLogin;
    private ImageView mAcIvLogin;
    private LinearLayout mLlLogin;
    private EditText mAcEtLoginUsername;
    private EditText mAcEtLoginPwd;
    private Button mAcBtnLogin;
    private String mDeviceId;
    private Animation mAnimation;
    private SpUtils mSpUtils;
    private NetUtils mNetUtils;
    private MyProgressDialog mMyProgressDialog;
    private String mUseName;
    private String mPwd;
    private boolean mIsConnected;
    private String mLoginUrl;

    private int condition = 0;
    private final int LOGIN_ACTION = 0X001;
    private final int SAVE_ACTION = 0X002;
    private MyOnResponseListener mMyOnResponseListener;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        mDeviceId = manager.getDeviceId();
//        ButterKnife.bind(this);
        assignViews();

        initData();
        mAcBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginJudge();
            }
        });
        setListener();

    }
    private void assignViews() {
        mRlLogin = (RelativeLayout) findViewById(R.id.rl_login);
        mAcIvLogin = (ImageView) findViewById(R.id.ac_iv_login);
        mLlLogin = (LinearLayout) findViewById(R.id.ll_login);
        mAcEtLoginUsername = (EditText) findViewById(R.id.ac_et_login_username);
        mAcEtLoginPwd = (EditText) findViewById(R.id.ac_et_login_pwd);
        mAcBtnLogin = (Button) findViewById(R.id.ac_btn_login);
    }

    private void setListener() {
        mMyOnResponseListener = new MyOnResponseListener();
        mNetUtils.setmOnResponseNetFinishListener(mMyOnResponseListener);
    }

    private void initData() {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        mSpUtils = SpUtils.getInstance(this);
        mNetUtils = new NetUtils();
        mMyProgressDialog = MyProgressDialog.createDialog(this);
        mUseName = mSpUtils.getString(SpUtils.USER_NAME, null);
        mPwd = mSpUtils.getString(SpUtils.PWD, null);
        mAcEtLoginUsername.setText(mUseName+"s");
        mAcEtLoginPwd.setText(mPwd+"ss");
    }


    /**
     * 登录判断
     */
    private void loginJudge() {

        mUseName = mAcEtLoginUsername.getText().toString();
        mPwd = mAcEtLoginPwd.getText().toString();
        if ("".equals(mUseName)) {
            mAcEtLoginUsername.startAnimation(mAnimation);
            ToastDebugUtils.showShortMsg(this, "用户名不能为空");
            return;
        } else if ("".equals(mPwd)) {
            mAcEtLoginPwd.startAnimation(mAnimation);
            ToastDebugUtils.showShortMsg(this, "密码不能为空");
            return;
        } else {
            try {
                // 判断网络状态
                judgeNetWork();
                // 启动分线程联网请求
                if (mIsConnected) {
                    login();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 启动分线程联网请求
     */
    private void login() {
        // 启动联网进度条，联网请求
        mMyProgressDialog.show();

        // 登录请求数据
        RequestParams params = new RequestParams();
        params.addBodyParameter("username", mUseName);
        // 加密密码
        String pwdMD5 = "";
        try {
            pwdMD5 = OnlyOneUtils.encode(mPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 加密
        params.addBodyParameter("password", pwdMD5);
        params.addBodyParameter("fromWhere", "android");
        mLoginUrl = mSpUtils.getString(SpConstent.BASE_URL, "")
                + ConnectUtil.LOGIN_URL;
        condition = LOGIN_ACTION;
        mNetUtils.getNetGetRequest(mLoginUrl, params);

    }

    private void judgeNetWork() {
        mIsConnected = NetUtils.isConnected(this);
        if (!mIsConnected) {
            ToastDebugUtils.showShortMsg(this, "请检查网络设置");
        }
    }

    class MyOnResponseListener implements NetUtils.OnResponseNetFinishListener {
        @Override
        public void onResponseSucceed(String result, String url) {
            mMyProgressDialog.dismiss();
            switch (condition) {
                case LOGIN_ACTION:
                    final LoginBackBean loginBackBean = ParseUtils.parseJson(
                            result, LoginBackBean.class);
                    if (loginBackBean.getState() == 1) {// 登录成功
                        mSpUtils.put(SpUtils.USER_NAME, mUseName);
                        mSpUtils.put(SpUtils.PWD, mPwd);
                        // 将菜单保存起来
                        ((MyApplication) getApplication())
                                .setDataEntity(loginBackBean.getData());
                        ((MyApplication) getApplication()).setLoginTag(1);
                        if ("客户".equals(((MyApplication) getApplication())
                                .getDataEntity().getCurrentOperatorType())) {
//                            Intent intent = new Intent(LoginActivity.this,
//                                    ClientHomeActivity.class);
//                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(LoginActivity.this,
                                    HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        ToastDebugUtils.showShortMsg(LoginActivity.this,
                                loginBackBean.getMessage());
                    }
                    break;
            }
        }

        // 请求失败
        @Override
        public void onResponseFailure(String result, String url) {
            mMyProgressDialog.dismiss();
            if (result.contains("timed out")
                    || result.contains("TimeoutException")) {
                ToastDebugUtils.showShortMsg(LoginActivity.this, "连接超时，请稍候重试！");
            } else if (result.contains("refused")) {
                ToastDebugUtils.showShortMsg(LoginActivity.this, "检查服务器是否开启");
            } else {
                ToastDebugUtils.showShortMsg(LoginActivity.this, "验证失败");
                Intent intent = new Intent(LoginActivity.this,
                        HomeActivity.class);
                startActivity(intent);
                finish();

            }
        }
    }
}

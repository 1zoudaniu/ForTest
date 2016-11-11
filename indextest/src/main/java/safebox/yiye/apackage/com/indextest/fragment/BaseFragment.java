package safebox.yiye.apackage.com.indextest.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import safebox.yiye.apackage.com.indextest.R;
import safebox.yiye.apackage.com.indextest.constant.SpConstent;
import safebox.yiye.apackage.com.indextest.gloable.MyApplication;
import safebox.yiye.apackage.com.indextest.utils.NetUtils;
import safebox.yiye.apackage.com.indextest.utils.SpUtils;
import safebox.yiye.apackage.com.indextest.utils.ToastDebugUtils;
import safebox.yiye.apackage.com.indextest.view.MyDialog;
import safebox.yiye.apackage.com.indextest.view.MyProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    public MyProgressDialog myProgressDialog;
    public String sessionId;
    public NetUtils netUtil;
    public String base_url;
    public int condition;
    boolean isConnected;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
//        sessionId = ((MyApplication) mActivity.getApplication())
//                .getDataEntity().getSessionId();
        netUtil = new NetUtils();
        SpUtils spUtils = SpUtils.getInstance(mActivity);
        base_url = spUtils.getString(SpConstent.BASE_URL, null);
        ToastDebugUtils.i(getFragmentName(), " onAttach()");
    }
    /**
     * 判断网络状态
     */
    public void judgeNetWork() {
        isConnected = NetUtils.isConnected(mActivity);
        if (!isConnected) {
            ToastDebugUtils.showShortMsg(mActivity, "请检查网络设置");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToastDebugUtils.i(getFragmentName(), " onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ToastDebugUtils.i(getFragmentName(), " onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ToastDebugUtils.i(getFragmentName(), " onViewCreated()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ToastDebugUtils.i(getFragmentName(), " onActivityCreated()");
        judgeNetWork();
        myProgressDialog = MyProgressDialog.createDialog(mActivity);
    }

    @Override
    public void onStart() {
        super.onStart();
        ToastDebugUtils.i(getFragmentName(), " onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();

        ToastDebugUtils.i(getFragmentName(), " onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        ToastDebugUtils.i(getFragmentName(), " onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        ToastDebugUtils.i(getFragmentName(), " onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ToastDebugUtils.i(getFragmentName(), " onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ToastDebugUtils.i(getFragmentName(), " onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ToastDebugUtils.i(getFragmentName(), " onDetach()");
    }

    /**
     * fragment name
     */
    public abstract String getFragmentName();

    // 提示对话框界面和按钮
    public MyDialog alertDialog;
    public TextView mTextViewMsg;
    public Button mButtonOK;
    public Button mButtonNO;

    /**
     * 弹出提示对话框
     */
    public void showAlertDialog() {
        View view = LayoutInflater.from(mActivity).inflate(
                R.layout.view_alertdialog, null);
        mTextViewMsg = (TextView) view.findViewById(R.id.tv_alert_msg);
        mButtonOK = (Button) view.findViewById(R.id.btn_alert_ok);
        mButtonNO = (Button) view.findViewById(R.id.btn_alert_no);
        alertDialog = new MyDialog(mActivity, 0, 0, view, R.style.dialog);
        alertDialog.show();
    }

}

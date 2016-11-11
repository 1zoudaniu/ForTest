package safebox.yiye.apackage.com.indextest.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import safebox.yiye.apackage.com.indextest.R;
import safebox.yiye.apackage.com.indextest.constant.SpConstent;
import safebox.yiye.apackage.com.indextest.fragment.CarFragment;
import safebox.yiye.apackage.com.indextest.fragment.FindFragment;
import safebox.yiye.apackage.com.indextest.fragment.HomeFragment;
import safebox.yiye.apackage.com.indextest.fragment.MineFragment;
import safebox.yiye.apackage.com.indextest.utils.ToastDebugUtils;
import safebox.yiye.apackage.com.indextest.view.MyTabWidget;

public class HomeActivity extends FragmentActivity implements
        MyTabWidget.OnTabSelectedListener {
    private MyTabWidget mTabWidget;
    private HomeFragment mHomeFragment;
    private FindFragment mFindFragment;
    private CarFragment mCarFragment;
    //	private ClassifyFragment mClassifyFragment;
    private MineFragment mMineFragment;
    private int mIndex = SpConstent.HOME_FRAGMENT_INDEX;
    private FragmentManager mFragmentManager;
    private mReturnToHome returnToHome;
    int index = 0;
    public boolean isUpdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        initEvents();
    }

    private void init() {
        mFragmentManager = getSupportFragmentManager();
        mTabWidget = (MyTabWidget) findViewById(R.id.tab_widget);
//        MyActivityManager.getInstance().pushOneActivity(this);
    }

    private void initEvents() {
        mTabWidget.setOnTabSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        index = getIntent().getIntExtra("INDEX", 0);
        if (index != 0) {
            mIndex = index;
            mTabWidget.setVisibility(View.GONE);
        } else {
            mTabWidget.setVisibility(View.VISIBLE);
        }
        onTabSelected(mIndex, 0);
        mTabWidget.setTabsDisplay(this, mIndex);
        showIndicateDisplay();

    }

    /**
     * 显示指示点
     */
    private void showIndicateDisplay() {
        // List<OrdersInfo> ordersList = ((ZcApplication) this.getApplication())
        // .getOrdersList();
        // if (ordersList.size() > 0) {
        // // 设置指示点是否显示
        // int count = 0;
        // for (int i = 0; i < ordersList.size(); i++) {
        // count += ordersList.get(i).getGoodsAmount();
        // }
        // mTabWidget.setIndicateDisplay(this, 1, true, count + "");
        // } else {
        // mTabWidget.setIndicateDisplay(this, 1, false, "");
        // }
    }

    class MyTableSelectListener implements HomeFragment.TableSelectListener {
        @Override
        public void tableSelect(int position, int tag) {
//			mTabWidget.setTabsDisplay(ClientHomeActivity.this, position);
            onTabSelected(position, tag);
        }

    }

    @Override
    public void onTabSelected(int index, int tag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case SpConstent.HOME_FRAGMENT_INDEX:
                if (null == mHomeFragment) {
                    MyTableSelectListener listener = new MyTableSelectListener();
                    mHomeFragment = new HomeFragment();
                    mHomeFragment.setTableSelectListener(listener);
                    transaction.add(R.id.center_layout, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case SpConstent.QUERY_FRAGMENT_INDEX:
                if (null == mFindFragment) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("TAG", tag);
                    mFindFragment = new FindFragment();
                    returnToHome = new mReturnToHome();
                    mFindFragment.setToHome(returnToHome);
                    mFindFragment.setArguments(bundle);
                    transaction.add(R.id.center_layout, mFindFragment);
                } else {
                    transaction.show(mFindFragment);
                }
                break;
            case SpConstent.SHOPCAR_FRAGMENT_INDEX:
                if (null == mCarFragment) {
                    mCarFragment = new CarFragment();
                    transaction.add(R.id.center_layout, mCarFragment);
                } else {
                    transaction.show(mCarFragment);
                }
                break;
//		case SpConstent.CLASSIFY_FRAGMENT_INDEX:// 分类
//			if (null == mClassifyFragment) {
//				mClassifyFragment = new ClassifyFragment();
//				transaction.add(R.id.center_layout, mClassifyFragment);
//			} else {
//				transaction.show(mClassifyFragment);
//			}
//			break;
            case SpConstent.SETTING_FRAGMENT_INDEX:
                if (null == mMineFragment) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.center_layout, mMineFragment);
                } else {
                    transaction.show(mMineFragment);
                }
                break;

            default:
                break;
        }
        mIndex = index;
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }
        if (null != mFindFragment) {
            transaction.hide(mFindFragment);
        }
        if (null != mCarFragment) {
            transaction.hide(mCarFragment);
        }
//		if (null != mClassifyFragment) {
//			transaction.hide(mClassifyFragment);
//		}
        if (null != mMineFragment) {
            transaction.hide(mMineFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 自己记录fragment的位置,防止activity被系统回收时，fragment错乱的问题
        // super.onSaveInstanceState(outState);
        outState.putInt("index", mIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // super.onRestoreInstanceState(savedInstanceState);
        mIndex = savedInstanceState.getInt("index");
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (index != 0) {
                finish();
            } else {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    ToastDebugUtils.showShortMsg(this, "再按一次退出程序");
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CarFragment.REQUESTCODE
//                && resultCode == ShopOrderActivity.RESULTCODE) {
//            isUpdate = false;
//        } else {
//            isUpdate = true;
//        }
    }

    class mReturnToHome implements FindFragment.ReturnToHome {

        @Override
        public void toHome() {
            mTabWidget.setVisibility(View.VISIBLE);
            onTabSelected(0, 0);
            mTabWidget.setTabsDisplay(HomeActivity.this, 0);
        }
    }
}

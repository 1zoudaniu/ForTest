package safebox.yiye.apackage.com.indextest.gloable;

import android.app.Application;
;

import safebox.yiye.apackage.com.indextest.bean.LoginBackBean;
import safebox.yiye.apackage.com.indextest.bean.LoginBackBean.DataEntity;
/**
 * Name: MyApplication
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 15:10
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // 登录之后返回的数据
    private DataEntity dataEntity;

    // 是否是最新的版本
    private boolean isNewVersion = true;

    public boolean isNewVersion() {
        return isNewVersion;
    }

    public void setNewVersion(boolean isNewVersion) {
        this.isNewVersion = isNewVersion;
    }
    //是否登陆的标识
    private int LoginTag;

    public int getLoginTag() {
        return LoginTag;
    }

    public void setLoginTag(int loginTag) {
        LoginTag = loginTag;
    }


    /**
     * 当前最新的版本号
     */
    private String NewVersion;


    public String getNewVersion() {
        return NewVersion;
    }

    public void setNewVersion(String newVersion) {
        NewVersion = newVersion;
    }


    public DataEntity getDataEntity() {
        return dataEntity;
    }

    public void setDataEntity(DataEntity dataEntity) {
        this.dataEntity = dataEntity;
    }

}

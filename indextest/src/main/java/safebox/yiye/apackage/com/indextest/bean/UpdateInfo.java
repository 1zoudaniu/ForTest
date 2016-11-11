package safebox.yiye.apackage.com.indextest.bean;

/**
 * Name: UpdateInfo
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 14:55
 * 封装最新应用相关信息的类
 */
public class UpdateInfo {
    private String version;

    public UpdateInfo(String version) {
        super();
        this.version = version;
    }

    public UpdateInfo() {
        super();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UpdateInfo [version=" + version + "]";
    }
}

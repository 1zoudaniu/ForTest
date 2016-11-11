package safebox.yiye.apackage.com.indextest.view;

import android.app.Dialog;
import android.content.Context;

import safebox.yiye.apackage.com.indextest.R;

/**
 * Name: MyProgressDialog
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 18:00
 */
public class MyProgressDialog extends Dialog {
    private static MyProgressDialog myProgressDialog = null;

    private MyProgressDialog(Context context) {
        super(context);
    }

    private MyProgressDialog(Context context, boolean cancelable,
                             OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public MyProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static MyProgressDialog createDialog(Context context) {
        myProgressDialog = new MyProgressDialog(context,
                R.style.CustomProgressDialog);
        myProgressDialog.setCanceledOnTouchOutside(false);
        myProgressDialog.setContentView(R.layout.myprogressdialog);
        return myProgressDialog;
    }
}

package cn.dgl.www.step.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * @ClassName ProgressDialog
 */
public class DialogUtil {

    private static ProgressDialog progressDialog;
    protected AlertDialog mAlertDialog;

    public static void showProgressDialog(Context mContext, int resId) {
        showProgressDialog(mContext, mContext.getString(resId));
    }

    public static void showProgressDialog(Context mContext, String message) {
        //此处必关必申请！想重复用就在BaseActivity中封装私有dialog，封装成所有activity用就要区分mContext是否同一个！
        closeProgressDialog();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        if (null != progressDialog
                && !progressDialog.isShowing()
                && !((Activity)mContext).isFinishing()) {//检查activity是否finishing!!!
            progressDialog.show();
        }
    }

    //不可取消的对话框,用在积分请求中，防止用户重复点击签到
    public static void showProgressDialogCancle(Context mContext, String message) {
        closeProgressDialog();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false);//不可取消
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        if (null != progressDialog && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public static void setPogressDialogCancelable() {
        if (null != progressDialog) {
            progressDialog.setCancelable(true);
        }
    }

    public static void setDialogCancelable(boolean cancelable) {
        if (null != progressDialog) {
            progressDialog.setCancelable(cancelable);
        }
    }

    public static void closeProgressDialog() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;//此处一定置空，否则就容易导致下一个Activity show闪退！！！
    }

    public static boolean isProgressDialogShowing() {
        if (null != progressDialog && progressDialog.isShowing()) {
            return true;
        }
        return false;
    }

    /**
     * 创建Dialog
     */
    public void createDialog(Context mContext, String title, String msg) {
        mAlertDialog = new AlertDialog.Builder(mContext).create();
        mAlertDialog.setTitle(title);
        // mAlertDialog.setIcon(android.R.drawable.ic_dialog_info);
        mAlertDialog.setMessage(msg);
        mAlertDialog.setCancelable(false);
    }

}

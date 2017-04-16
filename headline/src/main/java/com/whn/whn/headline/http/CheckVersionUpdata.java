package com.whn.whn.headline.http;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.whn.whn.headline.MainActivity;
import com.whn.whn.headline.SplashActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by somnus-sir on 2017/4/16.
 */

public class CheckVersionUpdata  implements HttpInterface{
    private boolean KEY_ENTER = true;
    private static final int REQUEST_CODE_INSTALL = 111;
    private String target;
    Activity activity;
    private String desc;
    private TextView tv_Version;
    private int code;
    private String downloadurl;
    public CheckVersionUpdata(Activity activity){
        this.activity = activity;
    }
    /**
     * 联网检查版本
     */
    private void checkVersion() {
        HttpHelper httpHelper = new HttpHelper();
        httpHelper.execGet(url, new HttpHelper.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                // json解析
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    int versionCode = jsonObject.getInt("version");// 服务器版本号
                    desc = jsonObject.getString("desc");// 更新信息
                    downloadurl = jsonObject.getString("downloadurl");// 下载地址
                    if (code < versionCode) {// 需要更新,弹出一个对话框
                        showAlertDialog();
                    } else {// 不需要更新,进入主界面
                        enterHome();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(Exception e) {
                enterMainActivity();
                activity.finish();
            }
        });
    }

    /**
     * 获取版本号
     */
    private int getCode() {
        PackageManager manager = activity.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 获取版本名称
     */
    private String getName() {
        PackageManager manager = activity.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 显示对话框
     */
    protected void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("版本更新提醒");
        builder.setMessage(desc);
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadapk();// 下载apk

            }
        });

        builder.setNegativeButton("稍后更新", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome();
            }
        });
        builder.show();
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                enterHome();
            }
        });
    }

    /**
     * 下载apk,显示进度条
     */
    protected void downloadapk() {
        // 先判断sd卡状态是否正常
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            enterHome();
            Toast.makeText(activity, "sd卡异常", Toast.LENGTH_SHORT).show();
            return;
        }
        // 进度条
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        target = "/mnt/sdcard/B/headline.apk";
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.download(downloadurl, target, new RequestCallBack<File>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                dialog.setMax((int) total);
                dialog.setProgress((int) current);
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(ResponseInfo<File> arg0) {
                // 下载成功,就获取文件,跳转到安装界面
                File result = arg0.result;
                dialog.dismiss();
                installapk(result);
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                Toast.makeText(activity, "下载失败", Toast.LENGTH_SHORT).show();
                enterHome();
                dialog.dismiss();
            }
        });
    }

    /**
     * 隐式意图跳转到安装apk,然后跳转到主页面
     */
    protected void installapk(File result) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        Uri uri = Uri.fromFile(result);// 要安装数据的uri
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        // 跳转安装页面
        activity.startActivityForResult(intent, REQUEST_CODE_INSTALL);
    }
    /**
     * 进入主页面
     */
    protected void enterHome() {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
    }

    public void enterMainActivity(){
        if(KEY_ENTER){
            enterHome();
            KEY_ENTER = false;
        }
        activity.finish();
    }

}

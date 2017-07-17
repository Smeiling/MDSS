package com.mdss.smeiling.mdss.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.controller.activity.MainActivity;
import com.mdss.smeiling.mdss.controller.activity.SingleActivity;

import java.util.Arrays;

/**
 * Created by songmeiling on 2016/1/19.
 * 对话框工具类，用于弹出不同对话框
 */
public class DialogUtil {

    //测试选择
    public static void choiceDialog(final Activity activity, final Context paramContext) {
        new android.app.AlertDialog.Builder(paramContext).setTitle(R.string.Test_TestFinished).setMessage(R.string.Test_TestFinished_Des).setCancelable(false).setPositiveButton(R.string.layout_pass, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                activity.setResult(Activity.RESULT_OK);
                activity.finish();
            }
        }).setNegativeButton(R.string.layout_fail, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                activity.setResult(Activity.RESULT_CANCELED);
                activity.finish();
            }
        }).show();
    }

    //错误提示
    public static void errorDialog(final Activity activity, final Context paramContext, final Boolean cate) {
        if (cate) {
            new android.app.AlertDialog.Builder(paramContext).setTitle(R.string.Speaker_Test_HeadsetFound).setMessage(R.string.Speaker_Test_HeadsetFound_Des).setCancelable(false).setNeutralButton(R.string.layout_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    activity.setResult(Activity.RESULT_CANCELED);
                    activity.finish();
                }
            }).show();
        } else {
            new android.app.AlertDialog.Builder(paramContext).setTitle(R.string.Headset_Test_NoHeadsetFound).setMessage(R.string.Headset_Test_NoHeadsetFound_Des).setCancelable(false).setNeutralButton(R.string.layout_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    activity.setResult(Activity.RESULT_CANCELED);
                    activity.finish();
                }
            }).show();
        }

    }

    //reset选择
    public static void resetDialog(final Context paramContext) {
        new android.app.AlertDialog.Builder(paramContext).setTitle(R.string.broadcast_title_Reset).setMessage(R.string.Menu_Setting_Reset_Des).setCancelable(false).setPositiveButton(R.string.layout_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                Arrays.fill(MainActivity.testResult, 0);
                Arrays.fill(MainActivity.testResultNum, 0);
                Arrays.fill(SingleActivity.testSingleResult, 0);
                FileUtil.newfile(paramContext);
            }
        }).setNegativeButton(R.string.layout_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
            }
        }).show();
    }

    //测试选择
    public static void noticeDialog(final Activity activity, final Context paramContext) {
        new android.app.AlertDialog.Builder(paramContext)
                .setTitle(R.string.Test_TestFinished)
                .setMessage(R.string.Test_TestFinished_Des)
                .setCancelable(true)
                .setNegativeButton(R.string.layout_cancel, null).show();
    }

}


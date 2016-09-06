package com.ghx.handlepermission_60.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by guo_hx on 2016/9/6.20:33
 */
public class PermissionUtils {

    /**
     * 判断是否需要申请权限_by_ghx
     *
     * @param permisssion
     * @return
     */
    public static boolean isPermissionRequired(Context context, String permisssion) {

        boolean isPermisssionReq = ContextCompat.checkSelfPermission(context,
                permisssion) != PackageManager.PERMISSION_GRANTED;
        return isPermisssionReq;

    }
}

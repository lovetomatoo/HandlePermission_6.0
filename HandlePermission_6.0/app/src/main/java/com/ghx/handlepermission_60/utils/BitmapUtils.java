package com.ghx.handlepermission_60.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by guo_hx on 2016/9/5.20:59
 */
public class BitmapUtils {

    public static Bitmap decodeBitmap(String uri) {

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(uri);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
}

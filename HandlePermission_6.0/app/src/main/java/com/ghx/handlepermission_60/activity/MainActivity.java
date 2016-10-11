package com.ghx.handlepermission_60.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ghx.handlepermission_60.utils.BitmapUtils;
import com.ghx.handlepermission_60.utils.ExtendMediaPicker;
import com.ghx.handlepermission_60.utils.PermissionUtils;
import com.ghx.handlepermission_60.weiget.ActionSheetDialog;
import com.ghx.handlepermission_60.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //    来新公司马上三个月了，2.2的版本终于要上线了，真不容易啊，加班加班加班。
    /*
*
*          ┌─┐       ┌─┐
*       ┌──┘ ┴───────┘ ┴──┐
*       │                 │
*       │       ───       │
*       │  ─┬┘       └┬─  │
*       │                 │
*       │       ─┴─       │
*       │                 │
*       └───┐         ┌───┘
*           │         │
*           │         │
*           │         │
*           │         └──────────────┐
*           │                        │
*           │                        ├─┐
*           │                        ┌─┘
*           │                        │
*           └─┐  ┐  ┌───────┬──┐  ┌──┘
*             │ ─┤ ─┤       │ ─┤ ─┤
*             └──┴──┘       └──┴──┘
*                 神兽保佑
*                 代码无BUG!
*/

    private static final int MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_ALBUM = 1;
    private static final int MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_PHOTOGRAPH = 2;
    private ImageView mIvShow;
    private Button mBtnSelect;
    private ExtendMediaPicker mMediaPicker;
    private Button mBtnToRxPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();

    }

    private void initView() {
        mIvShow = (ImageView) findViewById(R.id.iv_show);
        mBtnSelect = (Button) findViewById(R.id.btn_select);
        mBtnToRxPermission = (Button) findViewById(R.id.btn_to_rxpermission);

    }

    private void initData() {

        mMediaPicker = new ExtendMediaPicker(this);

        mIvShow.setImageResource(R.mipmap.default_null);
    }

    private void initListener() {

        mBtnSelect.setOnClickListener(this);

        mBtnToRxPermission.setOnClickListener(this);

        mMediaPicker.setOnPicBackListener(new ExtendMediaPicker.PicBackListener() {
            @Override
            public void setPic(String imagePath) {

                //把图片的path回调回来————处理，将图片拿到__展示即可
                Bitmap photo = BitmapUtils.decodeBitmap(imagePath);
                mIvShow.setImageBitmap(photo);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select: {

                toPickPicture();
            }
            break;
            case R.id.btn_to_rxpermission: {
                startActivity(new Intent(MainActivity.this, RxPermissionActivity.class));
            }
            break;
        }
    }

    private void toPickPicture() {
        //这里遇到个问题，国产rom贼sb，（小米4，6.1系统），一开始，我测试的时候，读外部存储是危险权限，后来，卸载再安装，他都说不是了。。。
        //去设置里设置，根本没用！根本没用！
        //拿原生的rom一测，没问题。。。。。国产rom。。开发者的痛苦。。。。。。。。

        //上面这个问题，后来想了想。
        //原因肯定是小米rom的问题这个肯定没错。
        //但是为什么会这样呢?
        //应该是我同意此应用的危险权限以后，小米在某个地方记录了。去设置里修改，还是没有用，说明他不是每次都是设置里读取的！

        new ActionSheetDialog(this).builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("从相册选择", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                //判断权限
                                if (PermissionUtils.isPermissionRequired(MainActivity.this,
                                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                    //如果需要权限，则去申请
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_ALBUM);
                                } else {
                                    //做该做的事
                                    Toast.makeText(getApplicationContext(),
                                            "不是危险权限或者已经同意，直接访问相册",
                                            Toast.LENGTH_SHORT).show();

                                    mMediaPicker.showPickerView(false);
                                }

                            }
                        })
                //这里是调用系统相机，开始我以为是需要CAMERA权限的，but__这里只是去调用系统的相机功能去拍照，并没有使用CAMERA__API
                //所以并不需要权限。但是虽然不需要CAMERA权限，但是图片是从sd卡取得，所以同样要加上读外部存储的权限
                //这里，在公司项目里遇到了一个很坑爹的事情，开始，我只在这里加了外部存储权限，因为相机的权限是不需要的
                //但是后来，公司业务牵扯到录像功能，涉及到相机的权限了，这里，竟然崩了！！我这里和原来没有区别，别的地方用到了相机权限，关这里
                //啥事，为什么这里会crash？？
                //只能解释为：项目中需要相机权限了，这里，也要加上相机权限的动态申请了。。。好无奈TAT
                //所以，这里，就要申请读外部存储和相机两个权限了。。。（这里不涉及，如果遇到类似问题，再申请下相机权限即可）
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                //判断权限
                                if (PermissionUtils.isPermissionRequired(MainActivity.this,
                                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                    //如果需要权限，则去申请
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_PHOTOGRAPH);
                                } else {
                                    //做该做的事
                                    Toast.makeText(getApplicationContext(),
                                            "不是危险权限或者已经同意，直接访问相机",
                                            Toast.LENGTH_SHORT).show();

                                    mMediaPicker.showPickerView(true);
                                }
                            }
                        })
                .show();
    }


    /**
     * 处理权限申请回调_ghx
     *
     * @param requestCode  请求码 The request code passed in requestPermissions(String[], int).
     * @param permissions  权限组 The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_ALBUM:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "是危险权限，回调访问相册",
                            Toast.LENGTH_SHORT).show();
                    mMediaPicker.showPickerView(false);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "需要权限，请在设置－>全部应用中打开",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_PHOTOGRAPH:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "是危险权限，回调访问相机",
                            Toast.LENGTH_SHORT).show();
                    mMediaPicker.showPickerView(true);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "需要权限，请在设置－>全部应用中打开",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:

                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    /**
     * 调用系统相册，相机__隐藏跳转的回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //这种代码，请不要写在Activity里，直接交给他自己处理
        mMediaPicker.onActivityResult(requestCode, resultCode, data);
    }
}

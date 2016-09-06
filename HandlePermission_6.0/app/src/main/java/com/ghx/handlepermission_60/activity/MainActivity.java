package com.ghx.handlepermission_60.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ghx.handlepermission_60.utils.ExtendMediaPicker;
import com.ghx.handlepermission_60.weiget.ActionSheetDialog;
import com.ghx.handlepermission_60.R;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //    来新公司马上三个月了，2.3的版本终于要上线了，真不容易啊，加班加班加班。我只想
    private static final int MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_ALBUM = 1;
    private static final int MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_PHOTOGRAPH = 2;
    private ImageView mIvShow;
    private Button mBtnSelect;
    private ExtendMediaPicker mMediaPicker;

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

    }

    private void initData() {

        mMediaPicker = new ExtendMediaPicker(this);

        mIvShow.setImageResource(R.mipmap.default_null);
    }

    private void initListener() {

        mBtnSelect.setOnClickListener(this);

        mMediaPicker.setOnPicBackListener(new ExtendMediaPicker.PicBackListener() {
            @Override
            public void setPic(String imagePath) {
                //把图片的path回调回来————处理，将图片拿到__展示即可
                //TODO...
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
        }
    }

    private void toPickPicture() {
        new ActionSheetDialog(this).builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("从相册选择", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

//                                //判断权限
//                                if (isPermissionRequired(MainActivity.this,
//                                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                                    //如果需要权限，则去申请
//                                    ActivityCompat.requestPermissions(MainActivity.this,
//                                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                                            MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_ALBUM);
//                                } else {
//                                    //做该做的事
//                                    Toast.makeText(getApplicationContext(),
//                                            "访问相册",
//                                            Toast.LENGTH_SHORT).show();

                                mMediaPicker.showPickerView(false);
                            }

//                            }
                        })
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

//                                //判断权限
//                                if (isPermissionRequired(MainActivity.this,
//                                                Manifest.permission.CAMERA)) {
//                                    //如果需要权限，则去申请
//                                    ActivityCompat.requestPermissions(MainActivity.this,
//                                            new String[]{Manifest.permission.CAMERA},
//                                            MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_PHOTOGRAPH);
//                                } else {
//                                    //做该做的事
                                Toast.makeText(getApplicationContext(),
                                        "访问相机",
                                        Toast.LENGTH_SHORT).show();

                                mMediaPicker.showPickerView(true);
//                                }
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
                    mMediaPicker.showPickerView(false);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "需要权限，请在设置－>全部应用中打开",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_PHOTOGRAPH:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        mMediaPicker.onActivityResult(requestCode, resultCode, data);
    }
}

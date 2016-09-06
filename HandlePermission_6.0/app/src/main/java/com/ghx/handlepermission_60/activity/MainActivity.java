package com.ghx.handlepermission_60.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ghx.handlepermission_60.utils.BitmapUtils;
import com.ghx.handlepermission_60.utils.MediaUtil;
import com.ghx.handlepermission_60.weiget.ActionSheetDialog;
import com.ghx.handlepermission_60.R;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_ALBUM = 1;
    private static final int MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_PHOTOGRAPH = 2;
    private static final int REQUEST_CODE_PICK_IMAGE = 100;
    private static final int REQUEST_CODE_TAKE_PHOTO = 200;
    private static final int REQUEST_CODE_CROP_PHOTO = 300;
    private ImageView mIvShow;
    private Button mBtnSelect;
    private Uri imageUri;
    private File tempFile;

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

        tempFile = createTempFile();
    }

    private void initData() {
        mIvShow.setImageResource(R.mipmap.default_null);
    }

    private void initListener() {

        mBtnSelect.setOnClickListener(this);
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

                                //判断权限
                                if (isPermissionRequired(MainActivity.this,
                                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                    //如果需要权限，则去申请
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_ALBUM);
                                } else {
                                    //做该做的事
                                    Toast.makeText(getApplicationContext(),
                                            "访问相册",
                                            Toast.LENGTH_SHORT).show();

                                    openSystemPickImage();
                                }

                            }
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

                                openSystemCamera();
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
                    openSystemPickImage();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "需要权限，请在设置－>全部应用中打开",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case MYPERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE_PHOTOGRAPH:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openSystemCamera();
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

    private boolean isPermissionRequired(Context context, String permisssion) {

        boolean isPermisssionReq = ContextCompat.checkSelfPermission(context,
                permisssion) != PackageManager.PERMISSION_GRANTED;
        return isPermisssionReq;

    }

    private void openSystemPickImage() {
        Intent photoPickerIntent = null;

        if (Build.VERSION.SDK_INT < 19) {
            photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            photoPickerIntent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }

        photoPickerIntent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        try {
            startActivityForResult(photoPickerIntent,
                    REQUEST_CODE_PICK_IMAGE);

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "请检查你手机的系统相册", Toast.LENGTH_SHORT).show();
        }

    }

    private void openSystemCamera() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File dirPath = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            imageUri = Uri.fromFile(dirPath);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent,
                        REQUEST_CODE_TAKE_PHOTO);
            }


        } else {
            Toast.makeText(getApplicationContext(),
                    "Before you take photos please insert SD card",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {

            return;
        }

        switch (requestCode) {
            case REQUEST_CODE_PICK_IMAGE:
                String path = MediaUtil.getPath(this, data.getData());

                cropImageUri(Uri.fromFile(new File(path)), 300, 300,
                        REQUEST_CODE_CROP_PHOTO);

                break;
            case REQUEST_CODE_TAKE_PHOTO:

                cropImageUri(imageUri, 300, 300, REQUEST_CODE_CROP_PHOTO);

                break;
            case REQUEST_CODE_CROP_PHOTO:

                String imagePath = tempFile.getAbsolutePath();

                handImage(imagePath);

                break;
        }

    }

    private void handImage(String imagePath) {
        Bitmap photo = BitmapUtils.decodeBitmap(imagePath);
        mIvShow.setImageBitmap(photo);
    }


    private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {
        /*if (isCamera) {

            sendMessage(uri.getPath());

            return;
        }*/

        try {
            // android1.6以后只能传图库中图片
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", outputX);
            intent.putExtra("outputY", outputY);
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
            intent.putExtra("return-data", false);
            intent.putExtra("outputFormat",
                    Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
                startActivityForResult(intent, requestCode);

        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "Can not find image crop app", Toast.LENGTH_SHORT).show();
        }
    }


    private File createTempFile() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return new File(Environment.getExternalStorageDirectory(),
                    "image.jpg");
        } else {
            return new File(getFilesDir(), "image.jpg");
        }
    }
}

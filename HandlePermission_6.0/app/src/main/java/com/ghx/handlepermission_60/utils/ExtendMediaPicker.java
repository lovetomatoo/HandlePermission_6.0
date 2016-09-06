package com.ghx.handlepermission_60.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

/**
 * Created by guo_hx on 2016/9/6.20:08
 *
 * 调用系统相机，系统相册类
 *
 */
public class ExtendMediaPicker {

    private static final int REQUEST_CODE_CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE = 3;
    private static final int REQUEST_CODE_TAKE_PHOTO = 4;

    private Uri imageUri;
    private File tempFile;

    private Activity mActivity;

    PicBackListener mListener;

    public ExtendMediaPicker(Activity activity) {

        this.mActivity = activity;

        this.tempFile = createTempFile();
    }

    public void showPickerView(boolean isCamera) {

        if (isCamera) {

            openSystemCamera();

        } else {

            openSystemPickImage();
        }


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {

            return;
        }

        switch (requestCode) {
            case REQUEST_CODE_PICK_IMAGE:
                String path = MediaUtil.getPath(mActivity, data.getData());

                cropImageUri(Uri.fromFile(new File(path)), 300, 300,
                        REQUEST_CODE_CROP_PHOTO);

                break;
            case REQUEST_CODE_TAKE_PHOTO:

                cropImageUri(imageUri, 500, 500, REQUEST_CODE_CROP_PHOTO);

                break;
            case REQUEST_CODE_CROP_PHOTO:

                String imagePath = tempFile.getAbsolutePath();

                mListener.setPic(imagePath);

                break;
        }
    }

    @SuppressLint("InlinedApi")
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
            mActivity.startActivityForResult(photoPickerIntent,
                    REQUEST_CODE_PICK_IMAGE);

        } catch (Exception e) {

            Toast.makeText(mActivity, "请检查你手机的系统相册", Toast.LENGTH_SHORT).show();
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
            if (intent.resolveActivity(mActivity.getPackageManager()) != null) {
                mActivity.startActivityForResult(intent,
                        REQUEST_CODE_TAKE_PHOTO);
            }

        } else {

            Toast.makeText(mActivity, "Before you take photos please insert SD card", Toast.LENGTH_SHORT).show();
        }
    }

    private File createTempFile() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return new File(Environment.getExternalStorageDirectory(),
                    "image.jpg");
        } else {
            return new File(mActivity.getFilesDir(), "image.jpg");
        }
    }

    /**
     * 截取图像部分区域
     *
     * @param uri
     * @param outputX
     * @param outputY
     * @return
     */
    private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {

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
            if (mActivity == null) {
                mActivity.startActivityForResult(intent, requestCode);
            } else {
                mActivity.startActivityForResult(intent, requestCode);
            }

        } catch (ActivityNotFoundException ex) {
            Toast.makeText(mActivity, "Can not find image crop app", Toast.LENGTH_SHORT).show();
        }
    }

    /**取到图片后的接口回调-----------------------------------------------------------------------------------*/

    public void setOnPicBackListener(PicBackListener listener) {
        this.mListener = listener;
    }


    public interface PicBackListener {
        void setPic(String imagePath);

    }


}

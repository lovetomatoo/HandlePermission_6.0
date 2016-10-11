
# HandlePermission_6.0
6.0权限处理__动态申请权限

#第一个页面，用最普遍的Google提供的方法做权限申请：
  1.判断是否需要申请
  
    若需要，那么去request这个权限
    
    若不需要，Do some thing.
    
  2.重写权限处理的回调 onRequestPermissionsResult
  
    处理

#第二个页面，用rxpermission处理权限

  1.    
    //RxJava
    
    compile 'io.reactivex:rxjava:1.1.8'
    
    compile 'io.reactivex:rxandroid:1.1.0'
    
  2. 
    compileOptions {//对lambda的支持
    
        sourceCompatibility JavaVersion.VERSION_1_8

        targetCompatibility JavaVersion.VERSION_1_8
        
    }
    
  3.
    代码
  
      RxPermissions.getInstance(RxPermissionActivity.this)
    
                                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                                
                                .subscribe(aBoolean -> {
                                
                                    if (aBoolean) {
                                    
                                        mMediaPicker.showPickerView(true);
                                        
                                        Toast.makeText(getApplicationContext(), "是危险权限，回调访问相机", Toast.LENGTH_SHORT).show();
                                        
                                    } else {
                                    
                                        Toast.makeText(getApplicationContext(), "需要权限，请在设置－>全部应用中打开", Toast.LENGTH_SHORT).show();
                                    }
                                    
                                });
                                
       OVER,就不用重写回调了，方便了很多！                         
    






附：
Android Dangerous Permissions:

group:android.permission-group.CONTACTS
  permission:android.permission.WRITE_CONTACTS
  permission:android.permission.GET_ACCOUNTS
  permission:android.permission.READ_CONTACTS

group:android.permission-group.PHONE
  permission:android.permission.READ_CALL_LOG
  permission:android.permission.READ_PHONE_STATE
  permission:android.permission.CALL_PHONE
  permission:android.permission.WRITE_CALL_LOG
  permission:android.permission.USE_SIP
  permission:android.permission.PROCESS_OUTGOING_CALLS
  permission:com.android.voicemail.permission.ADD_VOICEMAIL

group:android.permission-group.CALENDAR
  permission:android.permission.READ_CALENDAR
  permission:android.permission.WRITE_CALENDAR

group:android.permission-group.CAMERA
  permission:android.permission.CAMERA

group:android.permission-group.SENSORS
  permission:android.permission.BODY_SENSORS

group:android.permission-group.LOCATION
  permission:android.permission.ACCESS_FINE_LOCATION
  permission:android.permission.ACCESS_COARSE_LOCATION

group:android.permission-group.STORAGE
  permission:android.permission.READ_EXTERNAL_STORAGE
  permission:android.permission.WRITE_EXTERNAL_STORAGE

group:android.permission-group.MICROPHONE
  permission:android.permission.RECORD_AUDIO

group:android.permission-group.SMS
  permission:android.permission.READ_SMS
  permission:android.permission.RECEIVE_WAP_PUSH
  permission:android.permission.RECEIVE_MMS
  permission:android.permission.RECEIVE_SMS
  permission:android.permission.SEND_SMS
  permission:android.permission.READ_CELL_BROADCASTS

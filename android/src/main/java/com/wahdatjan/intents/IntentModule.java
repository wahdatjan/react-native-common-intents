package com.wahdatjan.intents;

import android.app.Activity;
import android.provider.AlarmClock;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import javax.annotation.Nonnull;

public class IntentModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private static final int PICK_IMAGE = 1;
    private static final int GALERY_INTENT = 2;


    private Callback pickerSuccessCallback;
    private Callback pickerCancelCallback;

    ReactApplicationContext context = getReactApplicationContext();

    public IntentModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        context.addActivityEventListener(this);
    }

    @Nonnull
    @Override
    public String getName() {
        return "RNIntents";
    }





    @ReactMethod
    public void ImagePick(Callback successCallback, Callback cancelCallback) {
        Activity currentActivity = getCurrentActivity();

        if (currentActivity == null) {
            cancelCallback.invoke("Activity doesn't exist");
            return;
        }
        pickerSuccessCallback = successCallback;
        pickerCancelCallback = cancelCallback;

        try{
            if (Build.VERSION.SDK_INT <19){

                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                final   Intent chooserIntent  = Intent.createChooser(galleryIntent,"Pick an Image");
                currentActivity.startActivityForResult(chooserIntent,PICK_IMAGE);

            }

            else {
                Intent intent = new Intent(android.content.Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                currentActivity.startActivityForResult(intent, GALERY_INTENT);
            }
        } catch (Exception e) {
            cancelCallback.invoke(e);
        }


    }
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

        if (pickerSuccessCallback !=null){
            if (resultCode == Activity.RESULT_CANCELED){
                pickerCancelCallback.invoke("Image Picker was cancelled");
            } else if (resultCode == Activity.RESULT_OK){
                Uri uri = data.getData();
                Log.d("Tagg",""+uri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                    try {
                        getReactApplicationContext().getContentResolver().takePersistableUriPermission(uri,Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    }
                    catch (SecurityException e){
                        e.printStackTrace();
                    }
                }


                if (uri == null){
                    pickerCancelCallback.invoke("No image data found");
                }
                else {
                    try {
                        pickerSuccessCallback.invoke(uri.toString());
                    }catch (Exception e){
                        pickerCancelCallback.invoke("No Image Data Found");
                    }

                }
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @ReactMethod
    public void openWebPage(String url){
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @ReactMethod
    public void openDateSettings(){
        Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @ReactMethod
    public void openWifiSettings(){
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @ReactMethod
    public void openBluetoothSettings(){
        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @ReactMethod
    public void openAirplaneModeSettings(){
        Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @ReactMethod
    public void performWebSearch(String query){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(SearchManager.QUERY, query);
            context.startActivity(intent);
        }
    }

    @ReactMethod
    public void dialNumber(String number){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" +number));
        if (intent.resolveActivity(context.getPackageManager()) !=null){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getReactApplicationContext().startActivity(intent);
        }
    }
   
   @ReactMethod
    public void startTimer(String message, int seconds,boolean value) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, value);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        
    }

    @ReactMethod
    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}

package com.snehpandya.aad.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by sneh.pandya on 26/09/17.
 */

public class MyTestService extends IntentService {

    public static final String ACTION = "com.snehpandya.aad.service.MyTestService";

    public MyTestService() {
        super("test_service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("TAG", "onHandleIntent: IntentService started");
        String value = intent.getStringExtra("Hello");
        Intent intent1 = new Intent(ACTION);
        intent1.putExtra("resultCode", Activity.RESULT_OK);
        intent1.putExtra("resultValue", "Result value is: " + value);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
    }
}

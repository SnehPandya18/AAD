package com.snehpandya.aad.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.snehpandya.aad.util.JobUtil;

/**
 * Created by sneh.pandya on 26/09/17.
 */

public class MyStartServiceReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        JobUtil.scheduleJob(context);
    }
}

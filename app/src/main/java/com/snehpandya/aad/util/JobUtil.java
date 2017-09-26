package com.snehpandya.aad.util;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.snehpandya.aad.service.TestJobService;

/**
 * Created by sneh.pandya on 26/09/17.
 */

public class JobUtil {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void scheduleJob(Context context) {
        ComponentName componentName = new ComponentName(context, TestJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, componentName);
        builder.setMinimumLatency(1000 * 10);
        builder.setOverrideDeadline(3 * 1000 * 60);
        builder.setRequiresCharging(false);

        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());
    }
}

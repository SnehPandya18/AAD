package com.snehpandya.aad.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.snehpandya.aad.util.JobUtil;

/**
 * Created by sneh.pandya on 26/09/17.
 */

public class TestJobService extends JobService {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Intent intent = new Intent(getApplicationContext(), MyTestService.class);
        getApplicationContext().startService(intent);
        JobUtil.scheduleJob(getApplicationContext()); //Reschedule the job
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}

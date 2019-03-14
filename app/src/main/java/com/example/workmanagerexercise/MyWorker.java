package com.example.workmanagerexercise;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

// creating worker class for work manager
public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        displayNotification("Work Manager", "Work Manager Notification");
//        The result of the Worker's computation. can be FAILURE or RETRY also
        return Result.SUCCESS;
    }

    private void displayNotification(String task, String desc) {
//        Class to notify the user of events that happen.  This is how you tell
//        the user that something has happened in the background.
        NotificationManager manager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        // check android version for channel support
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simple_channel", "simple_channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        //defining notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "simple_channel" )
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.drawable.ic_launcher_foreground);

        manager.notify(1, builder.build());
    }
}

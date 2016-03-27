package client.app.clientapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import client.app.clientapp.service.GCMIntentService;

public class MyBroadcastReceiver extends WakefulBroadcastReceiver {
    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        ComponentName comp = new ComponentName(context.getPackageName(), GCMIntentService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        Toast.makeText(context.getApplicationContext(), "GCM push received by clientapp.", Toast.LENGTH_LONG).show();
    }
}

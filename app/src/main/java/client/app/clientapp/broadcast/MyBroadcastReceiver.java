package client.app.clientapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


//       / Toast.makeText(context.getApplicationContext(), "GCM push received by clientapp.", Toast.LENGTH_LONG).show();
    }
}

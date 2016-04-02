package client.app.clientapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.widget.Toast;

import client.app.clientapp.utils.Constants;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    public SmsBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Client App SMS Received", Toast.LENGTH_LONG).show();

        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get("pdus");
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                Toast.makeText(context, "" + address + "\n" + smsBody, Toast.LENGTH_LONG).show();

                if (!TextUtils.isEmpty(smsBody) && !TextUtils.isEmpty(address)) {
                    if (address.contains(Constants.PHONE) && smsBody.contains(Constants.ACTIVATION_VALIDATION_MSG)) {

                    }
                }
            }
        }
    }
}

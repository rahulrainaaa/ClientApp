package client.app.clientapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import client.app.clientapp.utils.Constants;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    Context ctxt;
    public SmsBroadcastReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Client App SMS Received", Toast.LENGTH_LONG).show();

        ctxt = context;
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

    public class registerTask extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL(Constants.REGISTER_DEVICE + "?CATEGORY=default");
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();
                if (statusCode ==  200)
                {
                    InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader read = new InputStreamReader(it);
                    BufferedReader buff = new BufferedReader(read);
                    StringBuilder dta = new StringBuilder();
                    String chunks ;
                    while((chunks = buff.readLine()) != null)
                    {
                        dta.append(chunks);
                    }
                    return dta.toString();
                }
                else
                {
                    return "ERROR";
                }

            }
            catch (Exception e)
            {
                return "EXCEPTION";
            }

        }

        @Override
        protected void onPostExecute(String s) {

            if(s.contains("EXCEPTION") || s.contains("ERROR"))
            {

            }
            else
            {

            }
            Toast.makeText(ctxt, "" + s, Toast.LENGTH_LONG).show();
        }
    }


}

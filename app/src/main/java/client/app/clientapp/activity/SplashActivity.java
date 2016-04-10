package client.app.clientapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.HttpClientConnection;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import client.app.clientapp.R;
import client.app.clientapp.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    public GoogleCloudMessaging gcm = null;
    public String msg = "";
    public EditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        editText = (EditText)findViewById(R.id.editText);

        SharedPreferences s = getSharedPreferences("info_cache", 0);
        String pref = s.getString("REGISTER_GCM", "NO").trim();

        GCMRegistrar.checkDevice(getApplicationContext());

        if(ConnectionResult.SUCCESS == GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext()))
        {
            if(pref.equals("NO"))
            {
               information info = new information();
                info.execute("");
            }
            else
            {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                        finish();
                    }
                }, 1200);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Error: Google Cloud Messaging not supported.", Toast.LENGTH_LONG).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                    finish();
                }
            }, 2000);
        }
    }

    public class information extends AsyncTask<String, String, String>
    {
       @Override
        protected void onPreExecute() {
           Toast.makeText(getApplicationContext(), "Registering registering device for first time", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {

            try
            {
                //Register GCM first
                if (gcm == null)
                {
                    gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                }
                msg = "" + gcm.register(Constants.PROJECT_NUMBER.toString().trim());

                if(msg.contains("invalid")
                        || msg.contains("error")
                        || msg.contains("exception")
                        || msg.contains("fail")
                        || msg.contains("misplace")
                        || msg.contains("missing")
                        || msg.contains("exceed"))
                {
                    return "ERROR";
                }

               return msg.toString();

            }
            catch (Exception e)
            {
                return "ERROR";
            }
        }

        @Override
        protected void onPostExecute(String result) {

            editText.setText("" + result.toString());
            if(result.equals("ERROR"))
            {
                Toast.makeText(getApplicationContext(), "Check internet connection.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                SharedPreferences.Editor se = getSharedPreferences("info_cache",0).edit();
                se.putString("REGISTER_GCM","YES");
                se.putString("GCM_ID", "" + msg.toString());
                se.commit();
            }
            startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
            finish();
        }
    }
}
package client.app.clientapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

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

        GCMRegistrar.checkDevice(getApplicationContext());

        if(ConnectionResult.SUCCESS == GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext()))
        {
            information info = new information();
            info.execute("");
        }
        else
        {
            startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
            finish();
            Toast.makeText(getApplicationContext(), "Error: Google Cloud Messaging not supported.", Toast.LENGTH_LONG).show();
        }

    }

    public void updateDetails()
    {

    }

    public class information extends AsyncTask<String, String, String>
    {
       @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {

            try
            {
                if (gcm == null)
                {
                    gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                }
                msg = "" + gcm.register(Constants.PROJECT_NUMBER.toString().trim());


            }
            catch (Exception e) {
                msg = "Error :" + e.getMessage();

            }
            return msg.toString();
        }

        @Override
        protected void onPostExecute(String result) {

            editText.setText("" + result.toString());
           // startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
            //finish();
        }
    }
}
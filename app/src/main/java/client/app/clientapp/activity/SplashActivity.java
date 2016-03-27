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

import org.json.JSONObject;

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
        String pref = s.getString("REGISTER_GCM", "NO");

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
                //YES GCM is registered.
            }
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
                //Register GCM first
                if (gcm == null)
                {
                    gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                }
                msg = "" + gcm.register(Constants.PROJECT_NUMBER.toString().trim());

                if(msg.equalsIgnoreCase("invalid")
                        || msg.equalsIgnoreCase("error")
                        || msg.equalsIgnoreCase("misplace")
                        || msg.equalsIgnoreCase("missing")
                        || msg.equalsIgnoreCase("exceed"))
                {
                    return "ERROR";
                }

                //Hit REST-POST webservice to update GCM_ID

                //HttpClient client = new DefaultHttpClient();



                return msg;
            }
            catch (Exception e)
            {
                return "EXCEPTION";
            }
        }

        @Override
        protected void onPostExecute(String result) {

            editText.setText("" + result.toString());
            if(result.equals("EXCEPTION")  || result.equals("ERROR"))
            {

            }
            else
            {
                // startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                //finish();
            }
        }
    }
}
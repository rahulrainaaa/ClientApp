package client.app.clientapp.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ASyncActivateDevice extends AsyncTask<String, String, String> {

    private Context context;

    public ASyncActivateDevice(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            URL url = new URL(Constants.REGISTER_DEVICE);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode ==  200)
            {
                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("DEVICEID", "device_id_1");
                OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
                osw.write(jsonRequest.toString());
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

        try {
            JSONObject response = new JSONObject(s.toString());
            if (response.getString("statusCode").contains("200"))
            {
                SharedPreferences.Editor se = context.getSharedPreferences("info_cache", 0).edit();
                se.putString("ACTIVATE_SMS","YES");
                se.commit();
                Toast.makeText(context, "Device Registered Successfully.", Toast.LENGTH_SHORT).show();
            }
            else if (response.getString("statusCode").contains("300"))
            {
                Toast.makeText(context, "" + response.getString("data").toString(), Toast.LENGTH_SHORT).show();
            }
            else if (response.getString("statusCode").contains("400"))
            {
                Toast.makeText(context, "" + response.getString("data").toString(), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(context, "Please check internet connection.", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(context, "" + s, Toast.LENGTH_LONG).show();

        super.onPostExecute("");
    }
}

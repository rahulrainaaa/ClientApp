package client.app.clientapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import client.app.clientapp.R;
import client.app.clientapp.adapter.NotificationAdapter;
import client.app.clientapp.model.Notification;

public class NotificationActivity extends AppCompatActivity {

    ListView lw;
    NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        SharedPreferences s = getSharedPreferences("info_push", 0);
        String data = s.getString("data", "[]");
        ArrayList<Notification> list = new ArrayList<Notification>();
        try
        {
            JSONArray jarray = new JSONArray(data);
            for(int i = 0; i < jarray.length(); i++)
            {
                Notification n = new Notification();
                n.title = "" + jarray.getJSONObject(i).getString("title");
                n.message = "" + jarray.getJSONObject(i).getString("message");
                n.date = "" + jarray.getJSONObject(i).getString("date");
                n.time = "" + jarray.getJSONObject(i).getString("time");
                list.add(n);
            }
        }
        catch (JSONException e) {

        }

        lw = (ListView)findViewById(R.id.listView11);
        adapter = new NotificationAdapter(this, getLayoutInflater(), list);
        lw.setAdapter(adapter);



    }

}

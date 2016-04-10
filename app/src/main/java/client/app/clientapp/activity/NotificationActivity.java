package client.app.clientapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import client.app.clientapp.R;
import client.app.clientapp.adapter.NotificationAdapter;

public class NotificationActivity extends AppCompatActivity {


    ListView lw;
    NotificationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        SharedPreferences s = getSharedPreferences("info_push", 0);
        String data = s.getString("data","[]");

        lw = (ListView)findViewById(R.id.listView);
        adapter = new NotificationAdapter(this, 4);
        lw.setAdapter(adapter);



    }

}

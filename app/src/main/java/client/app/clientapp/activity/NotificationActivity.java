package client.app.clientapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import client.app.clientapp.R;

public class NotificationActivity extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        t = (TextView)findViewById(R.id.textView2);
        SharedPreferences s = getSharedPreferences("info_push", 0);
        String data = s.getString("data","****");
        t.setText("" + data);
    }

}

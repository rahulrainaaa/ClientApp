package client.app.clientapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.zip.Inflater;

import client.app.clientapp.R;


public class NotificationAdapter extends ArrayAdapter<Integer> {

    Activity activity;
    LayoutInflater inflater;

    public NotificationAdapter(Activity activity, int count) {

        super(activity, R.layout.list_item_notification, count);

        this.activity = activity;
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.list_item_notification, null);

        return view;
    }
}

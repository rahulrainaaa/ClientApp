package client.app.clientapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import client.app.clientapp.R;
import client.app.clientapp.utils.Constants;


public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private ImageView imgNotification;
    private ImageView imgEmail;
    private ImageView imgCall;
    private ImageView imgWebsite;
    private ImageView imgLocation;

    Animation animNotification;
    Animation animEmail;
    Animation animCall;
    Animation animWeb;
    Animation animLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imgCall = (ImageView) findViewById(R.id.id_call);
        imgNotification = (ImageView) findViewById(R.id.id_notification);
        imgEmail = (ImageView) findViewById(R.id.id_email);
        imgWebsite = (ImageView) findViewById(R.id.id_website);
        imgLocation = (ImageView) findViewById(R.id.id_location);

        animNotification = AnimationUtils.loadAnimation(this, R.anim.anim_pop_notification);
        animEmail = AnimationUtils.loadAnimation(this, R.anim.anim_pop_mail);
        animCall = AnimationUtils.loadAnimation(this, R.anim.anim_pop_call);
        animWeb = AnimationUtils.loadAnimation(this, R.anim.anim_pop_web);
        animLocation = AnimationUtils.loadAnimation(this, R.anim.anim_pop_location);

        imgNotification.startAnimation(animNotification);
        imgEmail.startAnimation(animEmail);
        imgCall.startAnimation(animCall);
        imgWebsite.startAnimation(animWeb);
        imgLocation.startAnimation(animLocation);

        imgNotification.setOnClickListener(this);
        imgEmail.setOnClickListener(this);
        imgWebsite.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgLocation.setOnClickListener(this);

        imgNotification.setOnLongClickListener(this);
        imgEmail.setOnLongClickListener(this);
        imgWebsite.setOnLongClickListener(this);
        imgCall.setOnLongClickListener(this);
        imgLocation.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.id_call) {
            try {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Call Permission Denied.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String uri = "tel:" + Constants.PHONE;
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
                startActivity(callIntent);
            } catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "Cannot Make Call.", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId() == R.id.id_email)
        {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{Constants.MAIL});
            i.putExtra(Intent.EXTRA_SUBJECT, "");
            i.putExtra(Intent.EXTRA_TEXT   , "");
            try
            {
                startActivity(Intent.createChooser(i, ""));
            } catch (android.content.ActivityNotFoundException ex)
            {
                Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId() == R.id.id_location)
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DashboardActivity.this);
            alertDialogBuilder.setNegativeButton("OK", null);
            alertDialogBuilder.setPositiveButton("Show on Map", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=" + Constants.ADDRESS_LAT_LONG));
                    startActivity(intent);
                }
            });
            alertDialogBuilder.setIcon(R.drawable.icon_map_pin);
            alertDialogBuilder.setTitle("Address");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setMessage("\n" + Constants.ADDRESS_LINE_1 + "\n" + Constants.ADDRESS_LINE_2 + "\n" + Constants.ADDRESS_LINE_3 + "\n");
            alertDialog.show();
        }
        else if(v.getId() == R.id.id_notification)
        {

        }
        else if(v.getId() == R.id.id_website)
        {
            try
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.WEB));
                startActivity(browserIntent);
            } catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "Invalid URL\n Cannot open web.", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public boolean onLongClick(View v) {

        if(v.getId() == R.id.id_call)
        {
            Snackbar.make(v, "Call Us", Snackbar.LENGTH_LONG).show();
        }
        else if(v.getId() == R.id.id_email)
        {
            Snackbar.make(v, "Email Us", Snackbar.LENGTH_LONG).show();
        }
        else if(v.getId() == R.id.id_location)
        {
            Snackbar.make(v, "Address", Snackbar.LENGTH_LONG).show();
        }
        else if(v.getId() == R.id.id_notification)
        {
            Snackbar.make(v, "Offers and Notifications", Snackbar.LENGTH_LONG).show();
        }
        else if(v.getId() == R.id.id_website)
        {
            Snackbar.make(v, "Visit Us", Snackbar.LENGTH_LONG).show();
        }

        return true;
    }

}
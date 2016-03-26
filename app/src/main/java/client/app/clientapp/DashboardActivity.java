package client.app.clientapp;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


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

        imgCall = (ImageView)findViewById(R.id.id_call);
        imgNotification = (ImageView)findViewById(R.id.id_notification);
        imgEmail = (ImageView)findViewById(R.id.id_email);
        imgWebsite = (ImageView)findViewById(R.id.id_website);
        imgLocation = (ImageView)findViewById(R.id.id_location);

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


        if(v.getId() == R.id.id_call)
        {
            Toast.makeText(getApplicationContext(), "calling", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.id_email)
        {

        }
        else if(v.getId() == R.id.id_location)
        {

        }
        else if(v.getId() == R.id.id_notification)
        {

        }
        else if(v.getId() == R.id.id_website)
        {

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

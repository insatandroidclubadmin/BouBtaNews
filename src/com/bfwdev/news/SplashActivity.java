package com.bfwdev.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

public class SplashActivity extends Activity
{
 private static final int STOPSPLASH = 0;

    /**
     * Default duration for the splash screen (milliseconds)
     */
 private static final long SPLASHTIME = 3000;

    /**
     * Handler to close this activity and to start automatically {@link MainActivity}
     * after <code>SPLASHTIME</code> seconds.
     */
 private final transient Handler splashHandler = new Handler()
    {
	 @Override
	 public void handleMessage(Message msg)
	 {
	     if (msg.what == STOPSPLASH)
	     {
	         final Animation animation = AnimationUtils.loadAnimation(getBaseContext(),
	                                                                  android.R.anim.slide_out_right);
	         animation.setAnimationListener(new AnimationListener()
	         {
	             @Override public void onAnimationEnd(Animation animation)
	             {
	                 (findViewById(R.id.splash)).setVisibility(View.INVISIBLE);
	                 final Intent intent = new Intent(SplashActivity.this, Home.class);
	                 startActivity(intent);
	                 finish();
	             }

	             @Override public void onAnimationRepeat(Animation animation)
	             {
	                 // nothing to do ...
	             }

	             @Override public void onAnimationStart(Animation animation)
	             {
	                 // nothing to do ...
	             }
	         });

	         (findViewById(R.id.splash)).startAnimation(animation);
	     }

	     super.handleMessage(msg);
	 }
    };

    @Override
 protected void onCreate(Bundle savedInstanceState)
    {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.splash);

 final Message msg = new Message();
        msg.what = STOPSPLASH;
        splashHandler.sendMessageDelayed(msg, SPLASHTIME);
    }
}
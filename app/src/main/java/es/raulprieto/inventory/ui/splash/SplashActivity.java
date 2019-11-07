package es.raulprieto.inventory.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.ui.login.LoginActivity;


/***
 * Activity that shows the App Logo for WAIT_TIME seconds.
 * Using a Handler so the next Activity (login) is loading as we see the logo.
 * @author Raúl Prieto Bailón
 * @version 1.0
 */
public class SplashActivity extends AppCompatActivity {
    private static final long WAIT_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Comment the line above if using the layer-list drawable (no need to use a layout)
    }

    /**
     * This Runnable object runs the method's code outside the UI's thread
     */
    @Override
    protected void onStart() {
        super.onStart();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                initLogin();
            }
        };
        handler.postDelayed(runnable,WAIT_TIME);
    }


    //  Try/Catch Option, worse than handler
    // as handler does handle exceptions by itself
    /*@Override
    protected void onStart() {
        super.onStart();
            @Override
            public void run() {
                try {
                    Thread.sleep(WAIT_TIME);
                    initLogin();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }*/

    private void initLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

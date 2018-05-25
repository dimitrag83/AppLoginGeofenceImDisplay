package com.dimitra.goudela.AppLoginGeofence;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;

public class Splashscreen extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ShowSplashscreen();
    }
    private void ShowSplashscreen() {


        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    //καθυστέρηση 2 sec
                    //η Splashscreen παραμένει για 2 sec
                    sleep(2000);
                    //και στη συνέχεια περνά στην επόμενη οθόνη
                    Intent intent = new Intent(Splashscreen.this,MainActivity.class);
                    startActivity(intent);
                    Splashscreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();

    }

}
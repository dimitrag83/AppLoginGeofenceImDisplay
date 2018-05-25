package com.dimitra.goudela.AppLoginGeofence;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.util.Random;

import static com.dimitra.goudela.AppLoginGeofence.MainActivity.clickSound;

public class ImagesDisplay extends Activity{

    Button nextBtn;
    WebView mWebView;

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_display);





        //εμφάνιση περιεχομένου ιστοσελίδας
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings();
        mWebView.loadUrl("https://picsum.photos/200/300/?image=940" );

        //To onclick για το κουμπί ΑΛΛΗ ΕΙΚΟΝΑ
        Button btn_new_img = (Button)findViewById(R.id.btn_new_img);
        btn_new_img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSound.start();

                int minNumber = 1;
                int maxNumber = 1000;

                //get the next random number within range
                // Inclusive both minimum and maximum value
                int randomNumber =  new Random().nextInt((maxNumber-minNumber)+1)+minNumber;

                //ξαναφορτώνει την ιστοσελίδα
                ImagesDisplay.this.mWebView.loadUrl("https://picsum.photos/200/300/?image="+ randomNumber);
            }
        });

        //κουμπί για την επόμενη οθόνη
        nextBtn=(Button) findViewById(R.id.btn_next_geo);
        //  To onclick για το κουμπί ΕΠΟΜΕΝΟ
        nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                clickSound.start();
                Intent intent = new Intent(v.getContext(), Geofence.class);
                startActivityForResult(intent, 0);
            }
        });

    }

}

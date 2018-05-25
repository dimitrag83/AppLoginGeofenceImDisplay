package com.dimitra.goudela.AppLoginGeofence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{
    static  MediaPlayer clickSound;
    Button clickButton, clickButton1;

 //   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickButton = (Button)findViewById(R.id.btnNext);
        clickButton.setText("ENTER");
        clickButton1 = (Button)findViewById(R.id.btnNext1);
        clickButton1.setVisibility(View.GONE);

        //ηχητικό εφέ
        clickSound = MediaPlayer.create(this,R.raw.click_sound);

        //animation στο κουμπί ΕΙΣΟΔΟΣ
        final Animation btn_animation = AnimationUtils.loadAnimation(this,R.anim.btn_animation);

        //listener του κουμπιού ΕΙΣΟΔΟΣ
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //ήχος
                clickSound.start();
                //animation
                v.startAnimation(btn_animation);

                //έλεγχος ύπαρξης σύνδεσης
                //δήλωση permissions sto manifest
                final WifiManager wifi =(WifiManager)getSystemService(Context.WIFI_SERVICE);

                //αν δεν υπάρχει ενεργή σύνδεση
                if(!wifi.isWifiEnabled() ) {
                    //εμφάνιση μηνύματος
                    Toast.makeText(getApplicationContext(), "Please turn on WIFI to continue", Toast.LENGTH_SHORT).show();
                    clickButton.setVisibility(View.GONE); //εξαφανίζεται το κουμπί ΕΠΟΜΕΝΟ
                    clickButton1.setVisibility(View.VISIBLE);//εμφανίζετια το κουμπί ΟΚ
                    clickButton1.setText("ΟΚ");

                    //listener του δεύτερου κουμπιού ΟΚ
                    //κάθε φορά που πατιέται το κουμπί και δεν υπάρχει ενεργή σύνδεση εμφανίζεται το μήνυμα,
                    // διαφορετικά προχωράει στην επόμενη οθόνη
                    clickButton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //αν δευ υπάρχει ενεργή σύνδεση
                            if (!wifi.isWifiEnabled()) {
                                //εμφάνιση μηνύματος
                                Toast.makeText(getApplicationContext(), "Please turn on WIFI to continue", Toast.LENGTH_SHORT).show();
                            } else
                                {
                                    //επόμενη οθόνη
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                }//else
                        }//if
                    });//OnClickListener
                } else
                    //αν υπάρχει σύνδεση, προχωράει στην επόμενη οθόνη
                    {
                        clickButton.setVisibility(View.GONE);
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
            }//onClick clickButton
        });//OnclickListener clickButton
    }//onCreate

}

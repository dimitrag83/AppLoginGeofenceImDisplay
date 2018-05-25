package com.dimitra.goudela.AppLoginGeofence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import static com.dimitra.goudela.AppLoginGeofence.MainActivity.clickSound;

public class Login extends android.support.v7.app.AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    private LinearLayout Prof_Section;
    private Button SignOut, Next;
    private SignInButton SignIn;
    private ImageView Prof_Pic;
    private TextView Name,Email;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Αρχικοποίηση μεταβλητών
        Prof_Section = (LinearLayout) findViewById(R.id.prof_section);
        Next = (Button)findViewById(R.id.btn_next_img);
        SignOut = (Button) findViewById(R.id.btn_logout);
        SignIn = (SignInButton)  findViewById(R.id.btn_login);
        Name = (TextView)findViewById(R.id.name);
        Email =(TextView)findViewById(R.id.email);
        Prof_Pic = (ImageView)findViewById(R.id.prof_pic);

        //κουμπιά για το clickListener
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        Next.setOnClickListener(this);

        //απόκρυψη του τμήματος με το προφίλ του χρήστη
        Prof_Section.setVisibility(View.GONE);

        //Αρχικοποίηση
        // Ζητείται πρόσβαση στο προφίλ του χρήστη, ID, email
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // GoogleApiClient για πρόσβαση στο Google Sign-In API και τις διάφορες επιλογές
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    //Διαχείριση των κουμπιών
    @Override
    public void onClick(View v) {
        clickSound.start();
        switch (v.getId())
        {
            case R.id.btn_login: //κουμπί sign in
                signIn();
                break;
            case R.id.btn_logout://κουμπί sign out
                signOut();
                break;
            case R.id.btn_next_img://κουμπί επόμενο
                nextScreen();
                break;
        }

    }


    //Σύνδεση στο google account
    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);

    }

    //Επόμενη οθόνη
    private void nextScreen()
    {
        Intent intent = new Intent(this, ImagesDisplay.class);
        startActivity(intent);
    }

    //Αποσύνδεση απο google account
    private void signOut()
    {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });

    }

    //εμφάνιση των στοιχείων του προφιλ του χρήστη
    private void handleResult(GoogleSignInResult result)
    {
        if(result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String ima_url = account.getPhotoUrl().toString();
            Name.setText(name);
            Email.setText(email);
            Glide.with(this).load(ima_url).into(Prof_Pic);
            updateUI(true);
        }
        else
        {
            updateUI(false);
        }

    }
    //update data
    private void updateUI(boolean isLogin)
    {
        if(isLogin)
        {
            Prof_Section.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);
        }
        else
        {
            Prof_Section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE)
        {
            //	Ανάκτηση του αποτελέσματος του sign-in
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }

    }
}


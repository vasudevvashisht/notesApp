package com.example.pilot;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pilot.fragment.AccountFragment;
import com.example.pilot.fragment.AlarmFragment;
import com.example.pilot.fragment.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
//this is main activity
public class MainActivity extends AppCompatActivity {


    private Button signOutButton;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private BottomNavigationView bottomNavigationView;
    private MyBroadcastReceiver myBroadcastReceiver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_main);
        initViews();
        setUpTopic();
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            switch(item.getItemId()){

                case R.id.alarm_page:
                    fragment = new AlarmFragment();
                    break;
                case R.id.account_page:
                    fragment = new AccountFragment();
                    break;
                case R.id.home_page:
                    fragment = new HomeFragment();
            }

            return loadFragment(fragment);
        });

        signOutButton.setOnClickListener(view -> signOut());




    }

    private void setUpTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(task -> {
                    String msg = "successfully subscribed to Topic";
                    if (!task.isSuccessful()) {
                        msg = "failed to subscribe to Topic";
                    }
                    Log.e("topic",msg);
                });

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        super.onStart();
        registerReceiver(myBroadcastReceiver,filter);
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
            return true;
        }
        return false;

    }
    private void initViews() {


        myBroadcastReceiver = new MyBroadcastReceiver();
        signOutButton = findViewById(R.id.sign_out_button);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        loadFragment(new HomeFragment());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(MainActivity.this, task -> {
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                    });
    }
}

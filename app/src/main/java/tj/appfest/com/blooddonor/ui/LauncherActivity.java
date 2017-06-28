package tj.appfest.com.blooddonor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import tj.appfest.com.blooddonor.BuildConfig;
import tj.appfest.com.blooddonor.R;

public class LauncherActivity extends BaseActivity {

    private static final String LOGIN_TYPE = "google_login";
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);
        showProgressDialog();
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        long cacheExpiration = 3600; // 1 hour in seconds.
        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LauncherActivity.this, "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show();

                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(LauncherActivity.this, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        decideSignInType();
                    }
                });
    }

    private void decideSignInType() {
        // TODO: 28/06/17
        hideProgressDialog();
        boolean loginTypeGoogle = mFirebaseRemoteConfig.getBoolean(LOGIN_TYPE);
        Intent loginScreen = new Intent(this, PhoneAuthActivity.class);
        if(loginTypeGoogle){
            loginScreen = new Intent(this, GoogleSignInActivity.class);
        }

        startActivity(loginScreen);
    }
}

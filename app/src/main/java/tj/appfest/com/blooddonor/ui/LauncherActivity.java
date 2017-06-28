package tj.appfest.com.blooddonor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tj.appfest.com.blooddonor.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);

        Intent profile = new Intent(this, LauncherActivity.class);
        startActivity(profile);


    }
}

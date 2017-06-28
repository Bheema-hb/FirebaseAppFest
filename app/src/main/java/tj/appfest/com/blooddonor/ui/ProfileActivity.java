package tj.appfest.com.blooddonor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tj.appfest.com.blooddonor.R;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.profile_pic)
    ImageView profilePic;

    @BindView(R.id.name)
    TextInputEditText name;

    @BindView(R.id.email)
    TextInputEditText email;

    @BindView(R.id.address)
    TextInputEditText address;

    @BindView(R.id.dob)
    TextInputEditText dob;

    @BindView(R.id.blood_group_type)
    Spinner bloodGroupType;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            showProfileInUI(firebaseUser);
        } else {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void showProfileInUI(FirebaseUser firebaseUser) {
        Picasso.with(this).load(firebaseUser.getPhotoUrl()).placeholder(R.drawable.user_profile_pic_place_holder).into(profilePic);
    }

    @OnClick(R.id.save_profile)
    public void saveProfile() {
        // TODO: 28/06/17 add logic to sync data with Realtime DB
    }

    @OnClick(R.id.logout_profile)
    public void logoutProfile() {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, LauncherActivity.class));
    }

}

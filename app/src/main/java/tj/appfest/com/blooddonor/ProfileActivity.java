package tj.appfest.com.blooddonor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.root_view)
    CoordinatorLayout rootView;

    @BindView(R.id.profile_pic)
    ImageView profilePic;

    @BindView(R.id.name)
    TextInputEditText name;

    @BindView(R.id.mobile_number)
    TextInputEditText mobile;


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
        if (firebaseUser.getEmail() != null) {
            email.setText(firebaseUser.getEmail());
        } else {
            email.setEnabled(true);
        }

        if (firebaseUser.getPhoneNumber() != null) {
            mobile.setText(firebaseUser.getPhoneNumber());
        } else {
            mobile.setEnabled(true);
        }

        if (firebaseUser.getDisplayName() != null) {
            name.setText(firebaseUser.getDisplayName());
        }


    }

    @OnClick(R.id.save_profile)
    public void saveProfile() {

        if (validateFields()) {
            // TODO: 28/06/17 add logic to sync data with Realtime DB
            Snackbar.make(rootView, "Data Will Saved", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(rootView, getString(R.string.err_mandatory_fields_not_entered), Snackbar.LENGTH_LONG).show();
        }
    }

    private boolean validateFields() {
        boolean validated = true;
        if (name.getText().toString().trim().length() < 1) {
            validated = false;
            name.setError("Please enter the name");
        } else {
            name.setError(null);
        }

        if (email.isEnabled() && email.getText().toString().trim().length() < 1) {
            validated = false;
            email.setError("Please enter the email");
        } else {
            email.setError(null);
        }

        if (mobile.isEnabled() && mobile.getText().toString().trim().length() < 1) {
            validated = false;
            mobile.setError("Please enter mobile");
        } else {
            mobile.setError(null);
        }

        if (dob.getText().toString().length() < 1) {
            validated = false;
            dob.setError("Please enter dob");
        } else {
            dob.setError(null);
        }

        return validated;
    }


    @OnClick(R.id.dob)
    public void showDOBPicker() {
        DialogFragment newFragment = new DatePickerFragment(dob);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}

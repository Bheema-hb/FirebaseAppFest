package tj.appfest.com.blooddonor.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.OnClick;
import tj.appfest.com.blooddonor.R;
import tj.appfest.com.blooddonor.model.Requests;

public class RequestForBloodActivity extends AppCompatActivity {

    @BindView(R.id.blood_group_type)
    Spinner bloodGroupType;

    @BindView(R.id.request_blood)
    Button requestBlood;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference requestsDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_blood);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseDatabase = FirebaseDatabase.getInstance();
        requestsDatabase = firebaseDatabase.getReference("server/data/requests");


    }

    @OnClick(R.id.request_blood)
    public void requestBlood() {
        Requests requests = new Requests();
        requests.bloodGroup = bloodGroupType.getSelectedItem().toString();

        requestsDatabase.setValue(requests);
    }

}

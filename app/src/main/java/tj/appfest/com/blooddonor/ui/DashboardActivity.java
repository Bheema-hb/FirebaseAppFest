package tj.appfest.com.blooddonor.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tj.appfest.com.blooddonor.R;
import tj.appfest.com.blooddonor.UserProfile;

public class DashboardActivity extends BaseActivity {


    private static final String TAG = DashboardActivity.class.getSimpleName();
    private DatabaseReference userProfileDb;
    private FirebaseDatabase firebaseDatabase;
    private UserProfile mUserProfile;
    ArrayList<UserProfile> donorsList =new ArrayList<>();
    private DonorAdapter donorAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView donorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        userProfileDb = firebaseDatabase.getReference("server/data/users/profiles");

        donorAdapter = new DonorAdapter(donorsList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(donorList.getContext(),
                DividerItemDecoration.VERTICAL);
        donorList.addItemDecoration(mDividerItemDecoration);

        donorList.setLayoutManager(mLayoutManager);
        donorList.setItemAnimator(new DefaultItemAnimator());
        donorList.setAdapter(donorAdapter);
        showProgressDialog();
        retrieve();

    }

    public ArrayList<UserProfile> retrieve()
    {

        userProfileDb.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               for (DataSnapshot ds : dataSnapshot.getChildren()){
                   UserProfile group = ds.getValue(UserProfile.class);
                   Log.d(TAG, ""+group.getName());
                   donorsList.add(group);
                   hideProgressDialog();
                   donorAdapter.notifyDataSetChanged();
               }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return donorsList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.profile:
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                break;
        }
        return true;
    }
}

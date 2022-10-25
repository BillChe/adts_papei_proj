package com.example.adts_papei_proj.ui.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.adts_papei_proj.R;
import com.example.adts_papei_proj.data.model.LoggedInUser;
import com.example.adts_papei_proj.data.viewmodels.MainViewModel;
import com.example.adts_papei_proj.databinding.ActivityHomeBinding;
import com.example.adts_papei_proj.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    MainViewModel vm;
    ActivityHomeBinding binding;
    //drawer layout
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public Button report;
    private String username;
    Context context;
    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    TextView usernameTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        vm = new MainViewModel(HomeActivity.this);

        binding.setVm(vm);
        context = HomeActivity.this;
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser == null) {
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        };
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        report = findViewById(R.id.report);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        usernameTextView = findViewById(R.id.usernameTextView);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        vm.setUsername(getIntent().getStringExtra("name"));
        usernameTextView.setText("Welcome "+ vm.getUsername());
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoutBtn:
                //todo check here if is needed, finish might always return to Login Screen
                //todo review
                //sign out firebase user
                FirebaseAuth.getInstance().signOut();
                //intent to Login Activity
                Intent registerIntent = new Intent(context, LoginActivity.class);
                registerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(registerIntent);
                //vm.logout();
                finish();
            default:

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    public void report()
    {
/*        Incident incident = new Incident();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateNow = dateFormat.format(date).toString();
        //Adding values
        incident.setDescription(incidentDescription);
        incident.setDate(dateNow);
        //incident.setLocation(vm.getLocation());
        incident.setLocationLat(String.valueOf(vm.getLocation().getLatitude()));
        incident.setLocationLong(String.valueOf(vm.getLocation().getLongitude()));
        incident.setType(incidentType);
        incident.setImageUrl(imageUrl);
        incident.setUserUId(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseDatabase.getInstance().getReference("Incidents")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push()
                .setValue(incident)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            //todo remove them
                            Toast.makeText(context, context.getString(R.string.reported_incident_success),
                                    Toast.LENGTH_LONG).show();
                            //todo redirect to Login Screen !!!!
                        }
                        else
                        {
                            Toast.makeText(context, context.getString(R.string.reported_incident__fail),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });*/
    }

}

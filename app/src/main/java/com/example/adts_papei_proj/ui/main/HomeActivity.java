package com.example.adts_papei_proj.ui.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.adts_papei_proj.R;
import com.example.adts_papei_proj.data.User;
import com.example.adts_papei_proj.data.viewmodels.MainViewModel;
import com.example.adts_papei_proj.databinding.ActivityHomeBinding;
import com.example.adts_papei_proj.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    MainViewModel vm;
    ActivityHomeBinding binding;
    public Button report;
    private String username;
    Context context;
    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    TextView usernameTextView ;
    boolean isB1completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        vm = new MainViewModel(HomeActivity.this);

        binding.setVm(vm);
        context = HomeActivity.this;

        report = findViewById(R.id.report);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        usernameTextView = findViewById(R.id.usernameText);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User currentUser = dataSnapshot.child(FirebaseAuth.getInstance().getUid()).getValue(User.class);
                        //Get map of users in datasnapshot
                        username = collectUsername((Map<String,Object>) dataSnapshot.getValue());
                        usernameTextView.setText("Welcome "+ username);
                        if(!currentUser.isB1completed())
                        {
                            binding.startnewB2.setVisibility(View.GONE);
/*
                            vm.setB1Completed(true);
*/
                        }
                        vm.setUsername(username);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });



    }


    private String collectUsername(Map<String, Object> value) {
        String name = "";

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : value.entrySet()){
            //Get user map
            Map singleUser = (Map) entry.getValue();
            if(singleUser.get("email").equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
            {
                //Get username field
                name = singleUser.get("username").toString();
            }
        }
        System.out.println(name.toString());
        return name;
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
                Intent registerIntent = new Intent(HomeActivity.this, LoginActivity.class);
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



}

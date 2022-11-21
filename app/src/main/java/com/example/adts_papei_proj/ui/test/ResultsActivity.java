package com.example.adts_papei_proj.ui.test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.adts_papei_proj.R;
import com.example.adts_papei_proj.data.model.UserTestResult;
import com.example.adts_papei_proj.helpers.ListAdapter;
import com.example.adts_papei_proj.ui.forgotpassword.PasswordForgot;
import com.example.adts_papei_proj.ui.login.LoginActivity;
import com.example.adts_papei_proj.ui.main.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<UserTestResult> arrayList = new ArrayList<>();
    ListAdapter arrayAdapter ;
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Results");
        if(getIntent().getStringExtra("name")!=null)
        {
            username = getIntent().getStringExtra("name");
            getSupportActionBar().setTitle(username + " " + getSupportActionBar().getTitle());
        }
        //set views and listener for button
        getAllUserTestResults();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void getAllUserTestResults() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("UserTestResults").child(FirebaseAuth.getInstance().getUid());
        listView = (ListView) findViewById(R.id.resultsListView);
        arrayAdapter = new ListAdapter(this, R.layout.itemlist,arrayList);
        listView.setAdapter(arrayAdapter);

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                UserTestResult incidentTemp = snapshot.getValue(UserTestResult.class);

                    //show only user related problems
                    if(incidentTemp.getUid().equals(FirebaseAuth.getInstance().getUid()))
                    {
                        incidentTemp.setUsername(username);
                        arrayList.add(incidentTemp);
                        arrayAdapter.notifyDataSetChanged();

                    }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent loginIntent = new Intent(ResultsActivity.this, HomeActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent loginIntent = new Intent(ResultsActivity.this, HomeActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
        finish();
    }
}
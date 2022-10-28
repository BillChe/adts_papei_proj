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
import com.example.adts_papei_proj.ui.main.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    private boolean showUser = false;
    ListView listView;
    ArrayList<UserTestResult> arrayList = new ArrayList<>();
    ListAdapter arrayAdapter ;
    Button viewOnMap;
    boolean problemsExist= false;
    public static ArrayList<UserTestResult> markersArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Results");
        //set views and listener for button
        viewOnMap = (Button) findViewById(R.id.viewOnMap);
        showUser = true;
        getAllUserTestResults();
        viewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(problemsExist)
                    intentToMapsView();
            }
        });
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
                        arrayList.add(incidentTemp);
                        arrayAdapter.notifyDataSetChanged();
                    }

                //fill markers array with incidents
                markersArray.add(incidentTemp);
                problemsExist = true;

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

    private void intentToMapsView() {
        Intent registerIntent = new Intent(ResultsActivity.this, HomeActivity.class);
        startActivity(registerIntent);
        ResultsActivity.this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
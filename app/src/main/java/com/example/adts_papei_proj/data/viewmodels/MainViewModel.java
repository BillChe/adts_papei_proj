package com.example.adts_papei_proj.data.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;


import com.example.adts_papei_proj.R;
import com.example.adts_papei_proj.data.User;
import com.example.adts_papei_proj.data.model.UserTestResult;
import com.example.adts_papei_proj.ui.login.LoginActivity;
import com.example.adts_papei_proj.ui.main.HomeActivity;
import com.example.adts_papei_proj.ui.test.ResultsActivity;
import com.example.adts_papei_proj.ui.test.TestActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MainViewModel extends BaseObservable {
    private String username;
    private Context context;
    @Bindable
    private boolean isB1Completed;

    public MainViewModel() {
    }

    public MainViewModel(Context context) {
        this.context = context;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void logout()
    {
        //sign out firebase user
        FirebaseAuth.getInstance().signOut();
        //intent to Login Activity
        Intent registerIntent = new Intent(context, LoginActivity.class);
        registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(registerIntent);
    }

    public void startNewTestB1Level()
    {
        Intent startNewTestIntent = new Intent(context, TestActivity.class);

        startNewTestIntent.putExtra("level","b1");

        context.startActivity(startNewTestIntent);
    }

    public void startNewTestB2Level()
    {
        Intent startNewTestIntent = new Intent(context, TestActivity.class);

        startNewTestIntent.putExtra("level","b2");

        context.startActivity(startNewTestIntent);
    }

    public void showResults()
    {
        Intent resultsIntent = new Intent(context, ResultsActivity.class);
        resultsIntent.putExtra("name",getUsername());
        context.startActivity(resultsIntent);
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/DirName");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/DirName/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File("/sdcard/DirName/", fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadUserScore(UserTestResult testResult, int testLevel, double scoreDouble)
    {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> userChildren = dataSnapshot.getChildren();

                for (DataSnapshot user: userChildren) {
                    User u = user.getValue(User.class);      //make a model User with necessary fields

                    if(u.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        if(!u.isB1completed())
                        {
                            if(testLevel==0 && scoreDouble>=65.0)
                            {
                                //todo update User Level
                                //todo remove them
                                Toast.makeText(context, "Congratulations! B2 Level Test is now available",
                                        Toast.LENGTH_LONG).show();

                                Map<String, Object> updates = new HashMap<String,Object>();
                                updates.put("b1completed", true);
                                ref.child(FirebaseAuth.getInstance().getUid()).updateChildren(updates);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference("UserTestResults")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push()
                .setValue(testResult)
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
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                });
    }

    public boolean isB1Completed() {
        return isB1Completed;
    }

    public void setB1Completed(boolean b1Completed) {
        isB1Completed = b1Completed;
    }

}

package com.example.adts_papei_proj.data.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Environment;

import androidx.lifecycle.ViewModel;


import com.example.adts_papei_proj.ui.login.LoginActivity;
import com.example.adts_papei_proj.ui.test.TestActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileOutputStream;

public class MainViewModel extends ViewModel {
    private String username;
    private Location location;
    Context context;

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

    public void startNewTest()
    {
        Intent startNewTestIntent = new Intent(context, TestActivity.class);
/*
        startNewTestIntent.putExtra("user","yes");
*/
        context.startActivity(startNewTestIntent);
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
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }







}

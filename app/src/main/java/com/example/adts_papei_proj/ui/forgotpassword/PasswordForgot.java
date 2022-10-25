package com.example.adts_papei_proj.ui.forgotpassword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adts_papei_proj.R;
import com.example.adts_papei_proj.ui.login.LoginActivity;
import com.example.adts_papei_proj.ui.register.RegisterForm;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordForgot extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordBtn;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_forgot);

        //set views
        emailEditText = (EditText) findViewById(R.id.email);
        resetPasswordBtn = (Button) findViewById(R.id.resetPassword);

        //init firebase auth
        auth = FirebaseAuth.getInstance();

        //on click listener for reset btn
        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();

        if(email.isEmpty())
        {
            emailEditText.setError(getString(R.string.require_email));
            emailEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailEditText.setError(getString(R.string.enter_valid_email));
            emailEditText.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    //check email toast
                    Toast.makeText(PasswordForgot.this, getString(R.string.reset_password_email), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(PasswordForgot.this, getString(R.string.enter_valid_email_try_again), Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent loginIntent = new Intent(PasswordForgot.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
        finish();
    }

}
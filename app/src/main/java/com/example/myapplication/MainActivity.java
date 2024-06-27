package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(mAuth.getCurrentUser() != null)
            moveToNextActivity();

    }

     private void moveToNextActivity() {
        Intent intent = new Intent(MainActivity.this, Menu.class);
        startActivity(intent);

    }



    public void SignUp(View view){
        EditText etMail = findViewById(R.id.editTextTextEmailAddress2);
        EditText etPassword = findViewById(R.id.editTextTextPassword2);

        if(TextUtils.isEmpty(etMail.getText()) ||TextUtils.isEmpty(etPassword.getText()))
        {
            Toast.makeText(this,"please assign again",Toast.LENGTH_LONG).show();

            return;
        }
        String emil = etMail.getText().toString();
        String password = etPassword.getText().toString();


            mAuth.createUserWithEmailAndPassword(emil,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) // register success
                    {
                        Toast.makeText(MainActivity.this, "register success", Toast.LENGTH_SHORT).show();
                        // can be done only register SUCCESS!!!
                        moveToNextActivity();

                    } else // register fail
                    {
                        String failureReason = task.getException().toString();
                        Log.d("REGISTER FB", "onComplete: " + failureReason);
                        Toast.makeText(MainActivity.this, "register failed " + failureReason, Toast.LENGTH_SHORT).show();

                    }
                }
            });


    }
}
package com.example.myapplication;

import static com.example.myapplication.GameConst.Other;
import static com.example.myapplication.GameConst.two_phone;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import static com.example.myapplication.GameConst.*;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;


public class Menu extends AppCompatActivity {

    public  String gameid;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->
    {
    //    if(result.getResultCode()== Activity.RESULT_OK)
    //    {
            Intent i = new Intent(this, GameActivity.class);
            i.putExtra("gameId", gameid);
            i.putExtra("player", Host);
            startActivity((i));
   //     }
    });
    public void shareClick(View view) {

        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        roomGame rg = new roomGame();
        fb.collection("roomGame").add(rg.roomGameToMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                TextView CodeTextView = findViewById(R.id.textViewMenuCreate);
                ImageView shareImage = findViewById(R.id.imageView);
                gameid = documentReference.getId();
                CodeTextView.setText( gameid);
                CodeTextView.setVisibility(View.VISIBLE);
                shareImage.setVisibility(View.VISIBLE);

                Log.d("ONSUCCES", "id" + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("ONFAILER","onfailure"+ e.getMessage());
            }
        });
        //Button startbutton = findViewById(R.id.startbutton);
        //startbutton.setVisibility((View.VISIBLE));
    }

    public void shareWithFriends(View view){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, gameid);
        mActivityResultLauncher.launch(shareIntent);
       // startActivityForResult(Intent.createChooser(shareIntent,"share using"),1);
    }

    public void  ClickToNext(View view)
    {
        TextView eCode = findViewById(R.id.eTextPasswordcode);
        String gameCode = eCode.getText().toString();
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("gameId",gameid);
        i.putExtra("player", Other);
        i.putExtra(game_confing, two_phone);
        startActivity(i);
    }

    protected void onActivityResult(int reqCode, int resCode, @Nullable Intent data)
    {
        super.onActivityResult(reqCode,resCode,data);
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("gameId", gameid);
        i.putExtra("player", Host);
        startActivity((i));
    }

    public void joinClicked(View view)
    {
        EditText enterCode = findViewById(R.id.eTextPasswordcode);
        Button clickJoin = findViewById(R.id.button7);
        Button startbutton = findViewById(R.id.startbutton);
        enterCode.setVisibility((View.VISIBLE));
        clickJoin.setVisibility((View.VISIBLE));
        startbutton.setVisibility((View.VISIBLE));
    }
    public  void moveToNextActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        EditText enterCode = findViewById(R.id.eTextPasswordcode);
        gameid = enterCode.getText().toString();

        intent.putExtra("gameId", gameid);
        intent.putExtra("player", Other);

        startActivity(intent);

    }}





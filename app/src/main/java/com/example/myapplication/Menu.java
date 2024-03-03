package com.example.myapplication;

import static com.example.myapplication.GameConst.Other;
import static com.example.myapplication.GameConst.two_phone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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


public class Menu extends AppCompatActivity {

    private String gameid;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void shareClick(View view) {

        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        roomGame rg = new roomGame();
        fb.collection("roomGame").add(rg).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                TextView CodeTextView = findViewById(R.id.textView7);
                ImageView shareImage = findViewById(R.id.imageView);
                gameid = documentReference.getId();
                CodeTextView.setText("your game code is: " + gameid + " .share it with your partner!");
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

    }

    public void shareWithFriends(View view){
        Intent shareIntent = new Intent(Intent.ACTION_SENDTO);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "hello, this is the code for the game:" +gameid+ "please join");
        startActivity(Intent.createChooser(shareIntent,"share using"));
    }

    public void  ClickToNext(View view)
    {
        TextView eCode = findViewById(R.id.editSend);
        String gameCode = eCode.getText().toString();
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("gameId",gameCode);
        i.putExtra("player", Other);
        i.putExtra(game_confing, two_phone);
        startActivity(i);
    }

    protected void onActivityResult(int reqCode, int resCode, @Nullable Intent data)
    {
        super.onActivityResult(reqCode,resCode,data);
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("game id", gameid);
        i.putExtra("player", Host);
        startActivity((i));
    }

    public void joinClicked(View view)
    {
        TextView enterCode = findViewById(R.id.editSend);
        Button clickJoin = findViewById(R.id.button7);
        enterCode.setVisibility((View.VISIBLE));
        clickJoin.setVisibility((View.VISIBLE));
    }


}
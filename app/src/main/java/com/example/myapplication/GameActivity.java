package com.example.myapplication;

import static com.example.myapplication.GameConst.Host;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class GameActivity extends AppCompatActivity {

    BoardGame bordGame;
    public static int subSize = 0;

    GameLogic gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gl = new GameLogic();
        bordGame = new BoardGame(this, gl);
        setContentView(R.layout.activity_game);
        LinearLayout ll = findViewById(R.id.view);
        ll.addView(bordGame);

        //Log.d("GAME ACTIVITY", "onCreate: HELLO ");
    }

    public void chooseSubSize(View view) {
        Button b = (Button) view;

        int num = Integer.valueOf(b.getText().toString());
        subSize = num;


    }

    public void moveToOnline(View view) {

        String gameId = getIntent().getStringExtra("gameId");
        int player = getIntent().getIntExtra("player", 0);

        // set the current status to START

        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        DocumentReference ref = fb.collection("roomGame").document(gameId);

        if (player == Host) {
            ref.update("status", "START").addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    ref.update("myboard", gl.getArry()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            waitForPlayersToJoin(ref,gameId,player);
                        }
                    });

                }
            });
        } else {
            ref.update("other", gl.getArry()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    ref.update("statusOther", "START").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            waitForPlayersToJoin(ref,gameId,player);

                        }
                    });

                }
            });

        }


    }

    private void waitForPlayersToJoin(DocumentReference ref,String gameId,int player) {


        ref.addSnapshotListener(GameActivity.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                if (value == null || !value.exists())
                    return;
                roomGame rg = value.toObject(roomGame.class);

                if (rg.getStatus().equals("START") && rg.getStatusOther().equals("START")) {
                    Intent i = new Intent(GameActivity.this, OnlineActivity.class);
                    //        i.putExtra("arr",gl.getArry());
                    i.putExtra("gameid", gameId);
                    i.putExtra("player", player);
                    //i.putExtra("myboard", roomGame.setMyboard(bordGame))
                    //(i);-*
                    startActivity(i);
                }
            }
        });


    }
}


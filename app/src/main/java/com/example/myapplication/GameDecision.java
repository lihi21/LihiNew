package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class GameDecision extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_decision);


        TextView tv = findViewById(R.id.tvCountDecision);


        // we know if we are host or other -> get player from the intent
        //  data about the game is in firebase
        CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {

                int value = Integer.valueOf(tv.getText().toString());
                if(value>0)

                    tv.setText(""+(value-1));


            }

            @Override
            public void onFinish() {

                // check who won and move to winner ot lose
                FirebaseFirestore fb = FirebaseFirestore.getInstance();
                String gameId= getIntent().getStringExtra("gameid");
                int player =  getIntent().getIntExtra("player",0);

                fb.collection("roomGame").document(gameId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        roomGame rg = documentSnapshot.toObject(roomGame.class);
                        //   rg num of click
                        //  host and other
                        // check  intent if I am host or other

                        if( rg.getMoves()== rg.getMovesother())
                        {
                            if(rg.getMoves() == 1000 || rg.getMovesother()== 1000)
                            {
                                moveToLoser();
                            }
                            else
                            {
                                moveToWinner();
                            }
                        }


                        if( player==GameConst.Host)
                        {
                            // check If I finished all subs,
                            if(rg.getMoves() > rg.getMovesother())
                            {
                                moveToLoser();
                            }
                            else moveToWinner();
                            // if so check whguessed woth less moves

                        }
                        else // I am other
                        {
                            if(rg.getMoves() < rg.getMovesother())
                            {
                                moveToLoser();
                            }
                            else moveToWinner();
                        }


                    }
                });


            }
        };
        countDownTimer.start();




    }

    private void moveToWinner() {
        Intent i = new Intent(this, winner.class);
        startActivity(i);
    }

    private void moveToLoser() {
        Intent i = new Intent(this, Loser.class);
        startActivity(i);

    }
}
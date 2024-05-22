package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class OnlineActivity extends AppCompatActivity {

    private int[][] arrGame;
    private int[][] oppBoard;


    private FirebaseFirestore fb = FirebaseFirestore.getInstance();

    private GameLogic glMe;
    private GameLogic glOther;

    private BoardGame myBoard;
    private BoardGame otherBoard;

    private String gameId;

    private roomGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // get the room from firebase!!
        gameId= getIntent().getStringExtra("gameid");
        getRoomDataFromFirebase();
  //      arrGame = (int[][]) getIntent().getSerializableExtra("arr");
    //    Log.d("log", "onCreate: " + arrGame[0][0]);
 //       // create 2 board games
    //    glMe = new GameLogic();

        // board game has reference to the activity
        //myBoard = new BoardGame(this, glMe);
       // myBoard.setLoigicalBoard(arrGame);


        // other player =- upper board
        //glOther = new GameLogic();
        //otherBoard = new BoardGame(this, glOther);
        //glOther.setOtherPlayer();

        // for testing only - we copy our board

        //otherBoard.setLoigicalBoard(oppBoard);

        // for the other player we need to get his Opponent board (behind only)
        // not displaying this board
        //setContentView(R.layout.activity_online);


//        LinearLayout llOther = findViewById(R.id.otherBoard);
  //      llOther.addView(otherBoard);


    //    LinearLayout llMe = findViewById(R.id.myBoard);
      //  llMe.addView(myBoard);

/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
               //         myBoard.logicalToVisual(arrGame);

                    }
                });
            }
        }).start();

 */

    }

    private void getRoomDataFromFirebase() {
        fb.collection("roomGame").document(gameId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //game = documentSnapshot.toObject(roomGame.class);
                game = new roomGame(documentSnapshot.getData());
              arrGame = game.getMyOriginalArray();
               oppBoard = game.getotherArray();

                glMe = new GameLogic();

                myBoard = new BoardGame(OnlineActivity.this , glMe);
                myBoard.setLoigicalBoard(arrGame);

                // other player = upper board
                glOther = new GameLogic();
                otherBoard = new BoardGame(OnlineActivity.this, glOther);
                glOther.setOtherPlayer();
                setContentView(R.layout.activity_online);

                LinearLayout llOther = findViewById(R.id.otherBoard);
                llOther.addView(otherBoard);
                LinearLayout llMe = findViewById(R.id.myBoard);
                llMe.addView(myBoard);


                myBoard.logicalToVisual(arrGame);



            }
        });



    }

    public void gameWon() {

        // sleep on seperate thread for 3 seconds
        // move to next activity

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(OnlineActivity.this, winner.class);
                        startActivity(i);
                    }
                });

            }
        }).start();


    }

    public void gameLose() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(OnlineActivity.this, Loser.class);
                        startActivity(i);
                    }
                });

            }
        }).start();
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class OnlineActivity extends AppCompatActivity {

    private int[][] arrGame;

    private GameLogic glMe;
    private GameLogic glOther;

    private BoardGame myBoard;
    private BoardGame otherBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // create 2 board games
        glMe = new GameLogic();
        myBoard = new BoardGame(this,glMe);



        glOther = new GameLogic();
        otherBoard = new BoardGame(this,glOther);
        setContentView(R.layout.activity_online);

        arrGame =(int[][]) getIntent().getSerializableExtra("arr");
        Log.d("log", "onCreate: " + arrGame[0][0]);




        LinearLayout llOther = findViewById(R.id.otherBoard);
        llOther.addView(otherBoard);


        LinearLayout llMe = findViewById(R.id.myBoard);
        llMe.addView(myBoard);



        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myBoard.logicalToVisual(arrGame);

                    }
                });
            }
        }).start();







    }
}
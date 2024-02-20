package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity {

    BoardGame bordGame;
    public static int subSize=0;

    GameLogic gl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gl = new GameLogic();
       bordGame = new BoardGame(this,gl);
        setContentView(R.layout.activity_game);
        LinearLayout ll = findViewById(R.id.view);
        ll.addView(bordGame);


        //Log.d("GAME ACTIVITY", "onCreate: HELLO ");

    }

    public void chooseSubSize(View view)
    {
        Button b = (Button)view;

        int num = Integer.valueOf(b.getText().toString());
        subSize = num;


    }

    public void moveToOnline(View view) {

        Intent i =new Intent(this,OnlineActivity.class);
        i.putExtra("arr",gl.getArry());
        startActivity(i);
    }

}
package com.example.myapplication;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;

import java.util.ArrayList;

public class BoardGame extends View {
    boolean setCoin = false;
GameLogic logic;


    private final static int ROW=6;
    private final static int COL=6;
    private Paint misgeret;


    public static ArrayList<Long> click = new ArrayList<>();

    public int getNumOfCLicks() {
        return numOfCLicks;
    }

     public int numOfCLicks = 0;
    //public static  int totalmove= 2+3+4+5;
    Square[][] squars = new Square[ROW][COL];


    Square mSquare;
    Context context;

    public BoardGame(Context context,GameLogic g) {
        super(context);

        this.context=context;


        logic= g;

    //    Paint p = new Paint();
      //  p.setColor(Color.BLUE);
     //   mSquare = new Square(this,50,50,50,50,p);


    }

    protected void onDraw(Canvas canvas) {
        drawBoard(canvas);
    }

    public void drawBoard(Canvas canvas) {

        int x = 0;
        int y = 40;
        int h = canvas.getWidth() / 7;
        int w = canvas.getWidth() / 7;
        int color = Color.BLACK;
        for (int i = 0; i < squars.length; i++) {
            for (int j = 0; j < squars.length; j++) {
                misgeret = new Paint();
                misgeret.setStyle(Paint.Style.STROKE);
                misgeret.setColor(Color.BLUE);
                misgeret.setStrokeWidth(12);
                if(squars[i][j] == null){
                    squars[i][j] = new Square(this, x, y, w, h, misgeret);
                }
                squars[i][j].draw(canvas);
                x = x + w;
            }
            y = y + h;
            x = 0;
        }
    //    mSquare.draw(canvas);
    }

    public void setLoigicalBoard(int[][] arr)
    {
        this.logic.setTwoArry(arr);
    }


    public void logicalToVisual(int[][] arr)
    {

        invalidate();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <arr[0].length ; j++) {
                if(arr[i][j]  != 0)
                {
                    squars[i][j].setOccupied(true);
                }
            }

        }

        invalidate();

    }

    public boolean onTouchEvent(MotionEvent event) {
        float x;
        float y;
/*
        if(event.getAction()==MotionEvent.ACTION_MOVE) {
            mSquare.x = mSquare.x + 10;
            invalidate();
        }
*/
        //int arr[]
        if (event.getAction() == MotionEvent.ACTION_UP) {
            x = event.getX();
            y = event.getY();
            for (int i = 0; i < squars.length; i++) {
                for (int j = 0; j < squars.length; j++) {
                    if (squars[i][j].didXAndYInSquare(x, y)) {

                        // guessing the upper board
                        if(logic.getPlayer() == 1)
                        {
                            // check if there is a SUBMARINE
                            // if so - set ocuupied
                            // else - set GREY
                           // roomGame.addmove(6*x+y);
                            click.add((long)(6*x+y));

                            if(logic.isThereSub(i,j)) {

                                squars[i][j].setOccupied(true);
                                logic.addToWinCounter();
                                //totalmove++;
                         /*         if(logic.checkWin())
                                {

                                    ( (OnlineActivity)context).gameWon();

                                }
                              else
                                {
                                    ( (OnlineActivity)context).gameLose();
                                }

                           */
                            }
                            else {
                                squars[i][j].setWrongClicked();
                           //     roomGame.setmoves();
                                numOfCLicks++;
                            }
                            invalidate();
                        }
                        // this means we are placing a SUBmarine
                        else {
                            boolean result = logic.place(i, j);
                            Log.d("BOARDGAME", "onTouchEvent: " + i + j);
                            // if updated logically - draw on the BOARD UI
                            if (result) {
                                squars[i][j].setOccupied(true);
                                invalidate();
                            }
                        }
                    }
                }
            }
        }
    return true;
    }


}



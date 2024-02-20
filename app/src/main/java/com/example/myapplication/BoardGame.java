package com.example.myapplication;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;

public class BoardGame extends View {
    boolean setCoin = false;
GameLogic logic;


    private final static int ROW=6;
    private final static int COL=6;
    private Paint misgeret;


    Square[][] squars = new Square[ROW][COL];


    Square mSquare;
    Context context;

    public BoardGame(Context context,GameLogic g) {
        super(context);

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
        this.logic.setArry(arr);
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
                            if(logic.isThereSub(i,j))
                                squars[i][j].setOccupied(true);
                            else
                                squars[i][j].setWrongClicked();
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



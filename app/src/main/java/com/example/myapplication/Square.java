package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Square {
    BoardGame bordgame;
    float x,y,w,h;

    boolean isOcuppied;

    boolean isWrongClicked=false;
    int color;
    Paint p;
    public  Square(BoardGame bordgame,float x,float y,float w,float h,Paint p){
        this.x = x;
        this.y =y;
        //int color = Color.BLACK;
        this.bordgame = bordgame;
        //p = new Paint();
        this.p = p;
        this.w = w;
        this.h = h;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawRect(x,y,  x+w, y+h,p);

        if(isOcuppied)
        {
            Paint p= new Paint();
            p.setColor(Color.BLUE);
            canvas.drawRect(x,y,x+w,y+h,p);

        }
        if(isWrongClicked)
        {
            Paint p= new Paint();
            p.setColor(Color.GRAY);
            canvas.drawRect(x,y,x+w,y+h,p);

        }
    }
    public boolean didXAndYInSquare(float xo,float yo)
    {
        if(xo > x && xo < x + w && yo > y && yo <y + h)
            return  true;
        return  false;
    }


    public void setWrongClicked()
    {
        isWrongClicked = true;
    }

    public void setOccupied(boolean state) {
        isOcuppied = state;
    }
}

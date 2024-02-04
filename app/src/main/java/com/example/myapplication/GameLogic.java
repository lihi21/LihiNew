package com.example.myapplication;

public class GameLogic {
    private final static int ROW=6;
    private final static int COL=6;
    int[][] arry = new int[ROW][COL];
    int privX = 0;
    int privY = 0;
    int counter =0;
    int side = 0;     // 1 culom, 2 row, 0 first click


    public int[][] getArry()
    {
        return this.arry;
    }
    public void ristartArry(){
        for (int i = 0; i < arry.length; i++) {
            for (int j = 0; j < arry.length; j++) {
                arry[i][j] = 0;
            }
        }
    }

    // x,y
    // privX, privY
    // counter
    // GameActicvity.subSize
    public boolean check(int x, int y, int sub){
        if(counter == 0)
        {
            privX = x;
            privY = y;
            counter++;
            return true;
        }
        else
        {
            boolean result = false;
            if(privX + 1 == x && privY == y && (side == 0 || side == 1)){
                side = 1;
                result =  true;
            }
            if(privY + 1 == y && privX == x && (side == 0 || side == 2)){
                side = 2;
                result =  true;
            }
            if(privX - 1 == x && privY == y && (side == 0 || side == 1)){
                side = 1;
                result =  true;
            }
            if(privY - 1 == y && privX == x && (side == 0 || side == 2)){
                side = 2;
                result =  true;
            }
            if(result) {
                privX = x;
                privY = y;
                counter++;
             //   if (counter >= sub){
               //     counter = 0;
                //}

                return true;
            }
            return false;
        }
    }

    public boolean place(int x,int y)
    {
        if(GameActivity.subSize ==0)
            return false;
        if(check(x,y,GameActivity.subSize)) {
            arry[x][y] = GameActivity.subSize;
            if(counter == GameActivity.subSize)
            {
                counter = 0;
                GameActivity.subSize=0;
                side = 0;
            }
            return true;
        }
        return false;
    }


}

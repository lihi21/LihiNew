package com.example.myapplication;

import android.content.Intent;

public class roomGame
{

    BoardGame myboard, other;
    int currentplyer;
    int currX, currY;
    GameLogic gameLogic;

    public void setMyboard(BoardGame myboard) {
        this.myboard = myboard;
    }

    public void setOther(BoardGame other) {
        this.other = other;
    }

    public void setCurrentplyer(int currentplyer) {
        this.currentplyer = currentplyer;
    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public BoardGame getMyboard() {
        return myboard;
    }

    public BoardGame getOther() {
        return other;
    }

    public int getCurrentplyer() {
        return currentplyer;
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }


    public roomGame(BoardGame mybord, BoardGame other, int currentplayer, int x, int y)
    {
        myboard = mybord;
        other = other;
        currentplyer = currentplayer;
        currX = x;
        currY = y;
    }


    public void back(int x, int y)
    {

    }
}

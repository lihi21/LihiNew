package com.example.myapplication;

import static java.util.Arrays.asList;

import android.content.Intent;
import android.util.Log;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class roomGame
{


    private String status;


    public void setStatusOther(String statusOther) {
        this.statusOther = statusOther;
    }

    private  String statusOther;

    public int getGameOwner() {
        return gameOwner;
    }

    public void setGameOwner(int gameOwner) {
        this.gameOwner = gameOwner;
    }

    private int gameOwner;

    private int[][]   myboard, other;

    private int currentPlayer;
    private int currX, currY;

/*
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


 */

    //  GameLogic gameLogic;
/*
    public void setMyboard(BoardGame myboard) {
        this.myboard = myboard;
    }

    public void setOther(BoardGame other) {
        this.other = other;
    }

 */
  public String getStatusOther() {
      return statusOther;
  }


    public void setcurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Integer> getMyboard() {

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < myboard.length; i++) {
            for (int j = 0; j < myboard.length ; j++) {
                arr.add(myboard[i][j]);

            }

        }
        return arr;
    }
    public int[][] getMyOriginalArray()
    {
        return myboard;
    }
    public int[][] getotherArray()
    {
        return other;
    }



    public void setMyboard(ArrayList<Long> arr) {

        // map 1D array to 2D array
        for (int i = 0; i < arr.size(); i++) {
            int value =arr.get(i).intValue();
           int row = i/6;
            int col = i%6;
            myboard[row][col] =value;// arr.get(i).intValue();


        }
    }

    public ArrayList<Integer> getOther() {

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < other.length; i++) {
            for (int j = 0; j < other.length ; j++) {
                arr.add(other[i][j]);

            }

        }
        return arr;

      //  return other;
    }

    public void setOther(ArrayList<Long> arr) {

        // map 1D array to 2D array
        for (int i = 0; i < arr.size(); i++) {
            int row = i/6;
            int col = i%6;
            other[row][col] = arr.get(i).intValue();


        }

    }


    public int getcurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }
/*
    public GameLogic getGameLogic() {
        return gameLogic;
    }

 */


    // this

    public Map<String,Object> roomGameToMap() {
        // dictionary
        Map<String, Object> map = new HashMap<>();
        map.put("currX", this.currX);
        map.put("currY", this.currY);
        map.put("currentplyer", this.currentPlayer);
        map.put("gameOwner", this.gameOwner);
        map.put("status", this.status);
        map.put("statusOther", this.statusOther);

        map.put("myboard", getMyboard());
        map.put("other", getOther());
        return map;
    }
    public roomGame(Map<String, Object> map) {
        this.currX = Integer.parseInt(map.get("currX").toString());
        this.currY = Integer.parseInt(map.get("currY").toString());
        this.currentPlayer = Integer.parseInt(map.get("currentplyer").toString());
        this.gameOwner = Integer.parseInt(map.get("gameOwner").toString());
        this.status = map.get("status").toString();
        this.statusOther = map.get("statusOther").toString();

        this.myboard = new int[6][6];
        this.other = new int[6][6];
        ArrayList<Long> arrBoard = null;
        ArrayList<Long> arrOther = null;
        try {

            arrBoard =   (ArrayList<Long>)map.get("myboard");

           arrOther =  (ArrayList<Long>) map.get("other");
            this.setMyboard(arrBoard);
            this.setOther(arrOther);
        }
        catch (Exception e)
        {
            Log.d("Map to Room Game", "roomGame: " + e.getMessage());
        }


    }

    public roomGame(int currentplayer, int x, int y)
    {
        myboard = new int[6][6];
        other = new int[6][6];
        currentPlayer = currentPlayer;
        currX = x;
        currY = y;
    }
    public roomGame() {
        myboard = new int[6][6];
        other = new int[6][6];
        currentPlayer = GameConst.Host;
        currX = -1;
        currY = -1;
        status = "BUILD";
        statusOther= "BUILD";
        gameOwner = GameConst.Host;

    }



    public void back(int x, int y)
    {

    }
}

package com.example.size.models;

public class Board {

    private int[][] board = new int[][]{{1, 2, 3},
        {4, 5, 6},
        {7, 7, 9}};


    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }


    public int getIncorrectPositionCount(){
        int count = 0;
        int itemCount = 0;
        for (int i = 0; i< board.length; i++){
            for (int j = 0; j< board[i].length; j++){
                itemCount++;
                if (itemCount != board[i][j]) count++;
                System.out.print("(" + itemCount + " - " + board[i][j] + ") ");

            }
            System.out.println();
        }
        System.out.println(count);
        return 0;
    }

}

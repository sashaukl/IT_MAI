package com.example.fifteengame.models;

public class Item{
    private Item prevBoard;
    private Board board;

    public Item(Item prevBoard, Board board) {
        this.prevBoard = prevBoard;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Item getPrevBoard() {
        return prevBoard;
    }
}
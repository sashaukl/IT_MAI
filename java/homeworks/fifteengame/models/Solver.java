package com.example.fifteengame.models;

public interface Solver {

    boolean isSolvable();
    int moves();
    Iterable<Board> solution();

}

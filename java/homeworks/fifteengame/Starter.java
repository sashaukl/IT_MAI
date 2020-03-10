package com.example.fifteengame;

import com.example.fifteengame.models.Board;
import com.example.fifteengame.models.imeplementations.BoardImpl;
import com.example.fifteengame.models.Solver;
import com.example.fifteengame.models.imeplementations.SolverImpl;

public class Starter {

    public static void main(String[] s){
        int[][] blocks = new int[][]{{1, 2, 3},
                                     {4, 0, 5},
                                     {7, 8, 6}};
        Board initial = new BoardImpl(blocks);
        Solver solver = new SolverImpl(initial);
        System.out.println("NeedMoves = " + solver.moves());
        for (Board board : solver.solution())
            System.out.println(board);
    }

}

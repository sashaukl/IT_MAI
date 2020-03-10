package com.example.size;

import com.example.size.models.Board;

public class MyClass {

    public static void main(String... s){
        System.out.println("----");
        Board b = new Board();

        b.setBoard(new int[][]{{2, 5, 6},
                               {1, 5, 3},
                               {8, 7, 9}});

        b.getIncorrectPositionCount();

    }
}

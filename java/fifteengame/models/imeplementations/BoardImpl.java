package com.example.fifteengame.models.imeplementations;

import com.example.fifteengame.models.Board;

import java.util.HashSet;
import java.util.Set;

public class BoardImpl extends Board {


    public BoardImpl(int[][] blocks) {
        int[][] blocks2 = deepCopy(blocks);
        this.blocks = blocks2;

        h = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] != (i*dimension() + j + 1) && blocks[i][j] != 0) {
                    h += 1;
                }
                if (blocks[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }
    }


    public int dimension() {
        return blocks.length;
    }

    public Iterable<BoardImpl> neighbors() {
        Set<BoardImpl> boardList = new HashSet<BoardImpl>();
        boardList.add(chng(getNewBlock(), zeroX, zeroY, zeroX, zeroY + 1));
        boardList.add(chng(getNewBlock(), zeroX, zeroY, zeroX, zeroY - 1));
        boardList.add(chng(getNewBlock(), zeroX, zeroY, zeroX - 1, zeroY));
        boardList.add(chng(getNewBlock(), zeroX, zeroY, zeroX + 1, zeroY));

        return boardList;
    }

    private int[][] getNewBlock() { //  опять же, для неизменяемости
        return deepCopy(blocks);
    }

    private BoardImpl chng(int[][] blocks2, int x1, int y1, int x2, int y2) {

        if (x2 > -1 && x2 < dimension() && y2 > -1 && y2 < dimension()) {
            int t = blocks2[x2][y2];
            blocks2[x2][y2] = blocks2[x1][y1];
            blocks2[x1][y1] = t;
            return new BoardImpl(blocks2);
        } else
            return null;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("n");
        }
        return s.toString();
    }

    private static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = new int[original[i].length];
            for (int j = 0; j < original[i].length; j++) {
                result[i][j] = original[i][j];
            }
        }
        return result;
    }

}

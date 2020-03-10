package com.example.fifteengame.models.imeplementations;

import com.example.fifteengame.models.Board;
import com.example.fifteengame.models.Item;
import com.example.fifteengame.models.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;


public class SolverImpl implements Solver {

    private List<Board> result = new ArrayList<>();


    public SolverImpl(Board initial) {
        if(!isSolvable()) return;

        PriorityQueue<Item> priorityQueue = new PriorityQueue<>(10, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return new Integer(measure(o1)).compareTo(new Integer(measure(o2)));
            }
        });

        priorityQueue.add(new Item(null, initial));

        while (true){
            Item board = priorityQueue.poll();
            if(board.getBoard().isGoal()) {
                itemToList(new Item(board, board.getBoard()));
                return;
            }

            Iterator iterator = board.getBoard().neighbors().iterator();
            while (iterator.hasNext()){
                BoardImpl board1 = (BoardImpl) iterator.next();
                if(board1!= null && !containsInPath(board, board1))
                    priorityQueue.add(new Item(board, board1));
            }

        }
    }

    private int measure(Item item){
        Item item2 = item;
        int c= 0;
        int measure = item.getBoard().h();
        while (true){
            c++;
            item2 = item2.getPrevBoard();
            if(item2 == null) {
                return measure + c;
            }
        }
    }

    private void itemToList(Item item){
        Item item2 = item;
        while (true){
            item2 = item2.getPrevBoard();
            if(item2 == null) {
                Collections.reverse(result);
                return;
            }
            result.add(item2.getBoard());
        }
    }

    private boolean containsInPath(Item item, BoardImpl board){
        Item item2 = item;
        while (true){
            if(item2.getBoard().equals(board)) return true;
            item2 = item2.getPrevBoard();
            if(item2 == null) return false;
        }
    }

    @Override
    public boolean isSolvable() {
        return true;
    }

    @Override
    public int moves() {
        if(!isSolvable()) return -1;
        return result.size() - 1;
    }

    @Override
    public Iterable<Board> solution() {
        return result;
    }


}
package com.sweetdum.dschess.game.piece;

import com.sweetdum.dschess.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * The chess piece entity class.
 * Created by Mengxiao Lin on 2016/11/22.
 */
public abstract class Piece {
    private int owner;
    private ArrayList<Tuple<Integer>> possibleSteps;
    public Piece(int owner){
        this.owner = owner;
        possibleSteps = new ArrayList<>();
    }

    /**
     * get owner id of this piece
     * @return an int to represent the owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Return a list that contains all possible steps of this piece.
     * For example (1,0) represent (X+1, Y+0) is a possible for the piece, (X, Y) is the current position.
     * @return a list that contains all possible steps of this piece in tuple.
     */
    public List<Tuple<Integer>> possibleSteps() {
        return (List<Tuple<Integer>>)possibleSteps.clone();
    }

    protected void registerPossibleStep(Tuple<Integer> step){
        possibleSteps.add(step);
    }
}

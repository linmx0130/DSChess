package com.sweetdum.dschess.game.piece;

import com.sweetdum.dschess.utils.Tuple;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
    public static Piece buildFromString(String desc){
        Scanner scanner = new Scanner(desc);
        String type = scanner.next();
        int owner = scanner.nextInt();
        Piece ret= null;
        if (type.equals("Bishop")){
            ret= new Bishop(owner);
        }
        if (type.equals("King")){
            ret = new King(owner);
        }
        if (type.equals("Knight")){
            ret = new Knight(owner);
        }
        if (type.equals("Queen")){
            ret = new Queen(owner);
        }
        if (type.equals("Rook")){
            ret = new Rook(owner);
        }
        if (type.equals("Pawn")){
            int direction = scanner.nextInt();
            boolean isPoorPawn = scanner.nextBoolean();
            ret = new Pawn(owner,direction);
            ((Pawn)ret).setPoorPawn(isPoorPawn);
        }
        return ret;
    }
}

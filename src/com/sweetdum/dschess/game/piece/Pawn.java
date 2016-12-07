package com.sweetdum.dschess.game.piece;

import com.sweetdum.dschess.utils.Tuple;

/**
 * The implementation of pawn.
 * Created by Mengxiao Lin on 2016/11/22.
 */
public class Pawn extends Piece {
    private int direction;
    /**
     * Construct a pawn.
     * @param owner
     * @param direction -1 or 1 to represent the direction of this pawn.
     */
    public Pawn(int owner, int direction) {
        super(owner);
        this.direction = direction;
        registerPossibleStep(new Tuple<>(0,direction));
        //special cases
        registerPossibleStep(new Tuple<>(0,2 * direction));
        registerPossibleStep(new Tuple<>(-1,direction));
        registerPossibleStep(new Tuple<>(1,direction));
    }
    boolean poorPawn;

    public boolean isPoorPawn() {
        return poorPawn;
    }

    public void setPoorPawn(boolean poorPawn) {
        this.poorPawn = poorPawn;
    }

    @Override
    public String toString() {
        return "Pawn "+this.getOwner()+" "+this.direction+" "+this+isPoorPawn();
    }
}

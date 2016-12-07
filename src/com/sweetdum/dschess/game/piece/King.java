package com.sweetdum.dschess.game.piece;

import com.sweetdum.dschess.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of king.
 * Created by Mengxiao Lin on 2016/11/22.
 */
public class King extends Piece{
    public King(int owner) {
        super(owner);
        registerPossibleStep(new Tuple<>(1,1));
        registerPossibleStep(new Tuple<>(1,0));
        registerPossibleStep(new Tuple<>(1,-1));
        registerPossibleStep(new Tuple<>(0,1));
        registerPossibleStep(new Tuple<>(0,-1));
        registerPossibleStep(new Tuple<>(-1,1));
        registerPossibleStep(new Tuple<>(-1,0));
        registerPossibleStep(new Tuple<>(-1,-1));
    }

    @Override
    public String toString() {
        return "King "+this.getOwner();
    }
}

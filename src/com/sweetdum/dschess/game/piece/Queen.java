package com.sweetdum.dschess.game.piece;

import com.sweetdum.dschess.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of queen.
 * Created by Mengxiao Lin on 2016/11/22.
 */
public class Queen extends Piece{
    public Queen(int owner) {
        super(owner);
        for (int i=-7;i<=7;++i) {
            if (i == 0) continue;
            registerPossibleStep(new Tuple<>(i, i));
            registerPossibleStep(new Tuple<>(i, -i));
            registerPossibleStep(new Tuple<>(i, 0));
            registerPossibleStep(new Tuple<>(0, i));
        }
    }

    @Override
    public String toString() {
        return "Queen "+ this.getOwner();
    }
}

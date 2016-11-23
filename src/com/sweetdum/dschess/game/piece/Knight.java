package com.sweetdum.dschess.game.piece;

import com.sweetdum.dschess.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mengxiao Lin on 2016/11/22.
 */
public class Knight extends Piece {
    public Knight(int owner) {
        super(owner);
        registerPossibleStep(new Tuple<>(2,1));
        registerPossibleStep(new Tuple<>(2,-1));
        registerPossibleStep(new Tuple<>(1,2));
        registerPossibleStep(new Tuple<>(1,-2));
        registerPossibleStep(new Tuple<>(-2,1));
        registerPossibleStep(new Tuple<>(-2,-1));
        registerPossibleStep(new Tuple<>(-1,2));
        registerPossibleStep(new Tuple<>(-1,-2));
    }
}

package com.sweetdum.dschess.game.piece;

import com.sweetdum.dschess.utils.Tuple;

/**
 * Created by Mengxiao Lin on 2016/11/22.
 */
public class Rock extends Piece {
    public Rock(int owner) {
        super(owner);
        for (int i=-7;i<=7;++i) {
            if (i == 0) continue;
            registerPossibleStep(new Tuple<>(i, 0));
            registerPossibleStep(new Tuple<>(0, i));
        }
    }
}

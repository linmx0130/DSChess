package com.sweetdum.dschess.game;

import com.sweetdum.dschess.game.piece.Piece;
import com.sweetdum.dschess.utils.Tuple;

import java.util.List;

/**
 * Created by Mengxiao Lin on 2016/12/7.
 */
public interface IGameControllerAdapter {
    Piece getPieceAt(int x, int y);
    List<Tuple<Integer>> getPossibleStepsOfPiece(int x, int y);
    boolean movePiece(int x, int y, Tuple<Integer>step);
}

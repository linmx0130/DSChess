package com.sweetdum.dschess.ai;

import com.sweetdum.dschess.game.IGameControllerAdapter;
import com.sweetdum.dschess.game.piece.Piece;

/**
 * The base class of all AI agents.
 * Created by Mengxiao Lin on 2016/11/23.
 */
public interface ChessAI {
    /**
     * Called by the controller when it is your turn
     * @param adapter the game controller adapter
     */
    void doAction(IGameControllerAdapter adapter);

    /**
     * Called by the controller when one of your pawn is touching the board line
     * @param adapter the game controller adapter
     * @param x the x coordinate of the pawn
     * @param y the y coordinate of the pawn
     * @return a piece instance to show the piece you want. Must be in {Knight, Rook, Bishop, Queen}
     */
    Piece pawnUpgrade(IGameControllerAdapter adapter, int x, int y);
}

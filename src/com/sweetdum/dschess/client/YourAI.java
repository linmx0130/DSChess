package com.sweetdum.dschess.client;

import com.sweetdum.dschess.ai.ChessAI;
import com.sweetdum.dschess.game.IGameControllerAdapter;
import com.sweetdum.dschess.game.piece.Piece;

/**
 * Modify this file, please!
 */
public class YourAI implements ChessAI {
    int playerId;
    public YourAI(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public void doAction(IGameControllerAdapter adapter) {

    }

    @Override
    public Piece pawnUpgrade(IGameControllerAdapter adapter, int x, int y) {
        return null;
    }
}

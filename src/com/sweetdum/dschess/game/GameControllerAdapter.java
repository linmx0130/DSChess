package com.sweetdum.dschess.game;

import com.sweetdum.dschess.game.piece.Piece;
import com.sweetdum.dschess.utils.Tuple;

import java.util.List;

/**
 * Created by Mengxiao Lin on 2016/11/23.
 */
public class GameControllerAdapter {
    private int owner;
    private ChessBoard chessBoard;
    private GameController controller;
    GameControllerAdapter(int owner, ChessBoard chessBoard, GameController controller){
        this.owner = owner;
        this.chessBoard = chessBoard;
        this.controller = controller;
    }

    /**
     * Get the piece object at (x,y)
     * @param x the x coordinate of the piece
     * @param y the y coordinate of the piece
     * @return the piece object at (x,y). Return null if there is no piece at (x,y).
     */
    public Piece getPieceAt(int x, int y){
        return chessBoard.getPieceAt(x,y);
    }
    /**
     * Get all possible steps of piece at (x, y)
     * @param x the x coordinate of the piece
     * @param y the y coordinate of the piece
     * @return a list contains all possible steps, null if there is no chess at (x, y)
     */
    public List<Tuple<Integer>> getPossibleStepsOfPiece(int x, int y) {
        return chessBoard.getPossibleStepsOfPiece(x,y);
    }

    /**
     * move a piece
     * @param x the x coordinate of the piece
     * @param y the y coordinate of the piece
     * @param step the step to move
     * @return success or no
     */
    public boolean movePiece(int x, int y, Tuple<Integer>step) {
        if (controller.isCurrentTurnFinished()) return false;
        boolean t=chessBoard.movePiece(x,y,step,owner);
        if (t) controller.setCurrentTurnFinished(true);
        return t;
    }
}

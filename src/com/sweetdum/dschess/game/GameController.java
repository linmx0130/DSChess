package com.sweetdum.dschess.game;

import com.sweetdum.dschess.ai.ChessAI;
import com.sweetdum.dschess.game.piece.*;

/**
 * Created by Mengxiao Lin on 2016/11/23.
 */
public class GameController {
    private ChessBoard chessBoard;
    private ChessAI agents[];
    private GameControllerAdapter adapter[];
    private int currentPlayer;
    private boolean currentTurnFinished;
    public GameController(){
        chessBoard = new ChessBoard();
        agents = new ChessAI[2];
        adapter = new GameControllerAdapter[2];
        currentPlayer = 0;
        currentTurnFinished = true;
    }
    public void setAgent(int id, ChessAI ai){
        agents[id]= ai;
        adapter[id] = new GameControllerAdapter(id, chessBoard, this);
    }
    void setCurrentTurnFinished(boolean currentTurnFinished){
        this.currentTurnFinished = currentTurnFinished;
    }
    public void nextTurn(){
        currentTurnFinished = false;
        currentPlayer = currentPlayer^1;
        while (!currentTurnFinished){
            agents[currentPlayer].doAction(adapter[currentPlayer]);
        }
        for (int i=0;i<8;++i){
            for (int j=0;j<=8;++j){
                if (chessBoard.getPieceAt(i,j) instanceof Pawn){
                    Pawn pawn = (Pawn) chessBoard.getPieceAt(i,j);
                    if (pawn.isPoorPawn()&& pawn.getOwner()!=currentPlayer){
                        pawn.setPoorPawn(false);
                    }
                }
            }
        }
        //check board line
        for (int i=0;i<8;++i){
            for (int bl = 0; bl<=7; bl+=7) {
                if (chessBoard.getPieceAt(bl, i) instanceof Pawn) {
                    int owner = chessBoard.getPieceAt(bl, i).getOwner();
                    do {
                        Piece newPiece = agents[owner].pawnUpgrade(adapter[owner], bl, i);
                        if ((newPiece instanceof Queen) || (newPiece instanceof Knight) ||
                                (newPiece instanceof Rook) || (newPiece instanceof Bishop)) {
                            chessBoard.setPiece(bl, i, newPiece);
                            break;
                        }
                    } while (true);
                }
            }
        }
    }
    public boolean isCurrentTurnFinished() {
        return currentTurnFinished;
    }
}
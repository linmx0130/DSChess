package com.sweetdum.dschess.game;

import com.sweetdum.dschess.game.piece.*;
import com.sweetdum.dschess.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mengxiao Lin on 2016/11/22.
 */
class ChessBoard {
    private Piece pieces[][];
    ChessBoard() {
        pieces = new Piece[8][8];
        //init the board
        pieces[0][0] = new Rook(0);
        pieces[0][1] = new Knight(0);
        pieces[0][2] = new Bishop(0);
        pieces[0][3] = new King(0);
        pieces[0][4] = new Queen(0);
        pieces[0][5] = new Bishop(0);
        pieces[0][6] = new Knight(0);
        pieces[0][7] = new Rook(0);
        pieces[7][0] = new Rook(1);
        pieces[7][1] = new Knight(1);
        pieces[7][2] = new Bishop(1);
        pieces[7][3] = new King(1);
        pieces[7][4] = new Queen(1);
        pieces[7][5] = new Bishop(1);
        pieces[7][6] = new Knight(1);
        pieces[7][7] = new Rook(1);
        for (int i=0;i<8;++i){
            pieces[1][i] = new Pawn(0,1);
            pieces[6][i] = new Pawn(1,-1);
        }
    }
    private boolean inBoard(int x, int y){
        return 0<=x && x<7 && 0<=y && y<7;
    }
    Piece getPieceAt(int x, int y){
        if (!inBoard(x,y)) return null;
        return pieces[x][y];
    }

    /**
     * To check whether it is possible to move the piece at (x,y) with step
     * @param x the x coordinate of the piece
     * @param y the y coordinate of the piece
     * @param step the step tuple
     * @return possible or not
     */
    private boolean isPossibleStep(int x, int y, Tuple<Integer> step){
        if (pieces[x][y] ==null) return false;
        int nx = x + step.x1();
        int ny = y + step.x2();
        if (!inBoard(nx,ny)) return false;
        // special case : pawn
        if (pieces[x][y] instanceof Pawn) {
            Pawn pawn = (Pawn) pieces[x][y];
            // go forward
            if (step.x1()==0) {
                if (pieces[nx][ny] != null) return false;
                if (Math.abs(step.x2()) == 1) return true;
                if (Math.abs(step.x2()) == 2) {
                    return (x == 1 && pawn.getOwner() == 0) || (x == 6 && pawn.getOwner() == 1);
                }
            }else {
                if (pieces[nx][ny]!=null){
                    return true;
                }
                if (pieces[nx][ny - step.x2()]!=null){
                    if (pieces[nx][ny-step.x2()] instanceof Pawn) {
                        Pawn npawn = (Pawn) pieces[nx][ny - step.x2()];
                        return npawn.isPoorPawn();
                    }
                }
                return false;
            }
        }
        // to check whether there is a piece in the path
        if (pieces[x][y] instanceof Knight || pieces[x][y] instanceof King) return true;
        int sigx1 = (int)Math.signum(step.x1());
        int sigx2 = (int)Math.signum(step.x2());
        int stepsCount = Math.max(Math.abs(step.x1()), Math.abs(step.x2()));
        for (int i=0;i<stepsCount-1;++i){
            int cx =x+sigx1*i;
            int cy = y+sigx2*i;
            if (pieces[cx][cy] !=null) return false;
        }
        return true;
    }

    /**
     * move a piece
     * @param x the x coordinate of the piece
     * @param y the y coordinate of the piece
     * @param step the step to move
     * @param owner the owner id of the piece
     * @return success or not
     */
    boolean movePiece(int x, int y, Tuple<Integer>step, int owner){
        if (!isPossibleStep(x,y,step)) return false;
        if (pieces[x][y].getOwner() != owner) return false;
        int nx = x + step.x1();
        int ny = y + step.x2();
        if (pieces[x][y] instanceof Pawn){
            Pawn pawn = (Pawn) pieces[x][y];
            if (Math.abs(step.x2())==2){
                pawn.setPoorPawn(true);
            }
            if (step.x1()!=0){
                if (pieces[nx][ny] == null){
                    pieces[nx][ny - step.x2()] = null;
                }
            }
        }
        pieces[nx][ny] = pieces[x][y];
        pieces[x][y]=null;
        return true;
    }
    /**
     * Get all possible steps of piece at (x, y)
     * @param x the x coordinate of the piece
     * @param y the y coordinate of the piece
     * @return a list contains all possible steps, null if there is no chess at (x, y)
     */
    List<Tuple<Integer>> getPossibleStepsOfPiece(int x,int y){
        if (!inBoard(x,y)) return null;
        if (pieces[x][y]==null) return null;
        ArrayList<Tuple<Integer>> ret = new ArrayList<>();
        for (Tuple<Integer> s: pieces[x][y].possibleSteps()){
            if (isPossibleStep(x,y,s)){
                ret.add(s);
            }
        }
        return ret;
    }
    void setPiece(int x, int y, Piece piece){
        pieces[x][y]=piece;
    }
}

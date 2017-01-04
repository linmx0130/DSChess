package com.sweetdum.dschess.client;

import com.sweetdum.dschess.ai.ChessAI;
import com.sweetdum.dschess.game.IGameControllerAdapter;
import com.sweetdum.dschess.game.piece.Piece;
import com.sweetdum.dschess.game.piece.Queen;
import com.sweetdum.dschess.utils.ListTupleTool;
import com.sweetdum.dschess.utils.Tuple;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Mengxiao Lin on 2016/12/21.
 */
public class HumanAI implements ChessAI {
    int playerId;
    Scanner cin =new Scanner(System.in);
    public HumanAI(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public void doAction(IGameControllerAdapter adapter) {
        System.out.println("Input Command!");
        System.out.print(">>> ");
        String command = cin.nextLine();
        Scanner sin = new Scanner(command);
        String word = sin.next();
        if (word.equals("P")){
            int x = sin.nextInt();
            int y = sin.nextInt();
            Piece p = adapter.getPieceAt(x,y);
            if (p==null) System.out.println("NULL");
            else System.out.println(p.toString());
        }
        if (word.equals("S")){
            int x = sin.nextInt();
            int y = sin.nextInt();
            List<Tuple<Integer>> ans = adapter.getPossibleStepsOfPiece(x,y);
            System.out.println(ListTupleTool.getStringFromListTuple(ans));
        }
        if (word.equals("M")){
            int x = sin.nextInt();
            int y = sin.nextInt();
            int dx = sin.nextInt();
            int dy = sin.nextInt();
            boolean ret = adapter.movePiece(x,y,new Tuple<>(dx,dy));
            if (ret){
                System.out.println("Success!");
            }else{
                System.out.println("Failure!");
            }
        }
    }

    @Override
    public Piece pawnUpgrade(IGameControllerAdapter adapter, int x, int y) {
        return new Queen(playerId);
    }
}

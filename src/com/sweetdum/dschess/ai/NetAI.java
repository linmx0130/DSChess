package com.sweetdum.dschess.ai;

import com.sweetdum.dschess.game.GameControllerAdapter;
import com.sweetdum.dschess.game.IGameControllerAdapter;
import com.sweetdum.dschess.game.piece.Piece;
import com.sweetdum.dschess.utils.ListTupleTool;
import com.sweetdum.dschess.utils.Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Mengxiao Lin on 2016/12/7.
 */
public class NetAI implements ChessAI {
    private BufferedReader reader;
    private PrintWriter writer;
    public NetAI(BufferedReader reader, PrintWriter writer){
        this.reader = reader;
        this.writer = writer;
    }
    @Override
    public void doAction(IGameControllerAdapter adapter) {
        writer.println("ACTION!");writer.flush();
        String command="";
        try {
            command = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
            return ;
        }
        Scanner comscan = new Scanner(command);
        if (!comscan.hasNext()){
            return;
        }
        String comWord = comscan.next();
        if (comWord.equals("P")){ //getPieceAt
            int x = comscan.nextInt();
            int y = comscan.nextInt();
            Piece p = adapter.getPieceAt(x,y);
            if (p!=null) {
                writer.println(p.toString());
            }else{
                writer.println("null");
            }
            writer.flush();
        }
        if (comWord.equals("S")){
            //getPossibleStepsOfPiece
            int x = comscan.nextInt();
            int y = comscan.nextInt();
            writer.println(ListTupleTool.getStringFromListTuple(adapter.getPossibleStepsOfPiece(x,y)));
            writer.flush();
        }
        if (comWord.equals("M")){
            int x = comscan.nextInt();
            int y = comscan.nextInt();
            int dx = comscan.nextInt();
            int dy = comscan.nextInt();
            boolean ret = adapter.movePiece(x,y,new Tuple<>(dx,dy));
            writer.println(ret);
            writer.flush();
        }
    }

    @Override
    public Piece pawnUpgrade(IGameControllerAdapter adapter, int x, int y) {
        writer.println("PAWNUPGRADE! "+x+" "+y);
        writer.flush();
        String pieceDesc;
        try {
            pieceDesc = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        return Piece.buildFromString(pieceDesc);
    }
}

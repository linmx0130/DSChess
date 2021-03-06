package com.sweetdum.dschess.client;

import com.sweetdum.dschess.game.IGameControllerAdapter;
import com.sweetdum.dschess.game.piece.Piece;
import com.sweetdum.dschess.utils.ListTupleTool;
import com.sweetdum.dschess.utils.Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Mengxiao Lin on 2016/12/7.
 */
public class ClientAdapter implements IGameControllerAdapter {
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientAdapter(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public Piece getPieceAt(int x, int y) {
        writer.println("P "+x+" "+y);
        writer.flush();
        try {
            do {
                String response = reader.readLine();
                if ("ACTION!".equals(response)) continue;
                return Piece.buildFromString(response);
            }while(true);
        }catch (IOException e) {
            return null;
        }
    }

    @Override
    public List<Tuple<Integer>> getPossibleStepsOfPiece(int x, int y) {
        writer.println("S "+x+" "+y);
        writer.flush();
        try {
            do {
                String response = reader.readLine();
                if ("ACTION!".equals(response)) continue;
                return ListTupleTool.getListTupleFromString(response);
            }while (true);
        }catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean movePiece(int x, int y, Tuple<Integer> step) {
        writer.println("M "+x+" "+y+" "+step.x1()+" "+step.x2());
        writer.flush();
        try {
            do {
                String response = reader.readLine();
                if ("ACTION!".equals(response)) continue;
                return Boolean.parseBoolean(response);
            }while(true);
        }catch (IOException e) {
            return false;
        }
    }
}

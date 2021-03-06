package com.sweetdum.dschess.client;

import com.sweetdum.dschess.ai.ChessAI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Mengxiao Lin on 2016/12/7.
 */
public class GameClient {
    private static int playerId;
    private static BufferedReader reader;
    private static PrintWriter writer;
    public static void main(String args[]) throws IOException{
        Socket socket = new Socket("localhost",1471);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
        System.out.print("Key: ");
        Scanner cin = new Scanner(System.in);
        int key = Integer.parseInt(cin.nextLine());
        writer.println("KEY"+key);
        writer.flush();
        String response = reader.readLine();
        Scanner responseScanner = new Scanner(response);
        responseScanner.next();
        playerId = responseScanner.nextInt();

        //ChessAI yourAI = new HumanAI(playerId);
        ChessAI yourAI = new YourAI(playerId);
        ClientAdapter adapter = new ClientAdapter(reader, writer);
        //listen
        do {
            String command = reader.readLine();
            Scanner sin = new Scanner(command);
            String comWord = sin.next();
            if (comWord.equals("ACTION!")){
                yourAI.doAction(adapter);
            }
            if (comWord.equals("PAWNUPGRADE!")){
                int x = sin.nextInt();
                int y = sin.nextInt();
                writer.println(yourAI.pawnUpgrade(adapter,x,y));
                writer.flush();
            }
        }while(true);
    }
}

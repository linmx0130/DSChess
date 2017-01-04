package com.sweetdum.dschess.server;

import com.sweetdum.dschess.ai.NetAI;
import com.sweetdum.dschess.game.GameController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Mengxiao Lin on 2016/12/7.
 */
public class GameServer {
    private static BufferedReader[] clientReaders = new BufferedReader[2];
    private static PrintWriter[] clientWriters = new PrintWriter[2];
    private static GameController controller = new GameController();
    private static int key[];
    private static void waitAgentLogin(ServerSocket serverSocket) throws IOException{
        do{
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String keyCommand= reader.readLine();
            boolean accepted = false;
            for (int i=0;i<2;++i) {
                String target = "KEY"+key[i];
                if (target.equals(keyCommand)){
                    clientReaders[i]=reader;
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    clientWriters[i]=writer;
                    writer.println("OK "+i);
                    writer.flush();
                    System.out.println("Agent "+i+" login!");
                    accepted = true;
                    break;
                }
            }
            if (!accepted){
                socket.close();
            }
        }while (clientReaders[0] == null || clientReaders[1] ==null);
    }
    public static void main(String args[]) throws Exception{
        int portNumber = 1471;
        if (args.length==1){
            portNumber = Integer.parseInt(args[0]);
        }
        Random random = new Random();
        key = new int[2];
        key[0]= random.nextInt()%10000+10000;
        key[1] =random.nextInt()%10000+10000;
        for (int i=0;i<2;++i){
            System.out.println("Key for agent "+i+": "+key[i]);
        }
        ServerSocket serverSocket = new ServerSocket(portNumber);
        waitAgentLogin(serverSocket);
        controller.setAgent(0, new NetAI(clientReaders[0], clientWriters[0]));
        controller.setAgent(1, new NetAI(clientReaders[1], clientWriters[1]));
        //run the game
        int turn = 0;
        Scanner cin = new Scanner(System.in);
        do{
            System.out.println("Chess board:");
            controller.printChessBoard();
            System.out.println("=========================");
            System.out.println("Turn: "+ turn);
            System.out.println("Press enter to next turn");
            cin.nextLine();
            controller.nextTurn();
            int winner = controller.winner();
            if (winner!=-1) {
                controller.printChessBoard();
                System.out.println("Game over!");
                System.out.println("Winner is "+winner);
                break;
            }
            ++turn;
        }while(true);
    }

}

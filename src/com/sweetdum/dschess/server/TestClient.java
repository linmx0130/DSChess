package com.sweetdum.dschess.server;

import com.sweetdum.dschess.ai.ChessAI;
import com.sweetdum.dschess.client.ClientAdapter;
import com.sweetdum.dschess.client.YourAI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Mengxiao Lin on 2016/12/7.
 */
public class TestClient {
    public static void main(String args[]) throws Exception{
        Socket socket = new Socket("localhost",1471);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        System.out.print("Key: ");
        Scanner cin = new Scanner(System.in);
        int key = Integer.parseInt(cin.nextLine());
        writer.println("KEY"+key);
        writer.flush();
        String response = reader.readLine();
        int id = Integer.parseInt(response.split(" ")[1]);
        System.out.println(response);
        do{
            do {
                response = reader.readLine();
                System.out.println(response);
                if (response.startsWith("PAWNUPGRADE!")){
                    writer.print("Queen ");
                    writer.println(id);
                    writer.flush();
                }
            }while(!response.equals("ACTION!"));
            System.out.print("Command: ");
            String s = cin.nextLine().trim();
            writer.println(s);
            writer.flush();
        }while(true);
    }
}

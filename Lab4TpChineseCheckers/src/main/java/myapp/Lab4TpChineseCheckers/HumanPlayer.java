package myapp.Lab4TpChineseCheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HumanPlayer extends Player {

	Socket socket;
    BufferedReader input;
    PrintWriter output;
    int noPlayer;
    
    public HumanPlayer(Socket socket, int noPlayer) {
        this.socket = socket;
        this.noPlayer = noPlayer;
        try {
            input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + this.noPlayer);
            output.println("MESSAGE Waiting for opponents to connect");
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }
    
    public void otherPlayerMoved(int x,int y,int nx,int ny) {
        output.println("OPPONENT_MOVED " + x +" "+ y +" "+ nx +" "+ ny);
        output.println(
            //hasWinner() ? "DEFEAT" : boardFilledUp() ? "TIE" : "");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.Lab4TpChineseCheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Window;

/**
 *
 * @author xxxx
 */
public class Factory 
{
    Game game = new Game();
    Board board;
    Rules rules;
    
    private Pane board_pane;
    private TextField noPlayers;
    private TextField rulesType;
    private List<Client> clients;
    
    public Factory(TextField noPlayers, TextField rulesType, Pane board_pane)
    {
        this.noPlayers = noPlayers;
        this.rulesType = rulesType;
        this.board_pane = board_pane;
    }
    
    public void create_game() throws Exception //w tej czesci sprawdzamy warunki i jesli sa poprawne, to tworzymy plansze
    {
        if (rulesType.getText().equals("1"))
        {
            switch (noPlayers.getText()) 
            {
                case "2":
                    board = new Board121w2Players();
                    break;
                case "3":
                    board = new Board121w3Players();
                    break;
                case "4":
                    board = new Board121w4Players();
                    break;
                case "6":
                    board = new Board121w6Players();
                    break;
                default:
                    noPlayers.setText("ERROR");
                    break;
            }
            
            if(noPlayers.getText().equals("ERROR") ) 
            {
                
            } 
            else if (board == null)
            {
                
            } 
            else 
            {
                game.setBoard(board);
                game.setPawns(game.pawn);
                board.setUp();
                
                Dialog dialog = new Dialog();
                dialog.setTitle("Game settings (pawns colors)");
                ColorPicker[] array = new ColorPicker[board.getnoPlayers()+2];
                Color[] def_colors = {null, Color.BLACK, Color.RED,
                    Color.BLUE, Color.GREEN, Color.YELLOW,
                    Color.ORANGE, Color.KHAKI};
                DialogPane dialogPane = dialog.getDialogPane();
                dialogPane.setPrefHeight(20+(array.length)*20);
                dialogPane.setPrefWidth(250);
                for (int a=0; a<array.length; a++)
                {
                    Text text;
                    switch (a)
                    {
                        case 0:
                            text = new Text("Not in game");
                            break;
                        case 1:
                            text = new Text("In game");
                            break;
                        default:
                            text = new Text("Player " + (a-1));
                            break;
                    }
                    text.setLayoutX(5);
                    text.setLayoutY(20+a*20);
                    array[a] = new ColorPicker();
                    array[a].setLayoutX(100);
                    array[a].setLayoutY(16+a*20);
                    array[a].setValue(def_colors[a]);
                    dialogPane.getChildren().addAll(text, array[a]);
                }
                
                ButtonType start = new ButtonType("Start");
                dialog.getDialogPane().getButtonTypes().add(start);
                Button okButton = (Button) dialog.getDialogPane().lookupButton(start);
                dialog.show();
                Window window = dialog.getDialogPane().getScene().getWindow();
                window.setOnCloseRequest(event -> window.hide());
                
               
                Circle[][] circle = new Circle[21][21];
                double X = board_pane.getWidth();
                double Y = board_pane.getHeight();
                okButton.setOnAction((ActionEvent event) ->
                {
                    for(int a=0; a<21; a++)
                    {
                        for (int b=0; b<21; b++)
                        {
                            circle[a][b] = new Circle();
                            circle[a][b].setRadius(X/54);
                            circle[a][b].setCenterX((X/54)*20 + (X/27)*a - (X/54)*b);
                            circle[a][b].setCenterY(70 + (Y/27)*b); 
                            set_colors(circle[a][b], board.p[a][b], array);
                            circle[a][b].setVisible(true);
                            board_pane.getChildren().add(circle[a][b]);
                        }
                    } 
                });     
                
               /*  Server server = new Server(board.getnoPlayers());
                for (int a=0; a<board.getnoPlayers(); a++)
                {
                    Client client = new Client("localhost");
                    clients.add(a+1, client);
                } */
               
                register_clicks(circle);
            }
        
        }
        else
        {
            rulesType.setText("ERROR");
        }
        
     
    }
    
    public void register_clicks(Circle[][] circle)
    {
        board_pane.setOnMousePressed((MouseEvent event2) -> 
        {
            double x = event2.getX();
            double y = event2.getY();
            for (int a=0; a<circle.length; a++)
            {
                for (int b=0; b<circle[a].length; b++)
                {
                    if (circle[a][b].contains(x,y))
                    {
                        System.out.println("Kliknieto " + a + ", " + b);
                    }
                }
            }
            
        });
    }
    
    public void set_colors(Circle circle, int player, ColorPicker[] array) //ustalanie poszczegolnych kolorow, postaram sie tutaj dodac ColorPickery
    {
        for (int a=0; a<array.length; a++)
        {
            if(player == a-1)
            {
                        circle.setFill(array[a].getValue());
            }
        }
    }
 
}

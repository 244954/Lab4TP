/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.Lab4TpChineseCheckers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.GRAY;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.PINK;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.VIOLET;
import static javafx.scene.paint.Color.YELLOW;
import javafx.scene.shape.Circle;

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
    
    public Factory(TextField noPlayers, TextField rulesType, Pane board_pane)
    {
        this.noPlayers = noPlayers;
        this.rulesType = rulesType;
        this.board_pane = board_pane;
    }
    
    public void create_game() //w tej czesci sprawdzamy warunki i jesli sa poprawne, to tworzymy plansze
    {
        if (rulesType.getText().equals("1"))
        {
            rules = new OurRules();
            if (noPlayers.getText().equals("2"))
            {
                game.setnoPlayers(2);
                board = new Board121w2Players();
            }
            else if (noPlayers.getText().equals("3"))
            {
                game.setnoPlayers(3);
                board = new Board121w3Players();
            }
            else if (noPlayers.getText().equals("4"))
            {
                game.setnoPlayers(4);
                board = new Board121w4Players();
            }
            else if (noPlayers.getText().equals("6"))
            {
                game.setnoPlayers(6);
                board = new Board121w6Players();
            }
            else
            {
                noPlayers.setText("ERROR");
            }
            
            if(noPlayers.getText().equals("ERROR") ) 
            {
                
            } 
            else 
            {
                game.setBoard(board);
                game.setPawns(game.pawn);
                board.setUp();
                Circle[][] circle = new Circle[21][21];
                double X = board_pane.getWidth();
                double Y = board_pane.getHeight();
                
                for(int a=0; a<21; a++)
                {
                     for (int b=0; b<21; b++)
                     {
                        circle[a][b] = new Circle();
                        circle[a][b].setRadius(X/54);
                        circle[a][b].setCenterX((X/54)*20 + (X/27)*a - (X/54)*b);
                        circle[a][b].setCenterY(70 + (Y/27)*b); 
                        set_colors(circle[a][b], board.p[a][b]);
                        circle[a][b].setVisible(true);
                        board_pane.getChildren().add(circle[a][b]);
                     }
                }

            }
        
        }
        else
        {
            rulesType.setText("ERROR");
        }
        
     
    }
    
    public void set_colors(Circle circle, int player) //ustalanie poszczegolnych kolorow, postaram sie tutaj dodac ColorPickery
    {
                if(player == 0)
                {
                    circle.setFill(BLACK);
                }
                else if(player == 1)
                {
                    circle.setFill(RED);
                }
                else if(player == 2)
                {
                    circle.setFill(GREEN);
                }
                else if(player == 3)
                {
                    circle.setFill(BLUE);
                }
                else if(player == 4)
                {
                    circle.setFill(YELLOW);
                }
                else if(player == 5)
                {
                    circle.setFill(PINK);
                }
                else if(player == 6)
                {
                    circle.setFill(VIOLET);
                }
                else
                {
                    circle.setFill(GRAY);
                }
    }
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.Lab4TpChineseCheckers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/**
 *
 * @author xxxx
 */
public class GUIController implements Initializable 
{
    @FXML
    private Pane board;
    @FXML 
    private TextField noPlayers;
    @FXML
    private TextField rulesType;
    
    public void new_game() //to jest metoda, ktora sie wywoluje na nacisniecie przycisku Start
    {
        Factory factory = new Factory(noPlayers, rulesType, board);
        factory.create_game();
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) //bardzo skomplikowana inicjalizacja
    {

    }
    
}

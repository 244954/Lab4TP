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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class GUIController implements Initializable 
{
    @FXML
    private Pane board;
    
    @FXML 
    public TextField textfield;
    
    Factory factory;
    
    public void info()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Info");
        alert.setHeaderText("Chinese Checkers");
        alert.setContentText("Authors: Tomasz Karciarz, Mateusz Ma³ecki" + 
                "\n\nThe game allows currently to play in 4 different modes:"
                + "\n2, 3, 4, 6 players and 1 as default type of rules. \n\nThe rules are available on: "
                + "https://en.wikipedia.org/wiki/Chinese_checkers#Rules");

        alert.showAndWait();
    }
    
    public void new_game() //this method turns on when pressing Start for the 1st time
    {
        try 
        {
        	factory = new Factory(board,"localhost",this);
        	factory.play();
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
   }
    
   public void endmove()
   {
	   factory.endmove();
   }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
    
    public void setText(String string)
    {
        textfield.setText(string);
    }
    
}
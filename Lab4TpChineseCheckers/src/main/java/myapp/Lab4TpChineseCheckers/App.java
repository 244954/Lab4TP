package myapp.Lab4TpChineseCheckers;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception //tutaj wlaczam aplikacje, raczej intuicyjne
    {
        System.out.println("getClass: " + myapp.Lab4TpChineseCheckers.GUIController.class);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        
        Parent root = loader.load();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Chinese Checkers");
        stage.show(); 
    }
    
    public static void main(String[] args)
    {
        launch(args);
    	/* int [][]p=new int[4][6];
    	for (int i=0;i<6;i++)
    	{
    		
    		for(int j=0;j<4;j++)
    			System.out.print( "*" );
    		
			System.out.print( "\n" );
    	}
        System.out.println( "Hello World!" ); */
    }
}

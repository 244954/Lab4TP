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
    public void start(Stage stage) throws Exception //turning application on
    {
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
    }
}

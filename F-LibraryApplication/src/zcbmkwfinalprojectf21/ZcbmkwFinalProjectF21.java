
package zcbmkwfinalprojectf21;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ZcbmkwFinalProjectF21 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("FXMLDocuments/UserSignin.fxml"));
        
        Scene mainMenuScene = new Scene(mainMenuParent);
        
        stage.setScene(mainMenuScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

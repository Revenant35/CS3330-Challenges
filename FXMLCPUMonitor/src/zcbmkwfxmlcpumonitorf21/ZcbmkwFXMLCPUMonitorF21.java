
package zcbmkwfxmlcpumonitorf21;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ZcbmkwFXMLCPUMonitorF21 extends Application {
    
    private Parent root;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        try{
            root = FXMLLoader.load(getClass().getResource("CPUMonitorFXMLDocument.fxml"));
        } catch (IOException e) {
            System.out.println(e);
        }
        Scene scene = new Scene(root, 600, 400);
        
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

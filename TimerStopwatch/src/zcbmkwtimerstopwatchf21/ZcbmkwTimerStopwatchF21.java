
package zcbmkwtimerstopwatchf21;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ZcbmkwTimerStopwatchF21 extends Application {
    
    private final String appName = "ZcbmkwTimerStopwatchF21";
    
    @Override
    public void start(Stage primaryStage) {

        Stopwatch stopWatch = new Stopwatch();
        Scene scene = new Scene((GridPane)stopWatch.getRootContainer(), 400,500);
        
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

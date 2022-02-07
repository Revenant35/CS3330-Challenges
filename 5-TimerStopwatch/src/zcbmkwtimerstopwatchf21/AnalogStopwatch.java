
package zcbmkwtimerstopwatchf21;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class AnalogStopwatch {
    
//Object Fields
    
    //StackPane housing the Clockface
    private final StackPane rootContainer;
    
    //Image loading for Clockface
    private ImageView dialImageView;
    private ImageView handImageView;
    private Image dialImage;
    private Image handImage;
    private final String dialImageFileName = "clockface.png";
    private final String handImageFileName = "hand.png";

    //TimeLine object used to control the movement of the clock
    private Timeline timeline;
    
    //Fields used to increment/set the time displayed
    private int secondsElapsed = 0;
    private final double angleDeltaPerSecond = 6.0;
//
  
    
//Default Constructor
    
    protected AnalogStopwatch(){
        rootContainer = new StackPane();
        initUI();
        initTimeline();
    }
//  
   
    
//Initialization functions
    
    //Initialize the User InterFace (create clock face and add to the rootContainer)
    private void initUI(){
        dialImage = new Image(getClass().getResourceAsStream(dialImageFileName));
        handImage = new Image(getClass().getResourceAsStream(handImageFileName));
        
        dialImageView = new ImageView(dialImage);
        handImageView = new ImageView(handImage);
        
        rootContainer.getChildren().addAll(dialImageView, handImageView);
    }
    
    //Initialize the TimeLine (Controls the speed of the clock)
    private void initTimeline(){
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), (ActionEvent actionEvent) -> update());
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
//   
    
    
//TimeLine control methods to be used by the Stopwatch Class
    
    //start the clock
    protected void start(){
        timeline.play();
    }
    
    //stop the clock
    protected void stop(){
        timeline.pause();
    }
//  
    
    
//Update/set clock position
    
    //Update Method used by the Timeline
    private void update(){
        set(++secondsElapsed);
    }
    
    //Sets the time to be displayed with an integer input in seconds, can be used by Stopwatch class
    protected void set(int newValue){
        handImageView.setRotate(newValue * angleDeltaPerSecond);
    }
//   
    
    
//Boolean methods to be used by the Stopwatch Class
    
    //Return true if the timeline and rootContainer are initialized
    protected boolean isInitialized(){
        return timeline != null && rootContainer != null;
    }
    
    //Return true if timeline is running, else return false
    protected boolean isRunning(){
        if(isInitialized())
            if(timeline.getStatus().equals(Animation.Status.RUNNING))
                    return true;
        return false;
    }
//  
    
    
//Function used to get the StackPane with our clockFace in it
    
    protected Parent getRootContainer(){
        return rootContainer;
    }
}
//
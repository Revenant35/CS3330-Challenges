
package zcbmkwtimerstopwatchf21;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.NANOS;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DigitalTimer {
    
// Object Fields
    
    //Root container storing all DigitalTimer Data
    private HBox rootContainer;
    
    //LocalTime fields as well as a Formatter of pattern mm:ss.SS
    private LocalTime currentTime;
    private LocalTime remainingTime;
    private LocalTime latestLap;
    private LocalTime championTime;
    private final LocalTime timerDuration;
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("mm:ss.SS");
    
    //Boolean field to indicate if the time has expired
    private boolean isFinished;
    
    //TimeLine field to control the flow of the timer
    private Timeline timeline;
    
    //All Text fields that will be changed through User Input
    private Text currentTimeText;
    private Text remainingTimeText;
    private Text championTimeText;
    private Text lapText[];
    
    //Integer to store the total times record has been pressed 
    private int lapCounter = 0;
    
//
    
   
    
// Constructor - Takes duration as an input
    protected DigitalTimer(int duration){
        timerDuration = remainingTime = LocalTime.ofSecondOfDay(duration);
        currentTime = latestLap = championTime = LocalTime.MIN;
        isFinished = false;
        initTimeline(duration);
        initUI();
    }
//    
    
    
    
// Initialization of the Timeline and User Interface
    
    //A method to create a timeline with a set duration, sets isFinished = true after timeline ends
    private void initTimeline(int duration){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), (ActionEvent actionEvent) -> update());
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(duration * 100);
        timeline.setOnFinished(event -> timesUp());
    }
    
    //A method to create and initialize two VBoxes using subMethods and Add both to the rootContainer
    private void initUI(){
        VBox timerBox = initTimerBox();
        VBox lapTimeBox = initLapTimeBox();
        
        rootContainer = new HBox(10, timerBox, lapTimeBox);
    }

    //A (sub)method to create, initialize, and return a VBox, timerBox, with:
        //currentTime - The total time elapsed
        //remainingTime - The total time remaining (timerDuration - currentTime)
        //championTime - The shortest recorded lap
    private VBox initTimerBox(){
        currentTimeText = new Text(currentTime.format(df));
        remainingTimeText = new Text("Timer: " + remainingTime.format(df));
        championTimeText = new Text("Champion: " + currentTime.format(df));
        
        currentTimeText.setFont(new Font("Arial", 24));
        remainingTimeText.setFont(new Font("Arial", 18));
        championTimeText.setFont(new Font("Arial", 18));
        
        VBox timerBox = new VBox(10, currentTimeText, remainingTimeText, championTimeText);
        timerBox.setAlignment(Pos.CENTER);
        
        return timerBox;
    }
    
    //A (sub)method to create, initialize, and return a VBox, lapTimeBox, with:
        //lapHeader - A label to be displayed above the lapText objects
        //lapText [3] - Array of text to house laps recorded by the user (initialized to 00:00.00)
            //The lapText is affected in the future by the record() function
    private VBox initLapTimeBox(){
        Label lapHeader = new Label("Lap Time");
        
        lapText = new Text[] {
            new Text("Rec " + 0 + " +" + LocalTime.MIN.format(df)),
            new Text("Rec " + 0 + " +" + LocalTime.MIN.format(df)), 
            new Text("Rec " + 0 + " +" + LocalTime.MIN.format(df))
        };
        
        lapHeader.setFont(new Font("Arial", 16));
        lapText[0].setFont(new Font("Arial", 15));
        lapText[1].setFont(new Font("Arial", 15));
        lapText[2].setFont(new Font("Arial", 15));
        
        VBox lapTimeBox = new VBox(5, lapHeader, lapText[0], lapText[1], lapText[2]);
        lapTimeBox.setAlignment(Pos.CENTER);
        return lapTimeBox;
    }
    
//
    
    
    
// DigitalTimer Element Manipultation Methods
    
    //Updates the currentTime and remainingTime by one centisecond
    protected void update(){
        currentTime = currentTime.plusNanos(10000000);
        remainingTime = remainingTime.minusNanos(10000000);
        updateText();
    }
    
    //Updates the text displayed by DigitalTimer to reflect the new LocalTime values
    protected void updateText(){
        currentTimeText.setText(currentTime.format(df));
        remainingTimeText.setText("Timer: " + remainingTime.format(df));
        championTimeText.setText("Champion: " + championTime.format(df));
    }
    
    // A function that records a new lap and stores it via Text in the lapText[] array
    private void newLap(){
        LocalTime newLap = LocalTime.ofNanoOfDay(NANOS.between(latestLap, currentTime));
        if(newLap.compareTo(LocalTime.MIN) != 0){
            lapText[lapCounter++ % 3].setText("Rec " + lapCounter + " + " + newLap.format(df));
            if(newLap.compareTo(championTime) < 0 || championTime.compareTo(LocalTime.MIN) == 0)
                championTime = newLap;
            latestLap = currentTime;
        }
    }
    
    //Start the timeLine so long as it isnt finished
    protected void start(){
        if(!isFinished)
            this.timeline.play();
    }
    
    //Stops the timeLine
    protected void stop(){
        this.timeline.pause();
    }
    
    //resets the LocalTime fields to their initial values
    protected void reset(){
        stop();
        remainingTime = timerDuration;
        latestLap = currentTime = championTime = LocalTime.MIN;
        lapCounter = 0;
        for(int i = 0; i < 3; i++)
            this.lapText[i].setText("Rec " + 0 + " +" + LocalTime.MIN.format(df));
        updateText();
    }
    
    //Records a new lap and stores it in the lapText[] field if the timer hasn't expired
        //Otherwise it displays an Alert box saying the time is up
    protected void record(){
        if(!isFinished){
            newLap();
            updateText();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Time is up... No more records.", ButtonType.OK);
            alert.setHeaderText("Alert");
            alert.setTitle("Time is up");
            alert.showAndWait();
        }
    }
    
    //Returns the value of this.isFinished
    protected boolean isFinished(){
        return this.isFinished;
    }
    
    protected boolean isInitialized(){
        if(this.timeline != null && this.rootContainer != null)
            return true;
        return false;
    }
    
    //Determines if the timer has been initialized and if the timeline is running
    protected boolean isRunning(){
        if(isInitialized())
            if(timeline.getStatus().equals(Animation.Status.RUNNING))
                    return true;
        return false;
    }
    
    //Updates the value of this.isFinished, updates this.remainingTime, and stops the timeline
    protected void timesUp(){
        remainingTimeText.setText("Time's Up!");
        isFinished = true;
        this.stop();
    }
    
    //returns the rootContainer
    protected Parent getRootContainer(){
        return rootContainer;
    }
}

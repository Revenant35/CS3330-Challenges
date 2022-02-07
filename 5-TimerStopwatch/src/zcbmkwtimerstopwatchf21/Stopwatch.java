
package zcbmkwtimerstopwatchf21;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class Stopwatch {
    
// Object Fields
    
    // The Analog and Digital sections of the full stopwatch
    private final AnalogStopwatch analogClock;
    private final DigitalTimer digitalClock;
    
    // The duration the user inputs (1-3599, inclusive)
    private final int timerDuration;
    
    // The two button that will control the stopwatch
    private Button startAndStop;
    private Button recordAndReset;
   
    // The rootContainer which will house the UI elements of the stopwatch
    private GridPane rootContainer;
//
    
    
// Constructor
    public Stopwatch(){
        timerDuration = inputTimerDuration("Enter desired timer duration in seconds:");
        analogClock = new AnalogStopwatch();
        digitalClock = new DigitalTimer(timerDuration);
        setupUI();
    }
//
    
    
    
// Initialize all elements of the stopwatch
    
    // Initialize the rootContainer and fills it with the Analog and Digital elements as well as buttons
    private void setupUI(){
        rootContainer = new GridPane();
        rootContainer.setAlignment(Pos.TOP_CENTER);
        rootContainer.add(analogClock.getRootContainer(), 0, 0);
        rootContainer.add(digitalClock.getRootContainer(), 0, 1);
        rootContainer.add(initializeButtons(), 0, 2);
    }
    
    // Recieves the duration as an integer from the user
    private int inputTimerDuration(String contentText){
        TextInputDialog timerInitial = new TextInputDialog();
        timerInitial.setContentText(contentText);
        timerInitial.setTitle("Time Setup");
        timerInitial.setHeaderText("Set up the start time:");
        timerInitial.showAndWait();
        try {
            int duration = Integer.parseInt(timerInitial.getEditor().getText());
            if(duration < 3600 && duration > 0 && timerInitial.getEditor().getText().length() != 0)
                return Integer.parseInt(timerInitial.getEditor().getText());
        } catch ( NumberFormatException e ) {}
        return inputTimerDuration("Please enter a positive, non-zero integer between 1 and 3599");
    }
    
    // Initializes the butons and HBox housing the buttons
    private HBox initializeButtons(){
        startAndStop = new Button("Start");
        recordAndReset = new Button("Record");
        startAndStop.setMaxWidth(Double.MAX_VALUE);
        recordAndReset.setMaxWidth(Double.MAX_VALUE);
        
        HBox controlButtons = new HBox(10, startAndStop, recordAndReset);
        controlButtons.setAlignment(Pos.CENTER);
        controlButtons.setMaxWidth(300);
        controlButtons.setPadding(new Insets(25,25,25,25));
        
        startAndStop.setOnAction((ActionEvent event) -> {
            startAndStopHandle();
        });
        
        recordAndReset.setOnAction((ActionEvent event) -> {
            recordAndResetHandle();
        });
        
        return controlButtons;
    }
//
    
    
    
// Button control methods
    
    // start/stop button, stops working once isFinished = true
    public void startAndStopHandle(){
        if(!digitalClock.isFinished()){
            if(startAndStop.getText().compareTo("Start") == 0){
                start();
                recordAndReset.setText("Record");
                startAndStop.setText("Stop");
            }
            else{
                stop();
                startAndStop.setText("Start");
                recordAndReset.setText("Reset");
            }
        }
    }
    
    // record/reset button
    private void recordAndResetHandle(){
        if(recordAndReset.getText().compareTo("Record") == 0){
            record();
        }
        else{
            reset();
            recordAndReset.setText("Record");
        }
    }
//
    
    
    
// 
    private void start(){
        analogClock.start();
        digitalClock.start();
    }
    
    public void stop(){
        analogClock.stop();
        digitalClock.stop();
    }
    
    public void reset(){
        stop();
        analogClock.set(0);
        digitalClock.reset();
    }
    
    public void record(){
        digitalClock.record();
    }
//
    
    
    
// Getters
    
    // returns true if both clocks are initialized and running, false otherwise
    public boolean isRunning(){
        if(analogClock.isInitialized() && digitalClock.isInitialized())
            if(analogClock.isRunning() && digitalClock.isRunning())
                return true;
        return false;
    }
    
    // returns the root container (GridPane)
    public Parent getRootContainer(){
        return rootContainer;
    }
}

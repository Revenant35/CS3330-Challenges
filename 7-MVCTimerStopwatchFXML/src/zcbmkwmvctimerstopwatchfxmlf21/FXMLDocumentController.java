
package zcbmkwmvctimerstopwatchfxmlf21;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class FXMLDocumentController implements Initializable {
    
    @FXML private ImageView handImageView;
    
    @FXML private Text currentTime;
    @FXML private Text remainingTime;
    @FXML private Text lapCount;
    @FXML private Text lapTime;
    @FXML private Text averageLapTime;
    
    @FXML private Button startStopButton;
    @FXML private Button recordResetButton;
    
    @FXML private LineChart recordedLapGraph;
    @FXML private AreaChart areaLapGraph;
    
    private AnalogController analogController;
    private DigitalController digitalController;
    private XYChart.Series seriesMean;
    private XYChart.Series seriesRecorded;
    private int laps;
    private Timeline timeline;
    
    @FXML
    private void startStopButtonHandle(ActionEvent event) {
        if(!digitalController.isFinished()){
            if(!isRunning())
                start();
            else
                stop();
        }
    }
    
    @FXML
    private void recordResetButtonHandle(ActionEvent event){
        if(recordResetButton.getText().compareTo("Record") == 0){
            if(isRunning()){
                record();
            }
        }
        else
            reset();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        laps = 0;
        int duration = inputTimerDuration("Enter a duration for the timer");
        analogController = new AnalogController();
        digitalController = new DigitalController(duration);
        initTimeline();
        initCharts();
    }
    
    private void initCharts(){
        seriesMean = new XYChart.Series();
        areaLapGraph.getData().addAll(seriesMean);
    
        seriesRecorded = new XYChart.Series();
        recordedLapGraph.getData().addAll(seriesRecorded);
    }
    
    private void initTimeline(){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> update());
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    
  
    private int inputTimerDuration(String contentText){
        int duration;
        TextInputDialog timerInitial = new TextInputDialog();
        timerInitial.setContentText(contentText);
        timerInitial.setTitle("Time Setup");
        timerInitial.setHeaderText("Set up the start time:");
        timerInitial.showAndWait();
        try {
            duration = Integer.parseInt(timerInitial.getEditor().getText());
            if(duration <= 86400 && duration > 0 && timerInitial.getEditor().getText().length() != 0)
                return Integer.parseInt(timerInitial.getEditor().getText());
        } catch ( NumberFormatException e ) {}
        return inputTimerDuration("Please enter a positive, non-zero integer between 1 and 86400");
    }

    
    
    private void update(){
        handImageView.setRotate(analogController.update());
        if(!digitalController.isFinished()){
            digitalController.update();
            updateText();
        } else
            remainingTime.setText("Time's Up!");
    }
    
    private void updateText(){
        currentTime.setText(digitalController.getCurrentTime());
        remainingTime.setText(digitalController.getRemainingTime());
    }
    
    private void updateLapText(){
        lapTime.setText(digitalController.getLapTime());
        averageLapTime.setText(digitalController.getAverageLapTime());
        lapCount.setText(Integer.toString(laps));
    }
    
    private void updateCharts(){
        seriesMean.getData().add(new XYChart.Data(Integer.toString(laps), digitalController.getLongAverageTime()));
        seriesRecorded.getData().add(new XYChart.Data(Integer.toString(laps), digitalController.getLongLapTime()));
    }
    
    
    
    private void clearCharts(){
        seriesMean.getData().clear();
        seriesRecorded.getData().clear();
    }
    
    
    
    private void start(){
        timeline.play();
        recordResetButton.setText("Record");
        startStopButton.setText("Stop");
    }
    
    private void stop(){
        timeline.pause();
        startStopButton.setText("Start");
        recordResetButton.setText("Reset");
    }
    
    private void reset(){
        timeline.stop();
        digitalController.reset();
        analogController.reset();
        handImageView.setRotate(0);
        recordResetButton.setText("Record");
        laps = 0;
        clearCharts();
        updateText();
        updateLapText();
    }
    
    private void record(){
        if(!digitalController.isFinished()){
            digitalController.newLap(++laps);
            updateLapText();
            updateCharts();
        }
        else{
            alertBox();
        }
    }
    
    private void alertBox(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Time is up... No more records.", ButtonType.OK);
        alert.setHeaderText("Alert");
        alert.setTitle("Time is up");
        alert.showAndWait();
    }
    
    private boolean isRunning(){
        if(timeline != null)
            return (timeline.getStatus() == Status.RUNNING);
        return false;
    }
}

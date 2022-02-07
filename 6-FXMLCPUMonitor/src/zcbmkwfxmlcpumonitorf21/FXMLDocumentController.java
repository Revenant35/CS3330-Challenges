
package zcbmkwfxmlcpumonitorf21;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FXMLDocumentController implements Initializable {
    

    private XYChart.Series seriesRecorded;
    private XYChart.Series seriesMean;
    private boolean isReset = false;
    private boolean isRecording = false;
    private CPUMonitor cpuMonitor;
    
    @FXML private Button b1;
    @FXML private Button b2;
    @FXML private ImageView handImageView;
    @FXML private Text peakCPUText;
    @FXML private Text meanCPUText;
    @FXML private Text currentCPUText;
    @FXML private AreaChart areaChart;
    @FXML private LineChart lineChart;

    @FXML
    private void handleB2(ActionEvent event) {
        if(isReset){
            cpuMonitor.reset();
            b2.setText("Record");
            isReset = false;
            isRecording = false;
        } else if(cpuMonitor.isRunning()){ 
            isRecording = !isRecording;
            cpuMonitor.setRecording(isRecording);
        } else System.out.println("Error");
    }
    
    @FXML
    private void handleB1(ActionEvent event) {
        if(cpuMonitor.isRunning()){
            cpuMonitor.pauseTimeline();
            isReset = true;
            b1.setText("Start");
            b1.setStyle("-fx-background-color: #00ff00");
            b2.setText("Reset");
        }
        else{
            cpuMonitor.startTimeline();
            isReset = false;
            b1.setText("Stop");
            b1.setStyle("-fx-background-color: #ff0000");
            b2.setText("Record");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert b1 != null: "b1 failed to initialize from the FXML file";
        assert b2 != null: "b2 failed to initialize from the FXML file";
        assert handImageView != null: "gaugeRoot failed to initialize from the FXML file";
        assert peakCPUText != null: "peakCPUText failed to initialize from the FXML file";
        assert meanCPUText != null: "meanCPUText failed to initialize from the FXML file";
        assert currentCPUText != null: "currentCPUText failed to initialize from the FXML file";
        assert areaChart != null: "areaChart failed to initialize from the FXML file";
        assert lineChart != null: "lineChart failed to initialize from the FXML file";
        
        initializeAreaChart();
        initializeLineChart();
        
        b1.setStyle("-fx-background-color: #00ff00");
        
        cpuMonitor = new CPUMonitor(handImageView, 
                peakCPUText, 
                meanCPUText, 
                currentCPUText,
                seriesMean,
                seriesRecorded);
    }
    
    private void initializeAreaChart(){
        seriesMean = new XYChart.Series();
        areaChart.getData().addAll(seriesMean);
    }
    
    private void initializeLineChart(){
        seriesRecorded = new XYChart.Series();
        lineChart.getData().addAll(seriesRecorded);
    }
    
}

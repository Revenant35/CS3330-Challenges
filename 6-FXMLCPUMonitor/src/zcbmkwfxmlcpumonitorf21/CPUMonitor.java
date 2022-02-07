
package zcbmkwfxmlcpumonitorf21;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CPUMonitor {
    
    private double peakLoad;
    private double meanLoad;
    private double currentLoad;
    private long updates;
    private long records;
    private boolean isRecording;
    
    private Timeline timeline;
    
    private ImageView handImageView;
    private Text peakCPUText;
    private Text meanCPUText;
    private Text currentCPUText;
    private XYChart.Series seriesMean;
    private XYChart.Series seriesRecorded;
    
    
    
    public CPUMonitor(){
        currentLoad = peakLoad = meanLoad = getCPUUsage();
        updates = 1;
        records = 0;
        isRecording = false;
        initTimelines();
    }
    
    public CPUMonitor(ImageView handImageView, 
            Text peakCPUText, 
            Text meanCPUText, 
            Text currentCPUText,
            XYChart.Series seriesMean,
            XYChart.Series seriesRecorded){
        this();
        this.handImageView = handImageView;
        this.peakCPUText = peakCPUText;
        this.meanCPUText = meanCPUText;
        this.currentCPUText = currentCPUText;
        this.seriesMean = seriesMean;
        this.seriesRecorded = seriesRecorded;
    }
    
    
    
    public final double getCPUUsage() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double value = 0;
        for(Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().startsWith("getSystemCpuLoad") && Modifier.isPublic(method.getModifiers())) {
                try {value = (double) method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = 0;
                } return value;
            }
        }return value;
    }
    
    
    
    private void initTimelines(){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), (ActionEvent actionEvent) -> update());
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
   
    
    protected void update(){
        try{
            currentLoad = 100 * getCPUUsage();
        } catch(NullPointerException e){
            setCPUText("null");
            currentLoad = 0;
        } finally{
            setCPUText();
            updateValues();
            setRotate(currentLoad);
            if(isRecording) record();
        }
    }
    
    private void updateValues(){
        if(currentLoad > peakLoad) peakLoad = currentLoad;
        meanLoad += (currentLoad-meanLoad)/++updates;
        
    }
    
    private void setRotate(double newLoad){
        handImageView.setRotate(195 + (newLoad));
    }
    
    private void setCPUText(String currentString){
        currentCPUText.setText(currentString);
    }
    
    private void setCPUText(){
        currentCPUText.setText(String.format("%.2f%%", currentLoad));
        peakCPUText.setText(String.format("%.2f%%", peakLoad));
        meanCPUText.setText(String.format("%.2f%%", meanLoad));
    }
    
    
    
    protected void record(){
        seriesMean.getData().add(new XYChart.Data(Long.toString(records), meanLoad));
        seriesRecorded.getData().add(new XYChart.Data(Long.toString(records++), currentLoad));
    }
    
    protected void reset(){
        setRotate(0);
        currentLoad = meanLoad = peakLoad = 0.0f;
        updates = records = 0;
        isRecording = false;
        setCPUText();
        clearSeries(seriesMean);
        clearSeries(seriesRecorded);
    }
    
    
    
    protected void pauseTimeline(){
        if(this.timeline != null)
            this.timeline.pause();
    }
    
    protected void startTimeline(){
        if(this.timeline != null)
            this.timeline.play();
    }
    
    protected boolean isRunning(){
        return(this.timeline.getStatus() == Animation.Status.RUNNING);
    }
    
    
    
    protected void setRecording(boolean isRecording){
        this.isRecording = isRecording;
    }
    
    protected boolean getRecording(){
        return this.isRecording;
    }
    
    

    private void clearSeries(XYChart.Series series){
        series.getData().clear();
    }
}

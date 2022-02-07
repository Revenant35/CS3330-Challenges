
package zcbmkwmvctimerstopwatchfxmlf21;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.NANOS;

public class DigitalController {
    
    private LocalTime currentTime;
    private LocalTime latestRecordTime;
    private LocalTime latestLapTime;
    private LocalTime averageLapTime;
    private final LocalTime timerDuration;
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss.SS");
    private boolean isFinished;
    
    
    
    // Constructor - timerDuration as an input
    protected DigitalController(int timerDuration){
        this.timerDuration = LocalTime.ofSecondOfDay(timerDuration);
        this.currentTime = this.latestRecordTime = this.latestLapTime = this.averageLapTime = LocalTime.MIN;
        this.isFinished = false;
    }
    
    
    
    // Updates the currentTime by one centisecond
    protected void update(){
        currentTime = currentTime.plusNanos(10000000);
        if(currentTime.compareTo(timerDuration) == 0)
            isFinished = true;
    }
    
    // Resets the LocalTime fields to their initial values
    protected void reset(){
        currentTime = latestRecordTime = latestLapTime = averageLapTime = LocalTime.MIN;
    }
    
    
    
    // A function that returns the length of a new lap
    protected void newLap(int lapCount){
        latestLapTime = LocalTime.MIN.plus(Duration.between(latestRecordTime, currentTime));
        averageLapTime = LocalTime.MIN.plus(Duration.between(LocalTime.MIN, currentTime).dividedBy(lapCount));
        latestRecordTime = currentTime;
    }
    
    protected double getLongLapTime(){
        System.out.println(Double.toString(latestLapTime.getNano() / 1e+9 + latestLapTime.getSecond() + latestLapTime.getMinute() * 60 + latestLapTime.getHour() * 3600));
        return (latestLapTime.getNano() / 1e+9 + latestLapTime.getSecond() + latestLapTime.getMinute() * 60 + latestLapTime.getHour() * 3600);
    }
    
    protected double getLongAverageTime(){
        return averageLapTime.getNano() / 1e+9 + averageLapTime.getSecond() + averageLapTime.getMinute() * 60 + averageLapTime.getHour() * 3600;
    }
    
    
    
    // Returns the value of this.isFinished
    protected boolean isFinished(){
        return this.isFinished;
    }
    
    // Getters and Setters
    protected String getCurrentTime(){
        return this.currentTime.format(df);
    }
    
    protected String getRemainingTime(){
        return LocalTime.ofNanoOfDay(NANOS.between(currentTime, timerDuration)).format(df);
    }
    
    protected String getLapTime(){
        return this.latestLapTime.format(df);
    }
    
    protected String getAverageLapTime(){
        return this.averageLapTime.format(df);
    }
    
    protected void setCurrentTime(LocalTime currentTime){
        this.currentTime = currentTime;
    }
}


package zcbmkwmvctimerstopwatchfxmlf21;

public class AnalogController {
    
    private long centiSecondsElapsed;
    private final double angleDeltaPerCentisecond = .06;
    
    // Constructor
    protected AnalogController(){
        centiSecondsElapsed = 0;
    }
    
    // Returns an updated angle to rotate the handImageView to
    protected double update(){
        return ++centiSecondsElapsed * angleDeltaPerCentisecond;
    }
    
    // Resets the secondsElapsed field to 0
    protected void reset(){
        centiSecondsElapsed = 0;
    }
}

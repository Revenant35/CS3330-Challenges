package zcbmkwhelloworldf21;

import java.time.LocalDateTime; // from https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html
import java.time.format.DateTimeFormatter; // from https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html

/**
 * @author Zach Brown ZCBMKW
 */
public class Name {
    private String name;
    private String myCourseNum;
    
    // Setters
    public void setName(String name){
        this.name = name;
    }
    public void setCourseNum(String courseNum){
        this.myCourseNum = courseNum;
    }
    
    public void printName(){
        System.out.println("Hello World! My name is " + this.name);
    }
    public void invokeMe(){
        // The information used to code this section was from the oracle website links posted with their respective libraries
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        
        // Turn the day of week class into a string and make the capitalization nicer
        String day = date.getDayOfWeek().toString();
        day = day.substring(0, 1) + day.substring(1).toLowerCase();
        
        // Print out the information
        System.out.println("My Course Number is " + this.myCourseNum);
        System.out.println("Today is " + day);
        System.out.println("Today's date is " + dateFormat.format(date));
        System.out.println("and the Current Time is " + timeFormat.format(date));
    }
}
